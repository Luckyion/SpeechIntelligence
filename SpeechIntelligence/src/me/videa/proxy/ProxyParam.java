package me.videa.proxy;

import android.content.Context;


public abstract class ProxyParam<T> {
	
	/**
	 * 请求发送方式</br>
	 * NOW   即时发送并返回结果
	 * ASYNC 异步发送
	 * RANDOM 随机发送
	 * @author Vickie Tang
	 *
	 */
	public static enum REQTYPE{
		NOW, ASYNC
	}
	
	protected T mParams;
	
	private String mUrl;
	
	private Context mContext;
	
	private boolean isCache = false;
	
	public ProxyParam(Context mContext) {
		// TODO Auto-generated constructor stub
		this.setmContext(mContext);
	}
	
	abstract void addParams(T params);
	
	public ProxyParam<T> get(){
		return this;
	}

	public T getmParams() {
		return mParams;
	}

	public void setmParams(T params) {
		this.mParams = params;
	}

	/**
	 * @return the mUrl
	 */
	public String getmUrl() {
		return mUrl;
	}

	/**
	 * @param mUrl the mUrl to set
	 */
	public void setmUrl(String mUrl) {
		this.mUrl = mUrl;
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
	
	protected abstract String paramToString();

	/**
	 * @return the isCache
	 */
	public boolean isCache() {
		return isCache;
	}

	/**
	 * @param isCache the isCache to set
	 */
	public void setCache(boolean isCache) {
		this.isCache = isCache;
	}

}
