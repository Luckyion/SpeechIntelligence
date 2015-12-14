package me.videa.voice.show;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class NetState {
	
	  /** 
     * 灏唅p鐨勬暣鏁板舰寮忚浆鎹㈡垚ip褰㈠紡 
     *  
     * @param ipInt 
     * @return 
     */  
    public static String int2ip(int ipInt) {  
        StringBuilder sb = new StringBuilder();  
        sb.append(ipInt & 0xFF).append(".");  
        sb.append((ipInt >> 8) & 0xFF).append(".");  
        sb.append((ipInt >> 16) & 0xFF).append(".");  
        sb.append((ipInt >> 24) & 0xFF);  
        return sb.toString();  
    }  
  
    /** 
     * 鑾峰彇褰撳墠ip鍦板潃 
     *  
     * @param context 
     * @return 
     */  
    public static String getLocalIpAddress(Context context) {  
        try {  
            WifiManager wifiManager = (WifiManager) context  
                    .getSystemService(Context.WIFI_SERVICE);  
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();  
            int i = wifiInfo.getIpAddress();  
            return int2ip(i);  
        } catch (Exception ex) {  
            return " 鑾峰彇IP鍑洪敊楦?!!!!璇蜂繚璇佹槸WIFI,鎴栬€呰ˉ烽噸鏂版墦寮€缃戠粶!\n" + ex.getMessage();  
        }  
        // return null;  
    } 

}

