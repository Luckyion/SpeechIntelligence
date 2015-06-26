package me.videa.base.functions;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.drm.DrmStore.Action;
import android.net.Uri;

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
	
	/**
	 * 根据包名启动应用程序
	 * @param packageName 包名称
	 */
	public void startSpecificApplication(String pkg, String cls){
		Intent intentPhone = new Intent();  
        ComponentName comp = new ComponentName(pkg,  
        		cls);  
        intentPhone.setComponent(comp);  
        intentPhone.setAction("android.intent.action.VIEW");  
        intentPhone.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  
                | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);  
		mContext.startActivity(intentPhone);
	}

}
