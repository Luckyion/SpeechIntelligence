package me.videa.base.functions;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class GetInstalledApplications {
	
	
	Context mContext;
	
	public GetInstalledApplications(Context context) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
	}
	
	
	/**
	 * 获取本地已安装的非系统应用的名称及包名
	 * @return HashMap   <应用名称, 包名>
	 */
	public HashMap<String, String> getInstalledPackages(){
		PackageManager packageManager = mContext.getPackageManager();
		List<PackageInfo> list = packageManager.getInstalledPackages(0);
		HashMap<String, String> appName_packageName = new HashMap<String, String>();
		for(PackageInfo info : list){			
			ApplicationInfo applicationInfo = info.applicationInfo;
			//packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
			if((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0){
				System.out.println("appName:" + applicationInfo.loadLabel(packageManager)
						+ "package:" + info.packageName + "versionName: "
						+ info.versionName + "versionCode" + info.versionCode);
				appName_packageName.put(applicationInfo.loadLabel(packageManager).toString(),
						info.packageName);
			}
		}
		return appName_packageName;
	}
	
	

}
