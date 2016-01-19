package me.videa.functions.novelloader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.baidu.a.a.a.a.c;

import me.videa.utils.SharePreferenceHelper;
import me.videa.voice.async.AsyncCallback;
import me.videa.voice.async.Result;
import me.videa.voice.show.HandlerWhat;
import me.videa.voice.show.TTSManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class LoaderEngine extends Thread{	
	
	private Context mContext;
	private static LoaderEngine mEngine;
	public static Object mObject = new Object();
	private String mNovelPath;
	private AsyncCallback mCallback;
	private long isLoaded = 0;
	private int mRollCounter = 0;
	private String mRes;
	private Handler mHandler;
	BroadcastReceiver mBroadcastReceiver;
	
	
	private LoaderEngine(Context context, Handler handler, String path) {
		this.mContext = context;
		this.mNovelPath = path;
		this.mHandler = handler;
		mBroadcastReceiver = new NotificationReceiver(context);
		IntentFilter mFilter = new IntentFilter("me.videa.ready");
		mContext.registerReceiver(mBroadcastReceiver, mFilter);
		isLoaded = SharePreferenceHelper.getInstance(mContext).getLong("novel_loaded", 0l);
	}
	
	
	public static LoaderEngine get(Context context, Handler handler, String path){
		if(mEngine == null){
			mEngine = new LoaderEngine(context, handler, path);			
		}
		return mEngine;
	}
	
	@Override
	public void run() {
		try {			
			String res;
			BufferedReader mBufferedReader = getBufferedReader();
			while ((res = mBufferedReader.readLine()) != null) {
				mRollCounter++;
				mRes += res.replace("null", "");
				if(mRollCounter == 5){
					synchronized (this) {
						isLoaded += mRes.length();
						readNovel(mRes);
						mEngine.wait();
					}
				}
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private BufferedReader getBufferedReader() throws IOException{
		if(mNovelPath == null || mNovelPath.equals("")){
			Result mResult = new Result();
			mResult.setErrorMsg("文件路径错误");
			mResult.setSuc(false);
			mCallback.callback(mResult);
			return null;
		}
		FileInputStream mFileInputStream = new FileInputStream(new File(mNovelPath));
		mFileInputStream.skip(isLoaded);
		BufferedReader mBufferedReader = new BufferedReader(new InputStreamReader(mFileInputStream, "GBK"));
		return mBufferedReader;
	}
	
	
	void readNovel(String novel){
		sendToView(novel);
		TTSManager manager = TTSManager.get();
		manager.start(novel);
	}
	
	void sendToView(String novel){
		Message message = new Message();
		Bundle mBundle = new Bundle();
		mBundle.putString("data", novel);
		message.setData(mBundle);
		message.what = HandlerWhat.CONVERSATION_VICKIE;
		mHandler.sendMessage(message);
	}
	
	/**
	 * 唤醒加载器
	 */
	public void notifyEngie(){
		synchronized (this) {
			mRollCounter = 0;
			mRes = "";
			mEngine.notify();
		}
	}


	public AsyncCallback getmCallback() {
		return mCallback;
	}


	public void setmCallback(AsyncCallback mCallback) {
		this.mCallback = mCallback;
	}
	
	public void destroy(){
		mContext.unregisterReceiver(mBroadcastReceiver);
	}
	
	class NotificationReceiver extends BroadcastReceiver{
		
		public NotificationReceiver(Context context) {
			// TODO Auto-generated constructor stub
		}
		
		private final String TAG = "NotificationReceiver";

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if(intent.getAction().equals("me.videa.ready")){
				Log.d(TAG, "快读完啦");
				notifyEngie();
			}
		}
		
	}
	

}
