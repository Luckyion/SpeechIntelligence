package me.videa.voice.show;

import me.videa.base.functions.BatteryReceiver;
import me.videa.base.functions.DateTimeReceiver;
import me.videa.base.functions.TextToSpeech;
import me.videa.effects.MainShowView;
import me.videa.utils.TimeUtils;
import me.videa.voice.R;
import me.videa.voice.service.SpeechIntelligence;
import me.videa.voice.show.beans.ExtraBean;
import me.videa.voice.show.beans.TimeBean;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

@SuppressLint("HandlerLeak")
@ContentView(R.layout.activity_voice_main)
public class VoiceMainActivity extends Activity implements HandlerWhat {

	private final static String TAG = "VoiceMainActivity";

	@ViewInject(R.id.mainView)
	private static MainShowView mVoiceView;
	public static Handler mHandler;
	private Message mMessage;
	private Bundle mBundle;
	private Context mContext;
	TelephonyManager mTel;
	StateListener mStateListener;
	private BatteryReceiver mBatteryReceiver;
	private DateTimeReceiver mDateTimeReceiver;
	
	private String text = "近几年，随着移动互联网的快速发展，国家电网电力建设飞速发展，电力企业移动信息化管理面临着前所未有的重大发展机遇。移动信息化技术在电力业务领域得到广泛应用，如电网调度自动化、电力负荷控制预测、营销采集系统等，现阶段电力行业信息化管理技术的应用正逐渐由操作层向管理层延伸，从单机、单项目向网络化、整体性、综合性应用发展，对大范围实时的移动信息化管理需求日益迫切";

	/******** 语音合成 ********/

	private TTSManager mTtsManager;

	/******************************************/
	private Toast mToast;

	@SuppressLint("ShowToast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		mContext = this;
		mHandler = new ViewHandler();
		mStateListener = new StateListener();
		mTel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		registerBatteryReceiver();
		registerDateTimeReceiver();
		iniDateAndTime();
		mTtsManager = new TTSManager(this, mHandler, mVoiceView);
//		mTtsManager.start(text);
		mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
	}

	@Override
	protected void onResume() {
		mTel.listen(mStateListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver();
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	class ViewHandler extends Handler {
		Bundle bundle;
		ExtraBean mBean;

		@Override
		public void dispatchMessage(Message msg) {
			bundle = msg.getData();
			switch (msg.what) {
			case NET_STATE:
				int singleStrenth = bundle.getInt("state");
				Log.d(TAG, "" + singleStrenth);
				mBean = new ExtraBean();
				mBean.setIp(bundle.getString("ip"));
				mBean.setSingleStrength(singleStrenth);
				mVoiceView.reDraw(NET_STATE, mBean);
				break;
			case BATTERY_STATE:
				Log.d(TAG, "level : " + bundle.getInt("level") + " scale : "
						+ bundle.getInt("scale"));
				mBean = new ExtraBean();
				Toast.makeText(mContext, bundle.getInt("level") + "",
						Toast.LENGTH_LONG).show();
				mBean.setBatteryLevel(bundle.getInt("level"));
				mVoiceView.reDraw(BATTERY_STATE, mBean);
				break;
			case DATE_STATE:
				mBean = new ExtraBean();
				mBean.setTimeBean((TimeBean) bundle.getSerializable("time"));
				mVoiceView.reDraw(DATE_STATE, mBean);
				break;

			default:
				break;
			}
			super.dispatchMessage(msg);
		}
	}

	/**
	 * 初始化日期和时间信息
	 */
	private void iniDateAndTime() {
		Message message = new Message();
		Bundle mBundle = new Bundle();
		mBundle.putSerializable("time", TimeUtils.getTimeBean());
		message.what = HandlerWhat.DATE_STATE;
		message.setData(mBundle);
		mHandler.sendMessage(message);
	}

	/* Start the PhoneState listener */
	private class StateListener extends PhoneStateListener

	{
		/*
		 * Get the Signal strength from the provider
		 */
		@Override
		public void onSignalStrengthsChanged(SignalStrength signalStrength) {
			super.onSignalStrengthsChanged(signalStrength);
			mMessage = new Message();
			mMessage.what = NET_STATE;
			mBundle = new Bundle();
			mBundle.putInt("state", signalStrength.getGsmSignalStrength());
			mBundle.putString("ip",
					NetState.getLocalIpAddress(VoiceMainActivity.this));
			mMessage.setData(mBundle);
			mHandler.sendMessage(mMessage);
		}

	};

	/**
	 * 注册电量广播接收器
	 */
	private void registerBatteryReceiver() {
		// 注册广播接受者java代码
		IntentFilter intentFilter = new IntentFilter(
				Intent.ACTION_BATTERY_CHANGED);
		// 创建广播接受者对象
		mBatteryReceiver = new BatteryReceiver(mHandler);

		// 注册receiver
		registerReceiver(mBatteryReceiver, intentFilter);
	}

	/**
	 * 注册时间广播接收
	 */
	private void registerDateTimeReceiver() {
		// 注册广播接受者java代码
		IntentFilter mFilter = new IntentFilter(Intent.ACTION_TIME_TICK);
		// 创建广播接受者对象
		mDateTimeReceiver = new DateTimeReceiver(mHandler);

		// 注册receiver
		registerReceiver(mDateTimeReceiver, mFilter);
	}

	/**
	 * 反注册所有广播
	 */
	private void unregisterReceiver() {
		unregisterReceiver(mBatteryReceiver);
		unregisterReceiver(mDateTimeReceiver);
	}

	private void showTip(final String str) {
		mToast.setText(str);
		mToast.show();
	}

	/**
	 * 启动语音服务
	 */
	void startVoiceService() {
		Intent mService = new Intent(this, SpeechIntelligence.class);
		startService(mService);
	}

}
