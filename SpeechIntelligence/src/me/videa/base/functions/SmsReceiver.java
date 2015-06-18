package me.videa.base.functions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;


public class SmsReceiver extends BroadcastReceiver {
	
	SmsReceiverCallback smsReceiverCallback;
	
	public void setSmsReceiverCallback(SmsReceiverCallback smsReceiverCallback){
		this.smsReceiverCallback = smsReceiverCallback;
	}

	public SmsReceiver() {
		// TODO Auto-generated constructor stub		
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		// 判断是系统短信；
		HashMap<String, String> contentHashMap = null;
		if (intent.getAction()
				.equals("android.provider.Telephony.SMS_RECEIVED")) {
			// 不再往下传播；
			this.abortBroadcast();
			contentHashMap = new HashMap<String, String>();
			String sendtime = null;
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				// 通过pdus获得接收到的所有短信消息，获取短信内容；
				Object[] pdus = (Object[]) bundle.get("pdus");
				// 构建短信对象数组；
				SmsMessage[] mges = new SmsMessage[pdus.length];
				for (int i = 0; i < pdus.length; i++) {
					// 获取单条短信内容，以pdu格式存,并生成短信对象；
					mges[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
				}
				for (SmsMessage mge : mges) {
					contentHashMap.put("address", mge.getDisplayOriginatingAddress());
					contentHashMap.put("conten", mge.getMessageBody());
					contentHashMap.put("sender", mge.getDisplayOriginatingAddress());
					Date date = new Date(mge.getTimestampMillis());
					SimpleDateFormat format = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss", Locale.CHINA);
					sendtime = format.format(date);
					contentHashMap.put("time", sendtime);
					// 获取短信发送时间；
					// SmsManager manager = SmsManager.getDefault();
					// manager.sendTextMessage("5556",
					// null,"发送人:"+sender+"-----发送时间:"+sendtime+"----内容:"+content,
					// null, null);//把拦截到的短信发送到指定的手机，此处为5556;
					// if ("5556".equals(sender)){
					// //屏蔽手机号为5556的短信，这里还可以时行一些处理，如把该信息发送到第三人的手机等等。
					// abortBroadcast();
					// }
				}
			}
			smsReceiverCallback.smsCallback(contentHashMap);
		}
	}

}
