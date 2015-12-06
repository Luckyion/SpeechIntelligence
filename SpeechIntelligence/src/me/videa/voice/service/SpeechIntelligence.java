package me.videa.voice.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;

public class SpeechIntelligence extends Service{
	
	private final static String TAG = "SpeechIntelligence Service";	
	/**
	 * 是否正在语音合成
	 */
	private static boolean ISRECOGNISING = true;
	private static SpeechRecognizer mRecognizer;
	private static RecycleHandler mHandler;
	
	
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
		speechListener();
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
	
	
	 /** 
     * 初始化监听器。 
     */ 
    private InitListener mInitListener = new InitListener() { 

        @Override 
        public void onInit(int code) { 
            Log.d(TAG, "SpeechRecognizer init() code = " + code); 
            if (code == ErrorCode.SUCCESS) { 
                Toast.makeText(SpeechIntelligence.this, "init success", 0).show(); 
            } 
        } 
    };
    
    /**
     * 语音识别监听器
     */
    private static RecognizerListener mRecognitionListener = new RecognizerListener() {
		
		@Override
		public void onVolumeChanged(int arg0) {
			// TODO Auto-generated method stub
			Log.d(TAG, "onVolumeChanged ----------- " + arg0);
		}
		
		@Override
		public void onResult(RecognizerResult arg0, boolean arg1) {
			// TODO Auto-generated method stub
			ISRECOGNISING = false;
			Log.d(TAG, "onResult:  resultStr ----------- " + arg0.getResultString() + "arg1 : " + arg1);
		}
		
		@Override
		public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {
			// TODO Auto-generated method stub
			Log.d(TAG, "onEvent:" + arg0 + "." + arg1 + "." + arg2 + "." + arg3);
		}
		
		@Override
		public void onError(SpeechError arg0) {
			// TODO Auto-generated method stub
			ISRECOGNISING = false;
			Log.d(TAG, "onError:" + arg0.getErrorDescription());
		}
		
		@Override
		public void onEndOfSpeech() {
			// TODO Auto-generated method stub
			Log.d(TAG, "onEndOfSpeech");
		}
		
		@Override
		public void onBeginOfSpeech() {
			// TODO Auto-generated method stub
			Log.d(TAG, "onBeginOfSpeech");
		}
	};
	
	/**
	 * 监听语音并进行语音合成
	 */
	private void speechListener(){
		mRecognizer = SpeechRecognizer.createRecognizer(this, mInitListener);
		mRecognizer.setParameter(SpeechConstant.DOMAIN, "iat");    
		mRecognizer.setParameter(SpeechConstant.LANGUAGE, "zh_cn");    
		mRecognizer.setParameter(SpeechConstant.ACCENT, "mandarin "); 		
		mRecognizer.startListening(mRecognitionListener);		
	}
	
	
	static class RecycleHandler extends Handler{

		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			switch (msg.what) {
			case 0:
				mRecognizer.startListening(mRecognitionListener);
				break;

			default:
				break;
			}
		}
		
	}
	
	/**
	 * 循环监听语音控制线程</br>
	 * 
	 */
	class RecycleManager implements Runnable{
		
		public RecycleManager() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(ISRECOGNISING){
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			mHandler.sendEmptyMessage(0);
		}
		
	}

}
