package me.videa.functions.map;

import java.sql.Time;

import me.videa.utils.LogUtil;
import me.videa.utils.TimeUtils;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

public class GpsService extends Service implements BDLocationListener {
	
	
	
	private final String TAG = "GpsService";

	// 定位相关
	LocationClient mLocClient;	
	private MyLocationData myLocationData;
	boolean isRequest = false;// 是否手动触发请求定位
	boolean isFirstLoc = true;// 是否首次定位
	private LocationClientOption option = null;
	public static final String GPS_MSG = "me.videa.GpsService";
	private static GpsService mGpsService;
	
	public static GpsService getGpsService(){
		return mGpsService;
	}

	private void initLocation() {
		// 定位初始化
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(this);
		option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000 * 5);
		option.setPriority(LocationClientOption.GpsFirst);
		mLocClient.setLocOption(option);
		mLocClient.start();
		requestLocClick();
	}
	
	public void CloseLocClient(){
		//
		if (mLocClient != null) {
			mLocClient.stop();
		}
	}
	/**
	 * 手动触发一次定位请求
	 */
	public void requestLocClick() {
		isRequest = true;
		mLocClient.requestLocation();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		mGpsService = this;
		initLocation();
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		if (mLocClient != null) {
			mLocClient.stop();
		}
		super.onDestroy();
	}

	@Override
	public void onReceiveLocation(BDLocation location) {
		// TODO Auto-generated method stub	
		if (location == null) {
			return;
		}
		/*
		if (location.getLatitude() == (4.9E-324)) {
			Toast.makeText(this, "未定位到准确位置", Toast.LENGTH_SHORT).show();
			LogUtil.i(TAG, "当前位置: " + new Time(System.currentTimeMillis()) + "未准确定位");
			return;
		}		*/
		LogUtil.i(TAG, "当前位置: " + new Time(System.currentTimeMillis()) + " lon=" + location.getLongitude() + " lat="+ location.getLatitude());
		Intent intent = new Intent(GPS_MSG);
		intent.putExtra("location", location);
		sendBroadcast(intent);
		// 是手动触发请求或首次定位时，移动到定位点
		if (isRequest || isFirstLoc) {
			isRequest = false;
		}
		// 首次定位完成
		isFirstLoc = false;
	}

	public void onReceivePoi(BDLocation poiLocation) {
		if (poiLocation == null) {
			return;
		}
	}
}
