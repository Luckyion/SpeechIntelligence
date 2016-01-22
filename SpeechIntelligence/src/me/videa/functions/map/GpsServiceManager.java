package me.videa.functions.map;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class GpsServiceManager {
	/**
	 * 启动Gps服务
	 */
	public static void startGpsService(Context context){
		Intent service = new Intent(context, GpsService.class);
		context.startService(service);
	}
	
	/**
	 * 停止Gps服务
	 */
	public static void stopGpsService(Context context){
		Intent service = new Intent(context, GpsService.class);
		context.stopService(service);
	}

	
	/**
	 * 注册Gps服务接收广播
	 */
	public static void registerBorcastReceiver(Context mContext, LocationReceiver receiver) {
		// TODO Auto-generated method stub
		IntentFilter intentFilter = new IntentFilter(
				GpsService.GPS_MSG);
		mContext.registerReceiver(receiver, intentFilter);
	}
	
	/**
	 * 反注册Gps服务接收广播
	 */
	public static void unregisterBorcastReceiver(Context mContext, LocationReceiver receiver){
		mContext.unregisterReceiver(receiver);
	}

}
