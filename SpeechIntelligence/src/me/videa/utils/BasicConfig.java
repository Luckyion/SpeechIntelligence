package me.videa.utils;

import me.videa.application.MyApplication;


public class BasicConfig extends MyApplication{
	

	private final static String DEFAULT_IP = "127.0.0.1";
	
	private final static String DEFAULT_PORT = "8888";
	
	private final static String SEP = "/";
	
	public static final String IP = getSharedPreferences().getString("ip", DEFAULT_IP);
	
	public static final String PORT = getSharedPreferences().getString("port", DEFAULT_PORT);
	
	
	
	public static final String FILE_URL = IP + ":" + PORT + SEP + "RemoteFileLoader"; 

}
