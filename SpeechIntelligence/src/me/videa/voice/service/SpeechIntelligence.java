package me.videa.voice.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class SpeechIntelligence extends Service{
	
	private final static String TAG = "SpeechIntelligence Service";
	
	
	public class LocalBinder extends Binder{
		String stringToSend = "I'm the test String"; 
		SpeechIntelligence getService() { 
            Log.i("TAG", "getService ---> " + SpeechIntelligence.this); 
            return SpeechIntelligence.this; 
        } 
	}
	
	private final IBinder mBinder = new LocalBinder(); 
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onBind");
		return mBinder;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.i(TAG, "onCreate");
		super.onCreate();	
//		new Contacts(this).getContacts();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i(TAG, "onDestroy");
		super.onDestroy();		
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onUnbind");
		return super.onUnbind(intent);
	}
	
	

}
