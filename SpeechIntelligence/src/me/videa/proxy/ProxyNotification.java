package me.videa.proxy;

import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.iflytek.voicedemo.R;

public class ProxyNotification {
	
	private Context mContext;
	private NotificationManager mNotificationManager;
	
	public ProxyNotification(Context mContext) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
		mNotificationManager = (NotificationManager) mContext.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
	}
	
	@SuppressLint("NewApi")
	/**
	 * 发送Notification。
	 * @param clzz 点击后要跳转的Activity.
	 * @param proxyId 代理请求失败的proxyId.
	 * @param title Notification 标题
	 * @param message Notification 提示信息
	 * @param bundle  Bundle 数据
	 */
	public void sendNotifycation(Class<Object> clzz, String proxyId, String title, String message, Bundle bundle){
		Notification notification = new Notification();
		Intent intent = new Intent(mContext, clzz);
		intent.putExtra("notifyData", proxyId);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
		intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);				
		Builder mBuilder = new Notification.Builder(mContext);
		mBuilder.setContentTitle("您有一条新消息");
		mBuilder.setContentText(message);
		mBuilder.setAutoCancel(false);
		mBuilder.setDeleteIntent(contentIntent);
		mBuilder.setExtras(bundle);
		notification.defaults = Notification.DEFAULT_LIGHTS;
		notification.defaults |= Notification.DEFAULT_SOUND;
		notification.defaults |= Notification.DEFAULT_VIBRATE;
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.when = System.currentTimeMillis();
		mNotificationManager.notify("ProxyNotification", new Random().nextInt(), notification);	
	}


}
