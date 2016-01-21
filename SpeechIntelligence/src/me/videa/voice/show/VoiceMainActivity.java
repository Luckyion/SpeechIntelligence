package me.videa.voice.show;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.videa.base.functions.BatteryReceiver;
import me.videa.base.functions.DateTimeReceiver;
import me.videa.effects.MainShowView;
import me.videa.functions.local.FileExplore;
import me.videa.functions.local.SelectorResult;
import me.videa.functions.novelloader.LoaderEngine;
import me.videa.functions.novelloader.NovelShow;
import me.videa.utils.TimeUtils;
import me.videa.voice.R;
import me.videa.voice.service.SpeechIntelligence;
import me.videa.voice.show.beans.ConversationAdapter;
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
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
	@ViewInject(R.id.conversation)
	private ListView mConversationView;
	@ViewInject(R.id.viewContainer)
	private RelativeLayout mLayout;
	private ConversationAdapter mAdapter;
	private List<String> mConversations;
	private FileExplore mExplore;
	private NovelShow mNovelShow;
//	private BDMapView mBdMapView;

	static int counter = 0;

	/******** 语音合成 ********/

	private TTSManager mTtsManager;
	private RecognitionManager mRecognitionManager;

	/******************************************/
	private Toast mToast;

	@SuppressLint("ShowToast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		ViewUtils.inject(this);
		mContext = this;
		mHandler = new ViewHandler();
		mStateListener = new StateListener();
		mTel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		registerBatteryReceiver();
		registerDateTimeReceiver();
		iniDateAndTime();
		mTtsManager = TTSManager.initManager(this, mHandler, mVoiceView);
		// mRecognitionManager = new RecognitionManager(this, mHandler,
		// mVoiceView);//启动语音识别
		mConversations = new ArrayList<String>();
		mAdapter = new ConversationAdapter(this, mConversations);
		mConversationView.setAdapter(mAdapter);
		mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
		
		/**************************/
//		Intent mIntent  = new Intent(this, LandmarkActivity.class);
//		startActivity(mIntent);
//		mBdMapView = new BDMapView(this);
//		mLayout.setVisibility(View.VISIBLE);
//		mLayout.addView(mBdMapView);
	}

	private void initViews() {
		mNovelShow = new NovelShow(this, new SelectorResult() {

			@Override
			public void setResult(Bundle data) {
				// TODO Auto-generated method stub
				Thread mThread = LoaderEngine.getLoaderEngine(mContext,
						mHandler, data.getString("data"));
				mThread.start();
				mNovelShow.setVisibility(View.GONE);
			}
		});
		
		// BaseActionAnalysis mActionAnalysis = new BaseActionAnalysis(this);
		// mActionAnalysis.AnalyseAction("发短信给测试");
		/*
		 * mExplore = new FileExplore(this, new SelectorResult() {
		 * 
		 * @Override public void setResult(Bundle data) { // TODO Auto-generated
		 * method stub String path = data.getString("data"); Log.d(TAG, path); }
		 * }, null); mLayout.setVisibility(View.VISIBLE);
		 * mLayout.addView(mExplore);
		 */
	}

	@Override
	protected void onResume() {
		mTel.listen(mStateListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver();
		LoaderEngine mEngine = LoaderEngine.get();
		if (mEngine != null) {
			LoaderEngine.get().setStop(true);
		}
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		// mExplore.onBackPressed();
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
			case TTS_STATE_INIT:
				// testTimer();
				break;
			case CONVERSATION_HOST:
				bundle = msg.getData();
				mConversations.add("Me : " + bundle.getString("data"));
				mAdapter.notifyDataSetChanged();
				mConversationView.setSelection(mAdapter.getCount());
				break;
			case CONVERSATION_VICKIE:
				bundle = msg.getData();
				mConversations.add("Vickie : " + bundle.getString("data"));
				mTtsManager.start(bundle.getString("data"));
				mAdapter.notifyDataSetChanged();
				mConversationView.setSelection(mAdapter.getCount());
				break;

			default:
				break;
			}
			super.dispatchMessage(msg);
		}
	}

	private void testTimer() {

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg = new Message();
				Bundle mBundle = new Bundle();
				mBundle.putString("data", counter + "");
				msg.setData(mBundle);
				if (counter % 2 == 0) {
					msg.what = HandlerWhat.CONVERSATION_VICKIE;
				} else {
					msg.what = HandlerWhat.CONVERSATION_HOST;
				}

				mHandler.sendMessage(msg);
				counter++;
			}
		}, 0, 5 * 1000);
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
