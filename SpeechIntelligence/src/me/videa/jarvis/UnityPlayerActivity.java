package me.videa.jarvis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.videa.base.functions.BatteryReceiver;
import me.videa.base.functions.DateTimeReceiver;
import me.videa.effects.MainShowView;
import me.videa.functions.local.FileExplore;
import me.videa.functions.map.BDMapView;
import me.videa.functions.map.GpsServiceManager;
import me.videa.functions.map.LocationReceiver;
import me.videa.functions.novelloader.LoaderEngine;
import me.videa.functions.novelloader.NovelShow;
import me.videa.functions.web.MyWebView;
import me.videa.functions.web.RequestOptions;
import me.videa.utils.AssetsResourceLoader;
import me.videa.utils.TimeUtils;
import me.videa.voice.R;
import me.videa.voice.service.SpeechIntelligence;
import me.videa.voice.show.HandlerWhat;
import me.videa.voice.show.NetState;
import me.videa.voice.show.RecognitionManager;
import me.videa.voice.show.TTSManager;
import me.videa.voice.show.VoiceMainActivity;
import me.videa.voice.show.beans.ConversationAdapter;
import me.videa.voice.show.beans.ExtraBean;
import me.videa.voice.show.beans.TimeBean;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;

public class UnityPlayerActivity extends VoiceMainActivity implements HandlerWhat
{
	private final String TAG = "UnityPlayerActivity";
	private static MainShowView mVoiceView;
	public static Handler mHandler;
	private Message mMessage;
	private Bundle mBundle;
	private Context mContext;
	TelephonyManager mTel;
	StateListener mStateListener;
	private BatteryReceiver mBatteryReceiver;
	private DateTimeReceiver mDateTimeReceiver;
	private ListView mConversationView;
	private RelativeLayout mLayout;
	private ConversationAdapter mAdapter;
	private List<String> mConversations;
	private FileExplore mExplore;
	private NovelShow mNovelShow;

	/**************** 地图 ****************/
	private BDMapView mBdMapView;
	private LocationReceiver mLocationReceiver;

	static int counter = 0;
	private boolean isLogined = false;

	/******** 语音合成 ********/

	private TTSManager mTtsManager;
	private RecognitionManager mRecognitionManager;

	/*****************NFC*************************/
	
	/*****************WebView*********************/
	private MyWebView myWebView;

	
	private Toast mToast;	
	protected UnityPlayer mUnityPlayer; // don't change the name of this variable; referenced from native code

	// Setup activity layout
	@Override protected void onCreate (Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		mContext = this;
		getWindow().setFormat(PixelFormat.RGBX_8888); // <--- This makes xperia play happy
		mUnityPlayer = new UnityPlayer(this);
		setContentView(mUnityPlayer);
		mUnityPlayer.requestFocus();
		mHandler = new ViewHandler();
	}
	
	/**
	 * 来自Unity3D的调用
	 */
	public void startMainActivity(String jarvis){
		Log.d(TAG, jarvis);
		mHandler.sendEmptyMessage(LOGIN_OK);
	}
	
	/**
	 * 加载主界面
	 */
	private void mainViewLoad(){
		mVoiceView = (MainShowView) findViewById(R.id.mainView);
		mConversationView = (ListView) findViewById(R.id.conversation);
		mLayout = (RelativeLayout) findViewById(R.id.viewContainer);
		mStateListener = new StateListener();
		mTel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		registerBatteryReceiver();
		registerDateTimeReceiver();
		iniDateAndTime();
		mTtsManager = TTSManager.initManager(this, mHandler, mVoiceView);
		mRecognitionManager = new RecognitionManager(this, mHandler,
		mVoiceView);//启动语音识别
		mConversations = new ArrayList<String>();
		mAdapter = new ConversationAdapter(this, mConversations);
		mConversationView.setAdapter(mAdapter);
		mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
		try {
			AssetsResourceLoader.getXmlData(AssetsResourceLoader.getXmlWithString(mContext, "actions"), "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			case TTS_STATE_INIT:
				// testTimer();
				break;
			case CONVERSATION_HOST:
				bundle = msg.getData();
				mConversations.add("Me : " + bundle.getString("data"));
				mAdapter.notifyDataSetChanged();
				mConversationView.setSelection(mAdapter.getCount());
				break;
			case CONVERSATION_JARVIS:
				bundle = msg.getData();
				mConversations.add("Vickie : " + bundle.getString("data"));
				mTtsManager.start(bundle.getString("data"));
				mAdapter.notifyDataSetChanged();
				mConversationView.setSelection(mAdapter.getCount());
				break;
			case LOGIN_OK:
				setContentView(R.layout.activity_voice_main);
				mainViewLoad();
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
	private class StateListener extends PhoneStateListener {
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
					NetState.getLocalIpAddress(UnityPlayerActivity.this));
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

	void startMapComponent() {
		mBdMapView = new BDMapView(this);
		mLocationReceiver = new LocationReceiver(mBdMapView);
		GpsServiceManager.startGpsService(this);
		GpsServiceManager.registerBorcastReceiver(this, mLocationReceiver);
		mLayout.setVisibility(View.VISIBLE);
		mLayout.addView(mBdMapView);
	}
	
	void startWebView(){
		myWebView = new MyWebView(this);
		mLayout.setVisibility(View.VISIBLE);
		mLayout.addView(myWebView);
		RequestOptions mOptions = new RequestOptions();
		String mUrl = "http://baidu.com";
		mOptions.setmUrl(mUrl);
		myWebView.loadPager(mOptions);
	}

	void stopMapComponent() {
		GpsServiceManager.unregisterBorcastReceiver(this, mLocationReceiver);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		if(isLogined){
			if(mExplore != null && !mExplore.onBackPressed()){
				return;
			}
			if(mBdMapView != null && !mBdMapView.onBackPressed()){
				return;
			}
			if(myWebView != null && !myWebView.onBackPressed()){
				return;
			}
		}		
	}

	// Quit Unity
	@Override protected void onDestroy ()
	{
		if(mUnityPlayer != null){
			mUnityPlayer.quit();
		}	
		if(isLogined){
			unregisterReceiver();
			LoaderEngine mEngine = LoaderEngine.get();
			if (mEngine != null) {
				LoaderEngine.get().setStop(true);
			}
			if(mBdMapView != null){
				stopMapComponent();
			}
		}		
		super.onDestroy();
	}

	// Pause Unity
	@Override protected void onPause()
	{
		super.onPause();
		if(mUnityPlayer != null){
			mUnityPlayer.pause();
		}		
	}

	// Resume Unity
	@Override protected void onResume()
	{
		super.onResume();
		if(mUnityPlayer != null){
			mUnityPlayer.resume();
		}		
		if(mTel != null){
			mTel.listen(mStateListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
		}		
	}

	// This ensures the layout will be correct.
	@Override public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		mUnityPlayer.configurationChanged(newConfig);
	}

	// Notify Unity of the focus change.
	@Override public void onWindowFocusChanged(boolean hasFocus)
	{
		super.onWindowFocusChanged(hasFocus);
		mUnityPlayer.windowFocusChanged(hasFocus);
	}

	// For some reason the multiple keyevent type is not supported by the ndk.
	// Force event injection by overriding dispatchKeyEvent().
	@Override public boolean dispatchKeyEvent(KeyEvent event)
	{
		if (event.getAction() == KeyEvent.ACTION_MULTIPLE)
			return mUnityPlayer.injectEvent(event);
		return super.dispatchKeyEvent(event);
	}

	// Pass any events not handled by (unfocused) views straight to UnityPlayer
	@Override public boolean onKeyUp(int keyCode, KeyEvent event)     { return mUnityPlayer.injectEvent(event); }
	@Override public boolean onKeyDown(int keyCode, KeyEvent event)   { return mUnityPlayer.injectEvent(event); }
	@Override public boolean onTouchEvent(MotionEvent event)          { return mUnityPlayer.injectEvent(event); }
	/*API12*/ public boolean onGenericMotionEvent(MotionEvent event)  { return mUnityPlayer.injectEvent(event); }
	/**
	 * 打电话
	 * @param phoneNumber
	 */
	protected void makeCall(String phoneNumber) {	}
	/**
	 * 发短信
	 * @param phoneNumber
	 * @param content
	 */
	protected void sendMessage(String phoneNumber, String content) {	}
	/**
	 * 朗读短信
	 */
	protected void messageSpeech() {	}
	/**
	 * 打开文件浏览器
	 */
	protected void openFileExplore() {	 }
	/**
	 * 打开地图
	 */
	protected void openMap(){	}
	/**
	 * 锁屏
	 */
	protected void lockScreen(){	}
	/**
	 * 解锁屏幕
	 */
	protected void unlockScreen(){		}
	/**
	 * 设置静音
	 */
	protected void setSilent(){		}
	/**
	 * 加大声音
	 */
	protected void setVolumeUp(){	}
	/**
	 * 降低声音
	 */
	protected void setVolumeDown(){		}
	/**
	 * 天气提醒
	 */
	protected void noticeWeather(){		}
	/**
	 * 时间提醒
	 */
	protected void noticeTime(){	}
	/**
	 * 日期提醒
	 */
	protected void noticeDate(){	}
	/**
	 * 温度提醒
	 */
	protected void noticeTemperature(){		}
	/**
	 * 跳转至
	 */
	protected void turnTo(){	}
	/**
	 * 打开指定app
	 */
	protected void openAppSpecified(){		}
	/**
	 * 打开电话录音
	 */
	protected void turnOnPhoneRecord(){		}
	/**
	 * 关闭电话录音
	 */
	protected void turnOffPhoneRecord(){		}
	
}
