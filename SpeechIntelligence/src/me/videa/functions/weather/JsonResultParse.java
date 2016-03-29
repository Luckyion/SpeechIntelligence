package me.videa.functions.weather;

import org.json.JSONObject;

import android.util.Log;

public class JsonResultParse {
	public static void jsonResultParse(JSONObject rtn){
		try{
			WeatherData wd = new WeatherData();
			wd.setCity(rtn.getJSONObject("weatherinfo").getString("city"));
			wd.setCityid(rtn.getJSONObject("weatherinfo").getString("cityid"));
			wd.setTemp(rtn.getJSONObject("weatherinfo").getString("temp"));
			wd.setWD(rtn.getJSONObject("weatherinfo").getString("WD"));
			wd.setWS(rtn.getJSONObject("weatherinfo").getString("WS"));
			wd.setSD(rtn.getJSONObject("weatherinfo").getString("SD"));
			wd.setWSE(rtn.getJSONObject("weatherinfo").getString("WSE"));
			wd.setTime(rtn.getJSONObject("weatherinfo").getString("time"));
			wd.setIsReader(rtn.getJSONObject("weatherinfo").getString("isRadar"));
			wd.setRadar(rtn.getJSONObject("weatherinfo").getString("Radar"));	
			Log.d("city", wd.getCity());
			Log.d("cityid", wd.getCityid());
			Log.d("temp", wd.getTemp());
			Log.d("wd", wd.getWD());
			Log.d("ws", wd.getWS());
			Log.d("wse", wd.getWSE());
			Log.d("time", wd.getTime());
			Log.d("isReader", wd.getIsReader());
			Log.d("radar", wd.getRadar());
		}catch(Exception e){
			Log.d("Exception", e.getMessage());
		}		
		
	}
	public static void jsonResultParseAll(JSONObject json){
		WeatherDataAll wda = new WeatherDataAll();
		try{
			wda.setCity(json.getJSONObject("weatherinfo").getString("city"));
			wda.setCity_en(json.getJSONObject("weatherinfo").getString("city_en"));
			wda.setDate_y(json.getJSONObject("weatherinfo").getString("date_y"));
			wda.setData(json.getJSONObject("weatherinfo").getString("date"));
			wda.setWeek(json.getJSONObject("weatherinfo").getString("week"));
			wda.setFchh(json.getJSONObject("weatherinfo").getString("fchh"));
			wda.setTemp1(json.getJSONObject("weatherinfo").getString("temp1"));
			wda.setTemp2(json.getJSONObject("weatherinfo").getString("temp2"));
			wda.setTemp3(json.getJSONObject("weatherinfo").getString("temp3"));
			wda.setTemp4(json.getJSONObject("weatherinfo").getString("temp4"));
			wda.setTemp5(json.getJSONObject("weatherinfo").getString("temp5"));
			wda.setTemp6(json.getJSONObject("weatherinfo").getString("temp6"));
			wda.setTempF1(json.getJSONObject("weatherinfo").getString("tempF1"));
			wda.setTempF2(json.getJSONObject("weatherinfo").getString("tempF2"));
			wda.setTempF3(json.getJSONObject("weatherinfo").getString("tempF3"));
			wda.setTempF4(json.getJSONObject("weatherinfo").getString("tempF4"));
			wda.setTempF5(json.getJSONObject("weatherinfo").getString("tempF5"));
			wda.setTempF6(json.getJSONObject("weatherinfo").getString("tempF6"));
			wda.setWeather1(json.getJSONObject("weatherinfo").getString("weather1"));
			wda.setWeather2(json.getJSONObject("weatherinfo").getString("weather2"));
			wda.setWeather3(json.getJSONObject("weatherinfo").getString("weather3"));
			wda.setWeather4(json.getJSONObject("weatherinfo").getString("weather4"));
			wda.setWeather5(json.getJSONObject("weatherinfo").getString("weather5"));
			wda.setWeather6(json.getJSONObject("weatherinfo").getString("weather6"));
			wda.setImg1(json.getJSONObject("weatherinfo").getString("img1"));
			wda.setImg2(json.getJSONObject("weatherinfo").getString("img2"));
			wda.setImg3(json.getJSONObject("weatherinfo").getString("img3"));
			wda.setImg4(json.getJSONObject("weatherinfo").getString("img4"));
			wda.setImg5(json.getJSONObject("weatherinfo").getString("img5"));
			wda.setImg6(json.getJSONObject("weatherinfo").getString("img6"));
			wda.setImg7(json.getJSONObject("weatherinfo").getString("img7"));
			wda.setImg8(json.getJSONObject("weatherinfo").getString("img8"));
			wda.setImg9(json.getJSONObject("weatherinfo").getString("img9"));
			wda.setImg10(json.getJSONObject("weatherinfo").getString("img10"));
			wda.setImg11(json.getJSONObject("weatherinfo").getString("img11"));
			wda.setImg_title_single(json.getJSONObject("weatherinfo").getString("img_single"));
			wda.setImg_title1(json.getJSONObject("weatherinfo").getString("img_title1"));
			wda.setImg_title2(json.getJSONObject("weatherinfo").getString("img_title2"));
			wda.setImg_title3(json.getJSONObject("weatherinfo").getString("img_title3"));
			wda.setImg_title4(json.getJSONObject("weatherinfo").getString("img_title4"));
			wda.setImg_title5(json.getJSONObject("weatherinfo").getString("img_title5"));
			wda.setImg_title6(json.getJSONObject("weatherinfo").getString("img_title6"));
			wda.setImg_title7(json.getJSONObject("weatherinfo").getString("img_title7"));
			wda.setImg_title8(json.getJSONObject("weatherinfo").getString("img_title8"));
			wda.setImg_title9(json.getJSONObject("weatherinfo").getString("img_title9"));
			wda.setImg_title10(json.getJSONObject("weatherinfo").getString("img_title10"));
			wda.setImg_title11(json.getJSONObject("weatherinfo").getString("img_title11"));
			wda.setImg_title12(json.getJSONObject("weatherinfo").getString("img_title12"));
			wda.setWind1(json.getJSONObject("weatherinfo").getString("wind1"));
			wda.setWind2(json.getJSONObject("weatherinfo").getString("wind2"));
			wda.setWind3(json.getJSONObject("weatherinfo").getString("wind3"));
			wda.setWind4(json.getJSONObject("weatherinfo").getString("wind4"));
			wda.setWind5(json.getJSONObject("weatherinfo").getString("wind5"));
			wda.setWind6(json.getJSONObject("weatherinfo").getString("wind6"));
			wda.setFx1(json.getJSONObject("weatherinfo").getString("fx1"));
			wda.setFx2(json.getJSONObject("weatherinfo").getString("fx2"));
			wda.setFl1(json.getJSONObject("weatherinfo").getString("fl1"));
			wda.setFl2(json.getJSONObject("weatherinfo").getString("fl2"));
			wda.setFl3(json.getJSONObject("weatherinfo").getString("fl3"));
			wda.setFl4(json.getJSONObject("weatherinfo").getString("fl4"));
			wda.setFl5(json.getJSONObject("weatherinfo").getString("fl5"));
			wda.setFl6(json.getJSONObject("weatherinfo").getString("fl6"));
			wda.setIndex(json.getJSONObject("weatherinfo").getString("index"));
			wda.setIndex_d(json.getJSONObject("weatherinfo").getString("index_d"));
			wda.setIndex_uv(json.getJSONObject("weatherinfo").getString("index_uv"));
			wda.setIndex48(json.getJSONObject("weatherinfo").getString("index48"));
			wda.setIndex48_d(json.getJSONObject("weatherinfo").getString("index48_d"));
			wda.setIndex48_uv(json.getJSONObject("weatherinfo").getString("index48_uv"));
			wda.setIndex_xc(json.getJSONObject("weatherinfo").getString("index_xc"));
			wda.setIndex_tr(json.getJSONObject("weatherinfo").getString("index_tr"));
			wda.setIndex_co(json.getJSONObject("weatherinfo").getString("index_co"));
			wda.setIndex_ag(json.getJSONObject("weatherinfo").getString("index_ag"));
			wda.setIndex_cl(json.getJSONObject("weatherinfo").getString("index_cl"));
			wda.setIndex_ls(json.getJSONObject("weatherinfo").getString("index_ls"));
			wda.setSt1(json.getJSONObject("weatherinfo").getString("st1"));
			wda.setSt2(json.getJSONObject("weatherinfo").getString("st2"));
			wda.setSt3(json.getJSONObject("weatherinfo").getString("st3"));
			wda.setSt4(json.getJSONObject("weatherinfo").getString("st4"));
			wda.setSt5(json.getJSONObject("weatherinfo").getString("st5"));
			wda.setSt6(json.getJSONObject("weatherinfo").getString("st6"));
//			Log.d("getWeather1", wda.getWeather1());
		}catch(Exception e){
			Log.d("Exception", e.getMessage());
		}		
	}
}
