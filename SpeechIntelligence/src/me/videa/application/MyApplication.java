package me.videa.application;

import me.videa.uncaughtexceptionhandler.UncaughtException;
import me.videa.voice.R;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;

import com.baidu.mapapi.SDKInitializer;
import com.iflytek.cloud.SpeechUtility;

public class MyApplication extends Application {

	public static MyApplication mApplication;
	protected static SharedPreferences sp;
	public static int mScreenWidth;
	public static int mScreenHeight;

	private final static String mConfigName = "config";
	private static boolean m_bKeyRight = true;

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
		SpeechUtility.createUtility(MyApplication.this, "appid="
				+ getString(R.string.app_id));//加载语音appid
		SDKInitializer.initialize(getApplicationContext());  
		initConfig();
		UncaughtException mUncaughtException = UncaughtException.getInstance();
		mUncaughtException.init();
	}


	public void writeConfig() {
		// TODO Auto-generated method stub

	}

	public void initConfig() {
		// TODO Auto-generated method stub
		DisplayMetrics dm = getResources().getDisplayMetrics();
		mScreenWidth = dm.widthPixels;
		mScreenHeight = dm.heightPixels;
	}

	protected static SharedPreferences getSharedPreferences() {
		return mContext.getSharedPreferences(mConfigName, 0);

	}
}
