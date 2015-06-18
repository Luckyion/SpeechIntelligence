package me.videa.base.functions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class MakeCall {
	
	
	Context mContext;
	
	public MakeCall(Context context) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
	}
	
	/**
	 * 拨打指定号码
	 * @param phoneNumber
	 */
	public void makeCall(String phoneNumber){
		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
		mContext.startActivity(intent);
	}

}
