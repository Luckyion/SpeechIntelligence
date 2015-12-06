package me.videa.application;

import com.iflytek.cloud.SpeechUtility;

import me.videa.uncaughtexceptionhandler.UncaughtException;
import me.videa.voice.R;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;

public class MyApplication extends Application {

	public static MyApplication mApplication;
	protected static SharedPreferences sp;

	private final static String mConfigName = "config";
	public static int mScreenWidth;
	public static int mScreenHeight;

	static Context mContext;

	public MyApplication() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mApplication = this;
		mContext = this.getApplicationContext();
		initConfig();
		// 应用程序入口处调用,避免手机内存过小,杀死后台进程后通过历史intent进入Activity造成SpeechUtility对象为null
		// 如在Application中调用初始化，需要在Mainifest中注册该Applicaiton
		// 注意：此接口在非主进程调用会返回null对象，如需在非主进程使用语音功能，请增加参数：SpeechConstant.FORCE_LOGIN+"=true"
		// 参数间使用“,”分隔。
		// 设置你申请的应用appid
		SpeechUtility
				.createUtility(this, "appid=" + getString(R.string.app_id));
		UncaughtException mUncaughtException = UncaughtException.getInstance();
		mUncaughtException.init();
	}

	void loadConfig() {

	}

	void initConfig() {
		DisplayMetrics dm = getResources().getDisplayMetrics();		   
		mScreenWidth = dm.widthPixels; 	
		mScreenHeight = dm.heightPixels;
	}

	protected static SharedPreferences getSharedPreferences() {
		return mContext.getSharedPreferences(mConfigName, 0);

	}
}
