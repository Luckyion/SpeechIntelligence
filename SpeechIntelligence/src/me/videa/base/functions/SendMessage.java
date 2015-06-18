package me.videa.base.functions;

import android.content.Context;
import android.telephony.SmsManager;

public class SendMessage {

	Context mContext;
	
	public SendMessage(Context context) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
	}
	
	/**
	 * 发送消息给指定号码
	 * @param phoneNumber
	 * @param message
	 */
	public void sendMessage(String phoneNumber, String message){
		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(phoneNumber, null, message, null, null);
	}
	
	/**
	 * 向多人发送同一条信息
	 * @param phoneNumbers
	 * @param message
	 */
	public void sendMessages(String[] phoneNumbers, String message){
		for(String phone : phoneNumbers){
			sendMessage(phone, message);
		}
	}
	
}
