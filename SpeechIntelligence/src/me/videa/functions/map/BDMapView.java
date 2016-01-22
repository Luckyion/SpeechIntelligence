package me.videa.functions.map;

import me.videa.utils.DebugUtil;
import me.videa.utils.SharePreferenceHelper;
import me.videa.voice.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapStatusChangeListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerDragListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;

public class BDMapView extends RelativeLayout implements MapViewLitener,
		OnClickListener {

	private final static String TAG = "BDMapView";

	private LayoutInflater mInflater;
	private RelativeLayout mLayout;
	private MapView mMapView;
	private BaiduMap mBaiduMap;
	private MyLocationData locData;
	private MapStatus mStatus;
	private float mZoom = 20;
	private double mRotate;
	private MapStatusUpdate mStatusUpdate;
	private LatLng mLatLng;
	private Button mLocate;
	private Button mRoadCondition;
	private Button m2Or3DButton;
	private Marker marker;

	/**
	 * 方向信息
	 */
	private MyOrientationListener myOrientationListener;

	double mLatitude;
	double mLongitude;
	float mDirection;
	float mAccuracy;

	/**
	 * 地图类型
	 * 
	 * @author pactera
	 * 
	 */
	public static enum MAP_TYPE {
		MAP_2D, MAP_3D
	}

	private MAP_TYPE mType = MAP_TYPE.MAP_2D;
	
	private int mCurModel = 0;
	
	private LocationMode mLocationMode = LocationMode.FOLLOWING;
	
	
	/***************离线地图******************/

	
	private MKOfflineMap mMKOfflineMap;
	
	
	/*****************交通图*********************/
	boolean isOpenRoadCondition = true;
	
	boolean is3D = false;
	
	/**
	 * 当前地点击点
	 */
	private GeoPoint currentPt = null;

	// 初始化全局 bitmap 信息，不用时及时 recycle
	private BitmapDescriptor bdA = BitmapDescriptorFactory
			.fromResource(R.drawable.icon_marka);
	private BitmapDescriptor bdB = BitmapDescriptorFactory
			.fromResource(R.drawable.icon_markb);
	private BitmapDescriptor bdC = BitmapDescriptorFactory
			.fromResource(R.drawable.icon_markc);
	private BitmapDescriptor bdD = BitmapDescriptorFactory
			.fromResource(R.drawable.icon_markd);
	private BitmapDescriptor bd = BitmapDescriptorFactory
			.fromResource(R.drawable.img_dingwei);
	private BitmapDescriptor bdGround = BitmapDescriptorFactory
			.fromResource(R.drawable.ground_overlay);

	public BDMapView(Context context) {
		super(context);
		// 获取地图控件引用
		mInflater = LayoutInflater.from(context);
		mLayout = (RelativeLayout) mInflater.inflate(
				R.layout.activity_bdmap_main, null);
		mMapView = (MapView) mLayout.findViewById(R.id.bmapView);
		mLocate = (Button) mLayout.findViewById(R.id.locate);
		mLocate.setOnClickListener(this);
		mRoadCondition = (Button) mLayout.findViewById(R.id.road_button);
		mRoadCondition.setOnClickListener(this);
		m2Or3DButton = (Button) mLayout.findViewById(R.id.ex_button);
		m2Or3DButton.setOnClickListener(this);
		mBaiduMap = mMapView.getMap();
		mBaiduMap.setOnMapStatusChangeListener(new OnMapStatusChangeListener() {
			
			@Override
			public void onMapStatusChangeStart(MapStatus arg0) {
				// TODO Auto-generated method stub				
				DebugUtil.d(TAG, "map ....2");
			}
			
			@Override
			public void onMapStatusChangeFinish(MapStatus arg0) {
				// TODO Auto-generated method stub
				mZoom = arg0.zoom;
				DebugUtil.d(TAG, "map ...1");
			}
			
			@Override
			public void onMapStatusChange(MapStatus arg0) {
				// TODO Auto-generated method stub
				DebugUtil.d(TAG, "map ..0");
			}
		});
		mBaiduMap.setOnMarkerDragListener(new OnMarkerDragListener() {
			public void onMarkerDrag(Marker marker) {
				// 拖拽中
				DebugUtil.d(TAG, "拖拽中...");
			}

			public void onMarkerDragEnd(Marker marker) {
				// 拖拽结束
				DebugUtil.d(TAG, "拖拽结束...");
			}

			public void onMarkerDragStart(Marker marker) {
				// 开始拖拽
				DebugUtil.d(TAG, "开始拖拽...");
			}
		});
		mBaiduMap.setTrafficEnabled(true);
		initConfig(context); // 初始化配置
		updateMyLocationConfig();
		initOrientationListener(context);
		initOfflineMapListener();
		this.addView(mLayout);
	}

	void initConfig(Context context) {
		mBaiduMap.setMyLocationEnabled(true);
		mLatitude = Double.parseDouble(SharePreferenceHelper.getInstance(
				context).getString("latitude", "31.952606"));
		mLongitude = Double.parseDouble(SharePreferenceHelper
				.getInstance(context).getString("longitude", "118.812969"));
		mAccuracy = 0;
		mDirection = 100;
		updateMyLocation();
	}	
	
	void initOrientationListener(Context context){
		myOrientationListener = new MyOrientationListener(context);
		myOrientationListener
				.setOnOrientationListener(new OnOrientationListener() {

					@Override
					public void onOrientationChanged(float x) {
						// TODO Auto-generated method stub
						DebugUtil.d(TAG, "方向发生变化 : " + x);						
						mDirection = x;
						updateMyLocation();

					}
				});
		myOrientationListener.start();
	}
	
	void initOfflineMapListener(){
		OfflineMapManager mOfflineMapManager = new OfflineMapManager();
		mMKOfflineMap = mOfflineMapManager.initOfflineMap(mMapView);
	}

	void updateMyLocation() {		
		if(mCurModel == 2){
			locData = new MyLocationData.Builder()
					// 此处设置开发者获取到的方向信息，顺时针0-360
							.direction(mDirection).latitude(mLatitude).longitude(mLongitude).build();
					// 设置定位数据
					mBaiduMap.setMyLocationData(locData);
		}else{
			locData = new MyLocationData.Builder().accuracy(mAccuracy)
					// 此处设置开发者获取到的方向信息，顺时针0-360
							.direction(mDirection).latitude(mLatitude).longitude(mLongitude).build();
					// 设置定位数据
					mBaiduMap.setMyLocationData(locData);
		}
		// 设置定位数据
		mBaiduMap.setMyLocationData(locData);
	}
	
	void updateMyLocationConfig(){
		BitmapDescriptor mDescriptor = BitmapDescriptorFactory
				.fromResource(R.drawable.icon_landing_arrow);
		MyLocationConfiguration mConfiguration = new MyLocationConfiguration(
				mLocationMode, true, null);
		mBaiduMap.setMyLocationConfigeration(mConfiguration);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu) {
		menu.add(1, 1, 1, "普通地图");
		menu.add(1, 2, 2, "卫星地图");
		menu.add(1, 3, 3, "开启交通图");
		menu.add(1, 4, 4, "开启城市热力图");
		menu.add(1, 5, 5, "标注");
	}

	@Override
	public void onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			// 普通地图
			mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
			break;
		case 2:
			// 普通地图
			mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
			break;
		case 3:
			if (item.getTitle().toString().equals("开启交通图")) {
				// 开启交通图
				mBaiduMap.setTrafficEnabled(true);
				item.setTitle("关闭交通图");
			} else {
				mBaiduMap.setTrafficEnabled(false);
				item.setTitle("开启交通图");
			}
			break;
		case 4:
			if (item.getTitle().toString().equals("开启城市热力图")) {
				mBaiduMap.setBaiduHeatMapEnabled(true);
				item.setTitle("关闭城市热力图");
			} else {
				mBaiduMap.setBaiduHeatMapEnabled(false);
				item.setTitle("开启城市热力图");
			}
			break;
		case 5:
			if (item.getTitle().toString().equals("标注")) {
				LatLng point = new LatLng(39.963175, 116.400244);
				// 构建Marker图标
				BitmapDescriptor bitmap = BitmapDescriptorFactory
						.fromResource(R.drawable.icon_marka);
				// 构建MarkerOption，用于在地图上添加Marker
				OverlayOptions option = new MarkerOptions().position(point)
						.icon(bitmap).draggable(true);
				// 在地图上添加Marker，并显示
				marker = (Marker) (mBaiduMap.addOverlay(option));
				item.setTitle("清除");
			} else {
				marker.remove();// 清除具体某个标注。
				item.setTitle("标注");
			}
		}
	}

	/**
	 * 清除所有Overlay
	 * 
	 * @param view
	 */
	public void clearOverlay(View view) {
		mBaiduMap.clear();
	}

	public void update(BDLocation mBdLocation) {
		mLatitude = mBdLocation.getLatitude();
		mLongitude = mBdLocation.getLongitude();
		mAccuracy = mBdLocation.getRadius();
		DebugUtil.d(TAG, "纬度： " + mLatitude + " 经度 ： " + mLongitude + " 范围：" + mAccuracy + " 方向：" + mDirection);
		updateMyLocation();
		updateLatLng();		
	}
	
	void updateLatLng(){
		mLatLng = new LatLng(mLatitude,
				mLongitude);
		if(is3D){
			mStatus = new MapStatus.Builder(mBaiduMap.getMapStatus()).zoom(mZoom).overlook(90)
					.target(mLatLng).build();
		}else{
			if(mCurModel != 2){
				mStatus = new MapStatus.Builder(mBaiduMap.getMapStatus()).zoom(mZoom).overlook(90)
						.target(mLatLng).build();
			}else{
				mStatus = new MapStatus.Builder(mBaiduMap.getMapStatus()).zoom(mZoom)
						.target(mLatLng).build();
			}
		}
				
		mStatusUpdate = MapStatusUpdateFactory.newMapStatus(mStatus);
		mBaiduMap.animateMapStatus(mStatusUpdate);
	}

	@Override
	public void onResume() {
		// 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		mMapView.onResume();
	}

	@Override
	public void onPause() {
		// 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		mMapView.onPause();
	}

	@Override
	public void onDestroy() {
		// 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
		mMapView.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.locate:
			GpsService.getGpsService().requestLocClick();
			if(mCurModel == 0){
				mCurModel = 1;
				mLocationMode = LocationMode.FOLLOWING;
				mZoom = 20;
				mLocate.setBackgroundResource(R.drawable.main_icon_follow);
				break;
			}
			if(mCurModel == 1){
				mCurModel = 2;
				mLocationMode = LocationMode.COMPASS;
				mZoom = 22;
				mAccuracy = 0;
				mLocate.setBackgroundResource(R.drawable.main_icon_compass);
				break;
			}
			if(mCurModel == 2){
				mCurModel = 0;
				mLocationMode = LocationMode.NORMAL;
				mLocate.setBackgroundResource(R.drawable.img_dingwei);
				break;
			}				
			break;
		case R.id.road_button:
			if (isOpenRoadCondition) {
				// 开启交通图
				isOpenRoadCondition = false;
				mBaiduMap.setTrafficEnabled(false);
			} else {
				isOpenRoadCondition = true;
				mBaiduMap.setTrafficEnabled(true);
			}
			break;
		case R.id.ex_button:
			if(is3D){
				is3D = false;
				break;
			}else{
				is3D = true;
				break;
			}
		default:
			break;
		}
		updateMyLocationConfig();
		updateMyLocation();		
		updateLatLng();
	}

	public MAP_TYPE getmType() {
		return mType;
	}

	public void setmType(MAP_TYPE mType) {
		this.mType = mType;
	}

}
