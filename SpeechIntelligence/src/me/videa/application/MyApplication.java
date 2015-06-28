package me.videa.application;

import me.videa.uncaughtexceptionhandler.UncaughtException;
import android.app.Application;
import android.content.Context;

public class MyApplication extends Application implements GlobalMethod{
	
	public static MyApplication application;
	
	Context mContext;
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
}
