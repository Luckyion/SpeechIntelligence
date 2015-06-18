package me.videa.base.functions;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

public class StartSpecificApplication {
	
	Context mContext;
	
	public StartSpecificApplication(Context context) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
	}
	
	/**
	 * 根据包名启动应用程序
	 * @param packageName 包名称
	 */
	public void startSpecificApplication(String packageName){
		Intent mIntent = new Intent();
		PackageManager packageManager = mContext.getPackageManager();
		packageManager.getLaunchIntentForPackage(packageName);
		mContext.startActivity(mIntent);
	}

}
