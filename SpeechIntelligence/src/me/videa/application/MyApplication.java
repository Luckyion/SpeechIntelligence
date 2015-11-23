package me.videa.application;

import me.videa.uncaughtexceptionhandler.UncaughtException;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class MyApplication extends Application implements GlobalMethod{
	
	public static MyApplication application;
	protected static SharedPreferences sp;
	
	private final static String mConfigName = "config";
	
	static Context mContext;
	
	public MyApplication() {
		// TODO Auto-generated constructor stub
	}
	
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        application = this;
        mContext = this.getApplicationContext();
        UncaughtException mUncaughtException = UncaughtException.getInstance();
        mUncaughtException.init();
    }

	@Override
	public void loadConfig() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeConfig() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initConfig() {
		// TODO Auto-generated method stub
		
	}
	
	protected static SharedPreferences getSharedPreferences() {
		return mContext.getSharedPreferences(mConfigName, 0);
		
	}
}
