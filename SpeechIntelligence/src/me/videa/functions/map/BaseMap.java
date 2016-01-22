package me.videa.functions.map;

import me.videa.voice.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerDragListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;


/**
 * This is a demo.
 * @author pactera
 *
 */
public class BaseMap extends Activity {
	MapView mMapView = null;
	private BaiduMap mBaiduMap;
	private Marker marker;
	
	// 初始化全局 bitmap 信息，不用时及时 recycle
	BitmapDescriptor bdA = BitmapDescriptorFactory
			.fromResource(R.drawable.icon_marka);
	BitmapDescriptor bdB = BitmapDescriptorFactory
			.fromResource(R.drawable.icon_markb);
	BitmapDescriptor bdC = BitmapDescriptorFactory
			.fromResource(R.drawable.icon_markc);
	BitmapDescriptor bdD = BitmapDescriptorFactory
			.fromResource(R.drawable.icon_markd);
	BitmapDescriptor bd = BitmapDescriptorFactory
			.fromResource(R.drawable.img_dingwei);
	BitmapDescriptor bdGround = BitmapDescriptorFactory
			.fromResource(R.drawable.ground_overlay);
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);   
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext  
        //注意该方法要再setContentView方法之前实现  
        setContentView(R.layout.activity_bdmap_main);  
        //获取地图控件引用  
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();  
        mBaiduMap.setOnMarkerDragListener(new OnMarkerDragListener() {
    	    public void onMarkerDrag(Marker marker) {
    	        //拖拽中
    	    }
    	    public void onMarkerDragEnd(Marker marker) {
    	        //拖拽结束
    	    }
    	    public void onMarkerDragStart(Marker marker) {
    	        //开始拖拽
    	    }
    	});
    }   
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 menu.add(1, 1, 1, "普通地图");  
		 menu.add(1, 2, 2, "卫星地图");  
		 menu.add(1, 3, 3, "开启交通图");
		 menu.add(1, 4, 4, "开启城市热力图");
		 menu.add(1, 5, 5, "标注");
		 return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case 1:
			//普通地图  
		    mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
			break;
		case 2:
			//普通地图  
		      mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
		      break;
		case 3:
			if(item.getTitle().toString().equals("开启交通图")){
				//开启交通图   
				mBaiduMap.setTrafficEnabled(true);
				item.setTitle("关闭交通图");
			}else{
				mBaiduMap.setTrafficEnabled(false);
				item.setTitle("开启交通图");
			}
			break;
		case 4:
			if(item.getTitle().toString().equals("开启城市热力图")){
				mBaiduMap.setBaiduHeatMapEnabled(true);
				item.setTitle("关闭城市热力图");
			}else{
				mBaiduMap.setBaiduHeatMapEnabled(false);
				item.setTitle("开启城市热力图");
			}
			break;
		case 5:
			if(item.getTitle().toString().equals("标注")){
				LatLng point = new LatLng(39.963175, 116.400244);  
				//构建Marker图标  
				BitmapDescriptor bitmap = BitmapDescriptorFactory  
				    .fromResource(R.drawable.icon_marka);  
				//构建MarkerOption，用于在地图上添加Marker  
				OverlayOptions option = new MarkerOptions()  
				    .position(point)  
				    .icon(bitmap)
				    .draggable(true);  
				//在地图上添加Marker，并显示  
				marker = (Marker) (mBaiduMap.addOverlay(option));
				item.setTitle("清除");
			}else{
				marker.remove();//清除具体某个标注。
				item.setTitle("标注");
			}
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * 清除所有Overlay
	 * 
	 * @param view
	 */
	public void clearOverlay(View view) {
		mBaiduMap.clear();
	}
	
	@Override  
    protected void onResume() {  
        super.onResume();  
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理  
        mMapView.onResume();  
        }  
    @Override  
    protected void onPause() {  
        super.onPause();  
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理  
        mMapView.onPause();  
        }  
    @Override  
    protected void onDestroy() {  
        super.onDestroy();  
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理  
        mMapView.onDestroy();  
    }
}
