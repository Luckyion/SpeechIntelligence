package me.videa.voice.show;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

//服务 用于  注册 监听时间变化、设置的广播   时间变化广播只能动态注册
public class TestActivity extends Activity {
	
	//监听时间变化的 这个receiver只能动态创建
	private TimeTickReceiver mTickReceiver;
	private IntentFilter mFilter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mFilter = new IntentFilter();
		mFilter.addAction(Intent.ACTION_TIME_TICK); //每分钟变化的action
		mFilter.addAction(Intent.ACTION_TIME_CHANGED); //设置了系统时间的action
		mTickReceiver = new TimeTickReceiver();
		registerReceiver(mTickReceiver, mFilter); 
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mTickReceiver);
	}
}