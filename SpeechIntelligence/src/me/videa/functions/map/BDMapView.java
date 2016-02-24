package me.videa.functions.map;

import java.util.List;

import me.videa.application.MyApplication;
import me.videa.utils.DebugUtil;
import me.videa.utils.LogUtil;
import me.videa.utils.SharePreferenceHelper;
import me.videa.voice.R;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.b.l;
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
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviParaOption;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

public class BDMapView extends RelativeLayout implements MapViewLitener,
		OnClickListener, OnGetPoiSearchResultListener {

	private final static String TAG = "BDMapView";

	private Context mContext;
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
	
	private Button mSerach;
	private EditText mEditText;
	private Button mNavigation;
	
	private PoiSearch mPoiSearch;
	private PopupWindow popupWindow;
	private ResultAdapter popAdapter;
	
	public boolean isNav = false;
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
		mContext = context;
		mInflater = LayoutInflater.from(context);
		mLayout = (RelativeLayout) mInflater.inflate(
				R.layout.activity_bdmap_main, null);
		mMapView = (MapView) mLayout.findViewById(R.id.bmapView);
		mSerach = (Button) mLayout.findViewById(R.id.search);
		mSerach.setOnClickListener(this);
		mEditText = (EditText) mLayout.findViewById(R.id.searchContent);
		mLocate = (Button) mLayout.findViewById(R.id.locate);
		mLocate.setOnClickListener(this);
		mRoadCondition = (Button) mLayout.findViewById(R.id.road_button);
		mRoadCondition.setOnClickListener(this);
		m2Or3DButton = (Button) mLayout.findViewById(R.id.ex_button);
		m2Or3DButton.setOnClickListener(this);
		mNavigation = (Button) mLayout.findViewById(R.id.nav_button);
		mNavigation.setOnClickListener(this);
		/**初始化搜索引擎*/
		mPoiSearch = PoiSearch.newInstance();					
		mPoiSearch.setOnGetPoiSearchResultListener(this);
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
		case R.id.search:
			String mSear = mEditText.getText().toString();
			if(mSear == null || mSear.equals("")){
				return;
			}			
			PoiBoundSearchOption mBoundSearchOption = new PoiBoundSearchOption();
			LatLngBounds mBounds = new LatLngBounds.Builder().include(mLatLng).build();
			mBoundSearchOption.keyword(mSear);
			mBoundSearchOption.bound(mBounds);	
			mPoiSearch.searchInBound(mBoundSearchOption);
			break;
		case R.id.nav_button:
			break;
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

	@Override
	public void onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGetPoiDetailResult(PoiDetailResult arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 导航
	 * @param mInfo
	 */
	void navigate(PoiInfo mInfo){
		NaviParaOption mNaviParaOption = new NaviParaOption();
		mNaviParaOption.startPoint(mLatLng);
		mNaviParaOption.endPoint(mInfo.location);
		BaiduMapNavigation.setSupportWebNavi(true);
		double distance = distance(mLongitude, mLatitude, mInfo.location.longitude, mInfo.location.latitude);
		if(distance > 3){
			Toast.makeText(mContext, "距离太远啦，自动给您打开自行车导航", Toast.LENGTH_LONG).show();
			BaiduMapNavigation.openBaiduMapBikeNavi(mNaviParaOption, mContext);
		}else{
			BaiduMapNavigation.openBaiduMapWalkNavi(mNaviParaOption, mContext);
		}		
		isNav = true;
	}
	

	/**
	 * 根据两个位置的经纬度，来计算两地的距离（单位为KM） 参数为String类型
	 * 
	 * @param lat1
	 *            用户经度
	 * @param lng1
	 *            用户纬度
	 * @param lat2
	 *            商家经度
	 * @param lng2
	 *            商家纬度
	 * @return
	 */
	public static double distance(double long1, double lat1, double long2, double lat2) {
		double a, b, R;
		R = 6378137; // 地球半径
		lat1 = lat1 * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;
		a = lat1 - lat2;
		b = (long1 - long2) * Math.PI / 180.0;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
		return d;
	}

	public boolean onBackPressed(){
		if(isNav){
			BaiduMapNavigation.finish(mContext);
			return false;
		}
		return true;
	}
	
	@Override
	public void onGetPoiResult(PoiResult res) {
		// TODO Auto-generated method stub
		List<PoiInfo> list = res.getAllPoi();
		if(list == null || list.size() == 0){
			Toast.makeText(mContext, "没有搜到数据", Toast.LENGTH_LONG).show();
			return;
		}
		showPopWindow(list);
		for(PoiInfo mInfo : list){
			LogUtil.d(TAG, mInfo.address);
		}
	}
	
	/**
	 * 显示Popwindow
	 */
	private void showPopWindow(List<PoiInfo> params) {
		if (popupWindow != null && popupWindow.isShowing()) {
			return;
		}
		initPop(params);
		popAdapter.notifyDataSetChanged();
		popupWindow.showAtLocation(mSerach, Gravity.CENTER_VERTICAL
				| Gravity.CENTER_HORIZONTAL, 0, 0);
	}

	private void initPop(final List<PoiInfo> params) {
		View popupWindow_view = mInflater.inflate(
				R.layout.activity_dialog_list, null);
//		 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
		popupWindow = new PopupWindow(popupWindow_view, MyApplication.mScreenWidth - 180,
				MyApplication.mScreenHeight / 2, true);
		// 点击其他地方消失
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
		ListView mList = (ListView) popupWindow_view
				.findViewById(R.id.dialog_list);
		popAdapter = new ResultAdapter(mContext, params);
		mList.setAdapter(popAdapter);
		mList.setCacheColorHint(0);
		mList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				dismissPop();
				navigate(params.get(position));
			}
		});
	}

	private void dismissPop() {
		if (popupWindow != null && popupWindow.isShowing()) {
			popupWindow.dismiss();
			popupWindow = null;
		}
	}

}
