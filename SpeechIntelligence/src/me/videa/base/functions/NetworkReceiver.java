package me.videa.base.functions;

import me.videa.application.MyApplication;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkReceiver extends BroadcastReceiver{
	
	private ConnectivityManager mConnectivityManager; 
	   
	 private NetworkInfo netInfo; 
	 
	 public NetworkReceiver mNetworkReceiver;
	 
	 Context mContext;
	 
	 public NetworkReceiver(Context context) {
		 mContext = context;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getAction(); 
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) { 
              
            mConnectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE); 
            netInfo = mConnectivityManager.getActiveNetworkInfo();   
            if(netInfo != null && netInfo.isAvailable()) { 
                if(netInfo.getType()==ConnectivityManager.TYPE_WIFI){ 
                 /////WiFi网络 
                	MyApplication.mNetworkInfo = ConnectivityManager.TYPE_WIFI;
                }else if(netInfo.getType()==ConnectivityManager.TYPE_ETHERNET){ 
                /////有线网络 
                	
                }else if(netInfo.getType()==ConnectivityManager.TYPE_MOBILE){ 
               /////////3g网络 
                	MyApplication.mNetworkInfo = ConnectivityManager.TYPE_MOBILE;
                } 
              } else { 
             ////////网络断开 
   
            } 
        } 
   
   } 
	
	public void registerNetworkReceiver(){
       IntentFilter mFilter = new IntentFilter(); 
       mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION); 
       mContext.registerReceiver(mNetworkReceiver, mFilter);   
      
	}
	
	public void unregisterNetworkReceiver(){
		if(mNetworkReceiver != null){ 
            mContext.unregisterReceiver(mNetworkReceiver); 
        }
	}

}
