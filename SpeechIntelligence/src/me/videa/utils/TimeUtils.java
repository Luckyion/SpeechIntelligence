package me.videa.utils;

import java.util.Calendar;
import java.util.Locale;

import me.videa.voice.show.TimeBean;


public class TimeUtils {

	public static String getWeek(int week) {
		String wk = "MONDAY";
		switch (week) {
		case 1:
			wk = "SUNDAY";
			System.out.println("星期日");
			break;
		case 2:
			wk = "MONDAY";
			System.out.println("星期一");
			break;
		case 3:
			wk = "TUESDAY";
			System.out.println("星期二");
			break;
		case 4:
			wk = "WEDNESDAY";
			System.out.println("星期三");
			break;
		case 5:
			wk = "THURSDAY";
			System.out.println("星期四");
			break;
		case 6:
			wk = "FRIDAY";
			System.out.println("星期五");
			break;
		case 7:
			wk = "SATURDAY";
			System.out.println("星期六");
			break;
		case 8:
			wk = "SUNDAY";
			System.out.println("星期日");
			break;
		}
		return wk;
	}
	
	public static String getMonth(int month){
		String m = "January";
		switch (month) {
		case 0:
			m = "January";
			break;
		case 1:
			m = "February";
			break;
		case 2:
			m = "March";
			break;
		case 3:
			m = "April";
			break;
		case 4:
			m = "May";
			break;
		case 5:
			m = "June";
			break;
		case 6:
			m = "July";
			break;
		case 7:
			m = "August";
			break;
		case 8:
			m = "September";
			break;
		case 9:
			m = "October";
			break;
		case 10:
			m = "November";
			break;
		case 11:
			m = "December";
			break;
		}
		return m;
	}
	
	/**
	 * 获取时间和日期相关的信息对象
	 * @return
	 */
	public static TimeBean getTimeBean(){
		TimeBean mBean = new TimeBean();
		mBean.setMonth(Calendar.getInstance(Locale.CHINA).get(Calendar.MONTH));
		mBean.setWeek(Calendar.getInstance(Locale.CHINA).get(Calendar.WEEK_OF_MONTH));
		mBean.setDay(Calendar.getInstance(Locale.CHINA).get(Calendar.DAY_OF_MONTH));
		mBean.setHour(Calendar.getInstance(Locale.CHINA).get(Calendar.HOUR_OF_DAY));
		mBean.setMinite(Calendar.getInstance(Locale.CHINA).get(Calendar.MINUTE));
		return mBean;
	}
	
	/**
	 * 将小于10的分钟数前面补0
	 * @param minute
	 * @return
	 */
	public static String getMinute(int minute){
		String m = "0";
		if(minute < 10){
			m = m + minute;
		}else {
			m = minute + "";
		}
		return m;
	}

}
