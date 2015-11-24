package me.videa.proxy;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ProxyService extends Service implements ProxyCallback, CacheNotify{
	
	private ProxyTask mProxyTask;
	

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		if(mProxyTask == null){
			mProxyTask = ProxyTask.get();
			mProxyTask.setDelay(1000);
			mProxyTask.setInterval(20 * 1000);
			mProxyTask.setmCallback(this);
			mProxyTask.start();
		}
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		mProxyTask.cancel();
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onProxyCompleted(ProxyResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProxyFailed(ProxyResult errorCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProxyStateChanged(Integer... state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendNotification() {
		// TODO Auto-generated method stub
		ProxyTask.get().setmContext(this);
	}	

}
