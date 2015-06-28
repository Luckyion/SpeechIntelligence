package me.videa.utils;

import android.content.Context;

public class BasicConfig {
	
	public static void setContext(Context context){
		mContext = context;
	}
	
	private static Context mContext;
	
	private final static String DEFAULT_IP = "127.0.0.1";
	
	private final static String DEFAULT_PORT = "8888";
	
	private final static String SEP = ":/";
	
	public static final String IP = SharedPreferencesUtil
			.getSharedPreferences(mContext).getString("ip", DEFAULT_IP);
	
	public static final String PORT = SharedPreferencesUtil
			.getSharedPreferences(mContext).getString("port", DEFAULT_PORT);
	
	
	
	public static final String FILE_URL = IP + PORT + SEP + "RemoteFileLoader"; 

}
