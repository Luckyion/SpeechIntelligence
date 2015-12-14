package me.videa.uncaughtexceptionhandler;

import java.util.HashMap;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * 获取设备信息</br>
 * 调用时需要在{@link AndroidManifest.xml}中配置权限</br>
 * <uses-permission android:name="android.permission.READ_PHONE_STATE" </br>
 * @author Vickie Tang
 * @version 1.0
 *
 */
public class DeviceMessages {
	
	TelephonyManager telephonyManager;
	Context context;
	HashMap<String, String> deviceMessage;
	
	public DeviceMessages(Context context){
		this.context = context;
		deviceMessage = new HashMap<String, String>();
		telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);		
	}
	
	/**
	 * 获取设备信息
	 * 如手机号码、IMEI、运营商、SIM卡序列号等
	 * @return List<String> deviceMessages
	 */
	public HashMap<String, String> getDevicesMessages(){
		deviceMessage.put("imei", getDevID());
		deviceMessage.put("tel_no", getDevTelNo());
		deviceMessage.put("version", getSoftwareVersion());
		deviceMessage.put("sim_serial", telephonyManager.getSimSerialNumber());
		deviceMessage.put("sim_operator", telephonyManager.getSimOperatorName());
		deviceMessage.put("network_state", telephonyManager.getNetworkType() + "");
		deviceMessage.put("sim_state", telephonyManager.getSimState() + "");
		return deviceMessage;
	}
	
	/**
	 * 获取设备编号IMEI
	 * @return String IMEI
	 */
	private String getDevID(){
		return telephonyManager.getDeviceId();
	}
	
	/**
	 * 获取电话号码
	 * @param telephonyManager
	 * @return String phone number
	 */
	private String getDevTelNo(){
		return telephonyManager.getLine1Number();
	}
	
	private String getSoftwareVersion(){
		telephonyManager.getDeviceSoftwareVersion();
		return null;
	}

}
