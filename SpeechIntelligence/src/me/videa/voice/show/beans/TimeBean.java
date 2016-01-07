package me.videa.voice.show.beans;

import java.io.Serializable;

public class TimeBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8164079464343109744L;

	private int month;
	
	private int week;
	
	private int day;
	
	private int hour;
	
	private int minite;

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinite() {
		return minite;
	}

	public void setMinite(int minite) {
		this.minite = minite;
	}

	
}
