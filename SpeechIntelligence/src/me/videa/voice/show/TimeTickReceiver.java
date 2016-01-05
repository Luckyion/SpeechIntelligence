package me.videa.voice.show;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TimeTickReceiver extends BroadcastReceiver {
	
	private boolean flag;

	@Override
	public void onReceive(Context context, Intent intent) {
//		System.out.println("时间变了" + intent.getAction());

		if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) {
			Log.d("TimeTickReceiver", "接收到时间变化了");
			 	//每过一分钟 触发
			} else{
			/*
			 * 系统bug??
			 * android.intent.action.TIME_SET  当调整系统时间后 这个action会收到两次
			 */
			if (flag) {
				try {
					/*  do some thing */
				} catch (Exception e) {
					e.printStackTrace();
				}
				flag = false; //第二次置false
			} else {
				flag = true; //第一次置true
			}
			
		}
		
	
		
	}

}