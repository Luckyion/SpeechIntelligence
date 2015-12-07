package me.videa.voice.show;

import java.util.Timer;
import java.util.TimerTask;

import me.videa.effects.MainShowView;
import me.videa.utils.NetState;
import me.videa.voice.R;
import me.videa.voice.service.SpeechIntelligence;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

@ContentView(R.layout.voice_main_activity)
public class VoiceMainActivity extends Activity implements HandlerWhat {
	
	
	private final static String TAG = "VoiceMainActivity";

	@ViewInject(R.id.mainView)
	private static MainShowView mVoiceView;
	public static Handler mHandler;
	private Message mMessage;
	private Bundle mBundle;
	/*
	 * This variables need to be global, so we can used them onResume and
	 * onPause method to
	 * 
	 * stop the listener
	 */

	TelephonyManager mTel;

	StateListener mStateListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		mHandler = new ViewHandler();
		mStateListener = new StateListener();
		mTel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
	}
	
	@Override
	protected void onResume() {
		mTel.listen(mStateListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

	static class ViewHandler extends Handler {
		Bundle bundle;

		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			bundle = msg.getData();
			switch (msg.what) {
			case NET_STATE:
				int singleStrenth = bundle.getInt("state");
				Log.d(TAG, "" + singleStrenth);
				ExtraBean mBean = new ExtraBean();
				mBean.setIp(bundle.getString("ip"));
				mBean.setSingleStrength(singleStrenth);
				mVoiceView.reDraw(NET_STATE, mBean);
				break;

			default:
				break;
			}
			super.dispatchMessage(msg);
		}

	}

	/**
	 * 启动网络状态动态效果的任务
	 */
	private void startNetShowTask() {
		Timer mTimer = new Timer();
		mTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mHandler.sendEmptyMessage(NET_STATE);
			}
		}, 5000, 300);
	}

	//
	// class RandomView implements Runnable{
	//
	// @Override
	// public void run() {
	// // TODO Auto-generated method stub
	// T
	// }
	//
	// }

	/* Start the PhoneState listener */

	/* —————————– */

	private class StateListener extends PhoneStateListener

	{

		/*
		 * Get the Signal strength from the provider, each tiome there is an
		 * update 从得到的信号强度,每个tiome供应商有更新
		 */

		@Override
		public void onSignalStrengthsChanged(SignalStrength signalStrength)

		{

			super.onSignalStrengthsChanged(signalStrength);		
			
			mMessage = new Message();
			mMessage.what = NET_STATE;
			mBundle = new Bundle();
			mBundle.putInt("state", signalStrength.getGsmSignalStrength());
			mBundle.putString("ip", NetState.getLocalIpAddress(VoiceMainActivity.this));
			mMessage.setData(mBundle);
			mHandler.sendMessage(mMessage);

			Toast.makeText(getApplicationContext(),
					"Go to Firstdroid!!! GSM Cinr = "

					+ String.valueOf(signalStrength.getGsmSignalStrength()),
					Toast.LENGTH_SHORT).show();

		}

	};/* End of private Class */

	/**
	 * 启动语音服务
	 */
	void startVoiceService() {
		Intent mService = new Intent(this, SpeechIntelligence.class);
		SpeechIntelligence.mViewHandler = mHandler;
		startService(mService);
	}

}
