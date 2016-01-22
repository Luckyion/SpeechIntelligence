package me.videa.functions.map;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.MyLocationData;

public class LocationReceiver extends BroadcastReceiver{
	
	private final static String TAG = "LocationReceiver";
	
	BDMapView mBdMapView;
	
	public LocationReceiver(BDMapView mapView) {
		this.mBdMapView = mapView;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (intent.getAction().equals(
				GpsService.GPS_MSG)) {
			BDLocation mLocation = intent.getParcelableExtra("location");
			mBdMapView.update(mLocation);
		}
	}
	
	/**
	 * 初始化方向传感器
	 */
	private void initOritationListener(Context context) {
		MyOrientationListener myOrientationListener = new MyOrientationListener(context);
		myOrientationListener
				.setOnOrientationListener(new OnOrientationListener() {
					@Override
					public void onOrientationChanged(float x) {

						// 构造定位数据
						MyLocationData locData = new MyLocationData.Builder()
						// 此处设置开发者获取到的方向信息，顺时针0-360
								.direction(x).build();
						// 设置定位数据
//						mBaiduMap.setMyLocationData(locData);
						// 设置自定义图标

//						BitmapDescriptor mDescriptor = BitmapDescriptorFactory
//								.fromResource(R.drawable.main_icon_nav);
//						mConfiguration = new MyLocationConfiguration(
//								LocationMode.NORMAL, true, mDescriptor);
//						mBaiduMap.setMyLocationConfigeration(mConfiguration);

					}
				});
	}

}
