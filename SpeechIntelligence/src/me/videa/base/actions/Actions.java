package me.videa.base.actions;

import me.videa.voice.show.HandlerWhat;

public interface Actions extends HandlerWhat{
	
	/**
	 * 打电话
	 * @param phoneNumber
	 */
	void makeCall(String phoneNumber);
	/**
	 * 发短信
	 * @param phoneNumber
	 * @param content
	 */
	void sendMessage(String phoneNumber, String content);
	/**
	 * 朗读短信
	 */
	void messageSpeech();
	/**
	 * 打开文件浏览器
	 */
	void openFileExplore();
	/**
	 * 打开地图
	 */
	void openMap();
	/**
	 * 锁屏
	 */
	void lockScreen();
	/**
	 * 解锁屏幕
	 */
	void unlockScreen();
	/**
	 * 设置静音
	 */
	void setSilent();
	/**
	 * 加大声音
	 */
	void setVolumeUp();
	/**
	 * 降低声音
	 */
	void setVolumeDown();
	/**
	 * 天气提醒
	 */
	void noticeWeather();
	/**
	 * 时间提醒
	 */
	void noticeTime();
	/**
	 * 日期提醒
	 */
	void noticeDate();
	/**
	 * 温度提醒
	 */
	void noticeTemperature();
	/**
	 * 跳转至
	 */
	void turnTo();
	/**
	 * 打开指定app
	 */
	void openAppSpecified();
	/**
	 * 打开电话录音
	 */
	void turnOnPhoneRecord();
	/**
	 * 关闭电话录音
	 */
	void turnOffPhoneRecord();

}
