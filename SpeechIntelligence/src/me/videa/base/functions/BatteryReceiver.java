package me.videa.base.functions;

import me.videa.voice.show.HandlerWhat;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class BatteryReceiver extends BroadcastReceiver {
	
	private Handler mHandler;
	
	public BatteryReceiver(Handler handler){
		this.mHandler = handler;
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		// 判断它是否是为电量变化的Broadcast Action
		if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
			// 获取当前电量
			int level = intent.getIntExtra("level", 0);
			// 电量的总刻度
			int scale = intent.getIntExtra("scale", 100);
			Bundle mBundle = new Bundle();
			mBundle.putInt("level", level);
			mBundle.putInt("scale", scale);
			Message message = new Message();
			message.setData(mBundle);
			message.what = HandlerWhat.BATTERY_STATE;
			mHandler.sendMessage(message);
		}
	}
}
