package me.videa.functions.weather;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

/**
 * ��ȡcityCode
 * */
public class GetCityCode {
	private Context mContext;
	/***
	 * ���������ļ�
	 * @param fileName=�����ļ�������
	 * */
    public GetCityCode(Context context)
    {
    	this.mContext = context;
    }
    /**
     * ��ȡcityCode
     * @param city	��������
  ����*/
     public String getCityCode(String city) {

      BufferedReader bufferedReader = null;
      String strLine = "";
      try {    	  
		bufferedReader = new BufferedReader(
				new InputStreamReader(mContext.getAssets().open("Weather_CityCode"))); 
		while ((strLine = bufferedReader.readLine()) != null) {

		    	String[] str=strLine.split("=");
		        if(str.length>=2)
		    		 {
		    			if(str[1].equals(city))
		    			{
		    				bufferedReader.close();
		    				return str[0].toString().replace(" ", "").replace("\t", "");
		    			}
		    		 }
		    	 }
	   		}catch (FileNotFoundException e1) {
	   			e1.printStackTrace();
	   		}catch (IOException e) { 
	   			e.printStackTrace();
	   		}
      		try {
      			bufferedReader.close();
      		} catch (IOException e) {
      			e.printStackTrace();
      		}
      		return "";
     }	
}
