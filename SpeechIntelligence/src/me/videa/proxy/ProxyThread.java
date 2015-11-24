package me.videa.proxy;

import me.videa.proxy.ProxyParam.REQTYPE;

public class ProxyThread {
	
	private ProxyRequest mProxyRequest;
	@SuppressWarnings("rawtypes")
	private ProxyParam mParam;
	private ProxyPool mPool;
	private REQTYPE mRequst;
	private ProxyTask mTask;
	
	public void setmRequst(REQTYPE mRequst) {
		this.mRequst = mRequst;
	}

	public ProxyThread() {
		// TODO Auto-generated constructor stub
		mPool = ProxyPool.get();
	}
	
	public ProxyThread(ProxyRequest proxyRequest) {
		// TODO Auto-generated constructor stub
		this();
		this.mProxyRequest = proxyRequest;		
	}
	
	/**
	 * 执行代理请求</br>
	 * 若未设置请求参数，则从接口获取请求参数
	 */
	public void startProxy(){
		if(mParam == null){
			mParam = mProxyRequest.proxy();
		}		
		start();
	}
	
	/**
	 * 将请求添加到代理请求队列中</br>
	 * 如果请求参数为null, 则不添加。
	 */
	private void start(){
		if(mParam == null){
			return;
		}
		if(mRequst == null || mRequst == ProxyParam.REQTYPE.ASYNC){
			mPool.addLast(mParam);			
		}else if(mRequst == ProxyParam.REQTYPE.NOW){
			mPool.addFirst(mParam);
		}
		mTask = ProxyTask.get();
		mTask.notify();
	}

	/**
	 * @param mParam the mParam to set
	 */
	public void setmParam(ProxyParam mParam) {
		this.mParam = mParam;
	}

	/**
	 * @return the mRequst
	 */
	public REQTYPE getmRequst() {
		return mRequst;
	}	

}
