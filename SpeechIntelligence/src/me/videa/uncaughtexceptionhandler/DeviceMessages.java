package me.videa.uncaughtexceptionhandler;

import java.util.HashMap;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * ��ȡ�豸��Ϣ</br>
 * ����ʱ��Ҫ��{@link AndroidManifest.xml}������Ȩ��</br>
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
	 * ��ȡ�豸��Ϣ
	 * ���ֻ����롢IMEI����Ӫ�̡�SIM�����кŵ�
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
	 * ��ȡ�豸���IMEI
	 * @return String IMEI
	 */
	private String getDevID(){
		return telephonyManager.getDeviceId();
	}
	
	/**
	 * ��ȡ�绰����
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
