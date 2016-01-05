package me.videa.proxy;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import me.videa.application.SessionManager;
import me.videa.utils.WebServiceHelper;
import android.content.Context;
import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class ProxyTask extends TimerTask implements ProxyTransferCallback{
	
	private final String TAG = "ProxyTask";
	
	private static Timer timer;
	
	private static ProxyTask mTask;
	
	private boolean isStarted = false;
	
	private int interval;
	
	private int delay;
	
	private static ProxyPool mProxyPool;
	
	private static LinkedList<ProxyParam> mPool;
	
	private ProxyParam mParam;
	
	private ProxyCallback mCallback;
	
	private CacheNotify mCacheNotify;
	
	private ProxyResult mProxyResult;
	
	private Context mContext;
	
	public static Object object = new Object();
	
	private ProxyTask(){
		
	}
	
	public static ProxyTask get(){
		if(mTask == null){
			timer = new Timer();
			mTask = new ProxyTask();
			mProxyPool = ProxyPool.get();
			mPool = mProxyPool.getmPool();
		}
		return mTask;
	}

	/**
	 * @return the interval
	 */
	public int getInterval() {
		return interval;
	}

	/**
	 * @param interval the interval to set
	 */
	public void setInterval(int interval) {
		this.interval = interval;
	}
	
	

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	/**
	 *  启动队列线程
	 */
	public void start(){
		if(!isStarted){
			timer.schedule(this, delay, interval);
		}		
	}

	@Override
	public void run() {				
		if(mPool.size() == 0){
			if(mContext != null && mCacheNotify != null){
				mCacheNotify.sendNotification();
				return;
			}
			try {
				synchronized (this) {
					mTask.wait();
				}				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		mParam = mPool.getFirst();
		if(mParam instanceof ServParams){
			sendServletRequest();
		}else if(mParam instanceof WSParams){
			sendWebServiceRequest();
		}else if(mParam instanceof EntityParams){
			ProxyEntity entity = (ProxyEntity) mParam.getmParams();
			new HttpMultipartPost(mParam.getmContext(), this, entity);
		}
		mPool.removeFirst();
	}

	/**
	 * @return the mCallback
	 */
	public ProxyCallback getmCallback() {
		return mCallback;
	}

	/**
	 * @param mCallback the mCallback to set
	 */
	public void setmCallback(ProxyCallback mCallback) {
		this.mCallback = mCallback;
	}

	@Override
	public void onTransferProgressChanged(Integer... progress) {
		// TODO Auto-generated method stub
		if(mCallback != null){
			mCallback.onProxyStateChanged(progress);
		}
	}

	@Override
	public void onTransferCompleted(ProxyResult result) {
		// TODO Auto-generated method stub
		if(mCallback != null){
			mCallback.onProxyCompleted(result);
		}
	}

	@Override
	public void onTransferFailed(ProxyResult errorCode) {
		// TODO Auto-generated method stub
		if(mCallback != null){
			mCallback.onProxyFailed(errorCode);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	/**
	 * 发送Servlet请求并返回结果
	 */
	private void sendServletRequest(){
		mProxyResult = new ProxyResult();
		mProxyResult.setFailedTimes("0");
		mProxyResult.setIsHandled("0");
		mProxyResult.setIsNotify("0");
		mProxyResult.setMark(mParam.getmContext().getClass().getName());
		mProxyResult.setProxyId(ProxyUUD.getProxyUUD());
		mProxyResult.setProxyUrl(mParam.getmUrl());
		mProxyResult.setStatus("0");
		mProxyResult.setUserID(SessionManager.getSessionManager().getSession().get("user_id"));
		if(!mParam.isCache()){
			ProxyResultDbHelper.saveProxyResult(mParam.getmContext(), mProxyResult);
		}		
		HttpUtils httpUtils = new HttpUtils();
		RequestParams params = new RequestParams(); // 默认编码UTF-8
		Map<String, String> map = (Map<String, String>) mParam.getmParams();
		if(map != null && map.size() != 0){
			Set<String> keys = map.keySet();
			for(String key : keys){
				params.addBodyParameter(key, map.get(key));
			}
		}		
		httpUtils.send(HttpRequest.HttpMethod.POST, mParam.getmUrl(), params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub				
				mCallback.onProxyFailed(mProxyResult);
				arg0.printStackTrace();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				Log.d(TAG, arg0.result);
				mProxyResult = ProxyResultDbHelper.findProxyResultByKey(mParam.getmContext(), "PROXYID", mProxyResult.getProxyId());
				mProxyResult.setStatus("-1");
				Log.d(TAG, "ProxyId : " + mProxyResult.getProxyId());
				mProxyResult = ProxyResultDbHelper.updateAndReturnProxyResult(mParam.getmContext(), mProxyResult);
				mCallback.onProxyCompleted(mProxyResult);
			}
		});
	}
	
	/**
	 * 请求Webservice 并返回结果
	 */
	@SuppressWarnings("unchecked")
	private void sendWebServiceRequest(){
		mProxyResult = new ProxyResult();
		mProxyResult.setContent(mParam.paramToString());
		mProxyResult.setFailedTimes("0");
		mProxyResult.setIsHandled("0");
		mProxyResult.setIsNotify("0");
		mProxyResult.setMark(mParam.getmContext().getClass().getName());
		mProxyResult.setProxyId(ProxyUUD.getProxyUUD());
		mProxyResult.setProxyMethod(((WSParams) mParam).getmMethod());
		mProxyResult.setProxyNameSpace(((WSParams) mParam).getmNameSpace());
		mProxyResult.setProxyUrl(mParam.getmUrl());
		mProxyResult.setStatus("0");
		mProxyResult.setUserID(SessionManager.getSessionManager().getSession().get("user_id"));
		ProxyResultDbHelper.saveProxyResult(mParam.getmContext(), mProxyResult);
		String result = WebServiceHelper.getQueryResult(((WSParams) mParam).getmMethod(), 
				mParam.getmUrl(), ((WSParams) mParam).getmNameSpace(), (List<String>) mParam.getmParams());
		if(result == null || result.equals("") || result.equals("{}")){	
			mCallback.onProxyFailed(mProxyResult);
			return;
		}
		mProxyResult.setStatus("-1");
		mProxyResult = ProxyResultDbHelper.updateAndReturnProxyResult(mParam.getmContext(), mProxyResult);
		mCallback.onProxyCompleted(mProxyResult);
	}

	/**
	 * @return the mCacheNotify
	 */
	public CacheNotify getmCacheNotify() {
		return mCacheNotify;
	}

	/**
	 * @param mCacheNotify the mCacheNotify to set
	 */
	public void setmCacheNotify(CacheNotify mCacheNotify) {
		this.mCacheNotify = mCacheNotify;
	}

	/**
	 * @return the mContext
	 */
	public Context getmContext() {
		return mContext;
	}

	/**
	 * @param mContext the mContext to set
	 */
	public void setmContext(Context mContext) {
		this.mContext = mContext;
	}

	public static Timer getTimer() {
		return timer;
	}	
	
	
}
