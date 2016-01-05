package me.videa.proxy;

import java.util.LinkedList;

@SuppressWarnings("rawtypes")
public class ProxyPool {	

	private static LinkedList<ProxyParam> mPool;
	
	private static ProxyPool mProxyPool;
	
	private ProxyPool(){		
	}
	
	public static ProxyPool get(){
		if(mProxyPool == null){
			mProxyPool = new ProxyPool();
			mPool = new LinkedList<ProxyParam>();
		}		
		return mProxyPool;
	}
	
	public void addFirst(ProxyParam mParam){
		mPool.addFirst(mParam);
	}
	
	public void addLast(ProxyParam mParam){
		mPool.addLast(mParam);
	}
	
	public void remove(int index){
		mPool.remove(index);
	}
	
	public void remove(){
		mPool.remove();
	}
	
	public void getFirst(){
		mPool.getFirst();
	}
	
	public void getLast(){
		mPool.getLast();
	}
	
	public long poolSize(){
		return mPool.size();
	}

	public LinkedList<ProxyParam> getmPool() {
		return mPool;
	}

}
