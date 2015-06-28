package me.videa.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {
	private static final String mFileName = "config";
	
	private static SharedPreferences sp;
	
	private SharedPreferencesUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static SharedPreferences getSharedPreferences(Context mContext){
		if(sp == null){
			sp = mContext.getSharedPreferences(mFileName, 0);
		}
		return sp;
	}

}
