package me.videa.functions.screenlock;

import me.videa.base.functions.StartSpecificApplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.iflytek.voicedemo.R;

public class ShowLockActivity extends Activity implements VioceLockCallback{

	
	private final static String TAG = "ShowLockActivity";
	RoundSpinView roundSpinView;
	View view;
	
	LockLayer layer;
	
	LockHandler mLockHandler;
	
	boolean isUnlock = false;
	boolean disableScreenLock = false;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_screen_lock);
		DisplayMetrics displayMetrics = getResources().getDisplayMetrics();	
		int mWidth = displayMetrics.widthPixels;
		int mHeight = displayMetrics.heightPixels;
		roundSpinView = new RoundSpinView(this, this, mWidth, mHeight - 100, 300);		
		view = ShowLockActivity.this.getWindow().getDecorView();
		layer = new LockLayer(this);
		layer.setLockView(roundSpinView);
		mLockHandler = new LockHandler();
		roundSpinView.setLockHandler(mLockHandler);
		layer.lock();
		
	}
	
	class LockHandler extends Handler{

		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0: //锁定屏幕
				break;
				
			case 1://解锁屏幕
				break;
			case 2://锁定屏幕解锁(只允许语音解锁)
				disableScreenLock = true;
				break;
			case 3: //解锁屏幕解锁(取消只语音解锁)
				disableScreenLock = false;
				break;
			case 4://上
				Log.d(TAG, "上");
				Intent intent = new Intent(Intent.ACTION_DIAL);  
		        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  
		                | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);  
		        ShowLockActivity.this.startActivity(intent);  
				isUnlock = true;
				break;
			case 5://下
				Log.d(TAG, "下");
//				new StartSpecificApplication(ShowLockActivity.this)
//					.startSpecificApplication("com.android.camera", "com.android.camera.Camera");
				isUnlock = true;
				break;
			case 6://左
				Log.d(TAG, "左");
//				new StartSpecificApplication(ShowLockActivity.this)
//				.startSpecificApplication("com.android.mms", "com.android.mms.ui.ConversationList");
				isUnlock = true;
				break;
			case 7://右
				Log.d(TAG, "右");				
				isUnlock = true;				
				break;
			default:
				isUnlock = false;
				break;
			}
			super.dispatchMessage(msg);
			if(isUnlock && !disableScreenLock){
				layer.unlock();
				finish();
			}
		}
		
		
		
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	}

	@Override
	public void controlCode(int code) {
		// TODO Auto-generated method stub
		Message message = new Message();
		message.what = code;
		mLockHandler.sendMessage(message);
	}
	
	

}
