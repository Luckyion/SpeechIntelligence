package me.videa.base.actions;

import me.videa.base.functions.MakeCall;
import me.videa.base.functions.SendMessage;
import me.videa.functions.weather.GetCityCode;
import me.videa.functions.weather.Weather;
import me.videa.functions.weather.Weather.WeatherCallback;
import me.videa.functions.weather.WeatherData;
import me.videa.voice.show.VoiceMainActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;

public class BaseAction implements Actions{
	
	protected static BaseAction instance;
	protected Context mContext;
	
	/**
	 * 获取实例
	 * @return
	 */
	public static BaseAction get(Context context){		
		if(instance == null){
			instance = new BaseAction();
		}
		instance.mContext = context;
		return instance;
	}
	
	@Override
	public void makeCall(String phoneNumber) {
		// TODO Auto-generated method stub		
		MakeCall makeCall = new MakeCall(mContext);
		makeCall.makeCall(phoneNumber);		
	}
	
	@Override
	public void sendMessage(String phoneNumber, String content) {
		// TODO Auto-generated method stub
		SendMessage message = new SendMessage(mContext);
		message.sendMessage(phoneNumber, content);
	}
	
	@Override
	public void lockScreen() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void noticeWeather() {
		// TODO Auto-generated method stub
		new Weather(new WeatherCallback() {
			
			@Override
			public void callBack(WeatherData data) {
				// TODO Auto-generated method stub
				Message message = new Message();
				message.what = WEATHER;
				Bundle mBundle = new Bundle();
				mBundle.putSerializable("weather", data);
				message.setData(mBundle);
				VoiceMainActivity.mHandler.sendMessage(message);
			}
		}, new GetCityCode(mContext).getCityCode("南京"), mContext).execute();
	}

	@Override
	public void messageSpeech() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openFileExplore() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openMap() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unlockScreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSilent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVolumeUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVolumeDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void noticeTime() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void noticeDate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void noticeTemperature() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnTo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openAppSpecified() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnOnPhoneRecord() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnOffPhoneRecord() {
		// TODO Auto-generated method stub
		
	}
	

}
