package me.videa.voice.show;

import java.io.Serializable;

public class ExtraBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1996439640354076287L;

	private int singleStrength;
	
	private String ip;
	
	private int batteryLevel;
	
	private TimeBean timeBean;

	public int getSingleStrength() {
		return singleStrength;
	}

	public void setSingleStrength(int singleStrength) {
		this.singleStrength = singleStrength;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(int batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	public TimeBean getTimeBean() {
		return timeBean;
	}

	public void setTimeBean(TimeBean timeBean) {
		this.timeBean = timeBean;
	}
	
	

}

