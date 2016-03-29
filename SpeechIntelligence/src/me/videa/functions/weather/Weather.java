package me.videa.functions.weather;

import org.json.JSONObject;
import org.json.JSONTokener;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


/**
 * Weather forecast
 * @author	Luckyion
 *
 */
public class Weather extends AsyncTask<Void, Void, WeatherData>{
	
	private String mCityCode;
	private Context mContext;
	private WeatherCallback mCallback;
	
	public interface WeatherCallback{
		void callBack(WeatherData data);
	}
	
	public Weather(WeatherCallback callback, String cityCode, Context context){
		this.mCityCode = cityCode;
		this.mContext = context;	
		mCallback = callback;
	}	
	 
	/**
	 * getWeather
	 * @param cityCode
	 * @param isCurrent
	 */
	public WeatherData getWeather(String cityCode){	
		WeatherData mData = null;
		String siteUrl = "http://www.weather.com.cn/data/sk/" + cityCode + ".html";
		 String result = InfoTransmit.getResult(siteUrl);
		 Log.d("result", result);
		//�Է��������صĽ��ֵ���д��� response	
			try {
				JSONTokener jsonParser = new JSONTokener(result);
				JSONObject rtn = (JSONObject) jsonParser.nextValue();
				mData = new WeatherData();
				mData.setTemp(rtn.getJSONObject("weatherinfo").getString("temp"));
				mData.setWD(rtn.getJSONObject("weatherinfo").getString("WD"));
				mData.setWS(rtn.getJSONObject("weatherinfo").getString("WS"));
				mData.setSD(rtn.getJSONObject("weatherinfo").getString("SD"));
				mData.setWeather(rtn.getJSONObject("weatherinfo").getString("weather1"));
			} catch (Exception e) {
				e.printStackTrace();		
			}			
			return mData;
	 }

	@Override
	protected WeatherData doInBackground(Void... params) {
		// TODO Auto-generated method stub
		return getWeather(mCityCode);
	}
	
	@Override
	protected void onPostExecute(WeatherData result) {
		// TODO Auto-generated method stub
		mCallback.callBack(result);
		super.onPostExecute(result);
	}
}
