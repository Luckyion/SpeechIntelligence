package me.videa.functions.map;

import android.util.Log;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.baidu.mapapi.map.offline.MKOfflineMapListener;

public class OfflineMapManager {
	
	MKOfflineMap mOffline = null;  //申明变量
	
	public MKOfflineMap initOfflineMap(MapView mMapView){
		
		//写在onCreate函数里
		mOffline = new MKOfflineMap();
		//offline 实始化方法用更改。
		mOffline.init(new MKOfflineMapListener(){
		@Override
		public void onGetOfflineMapState(int type, int state){
			switch(type){
				case MKOfflineMap.TYPE_DOWNLOAD_UPDATE:
				{
					MKOLUpdateElement update = mOffline.getUpdateInfo(state);
				}
					break;
				case MKOfflineMap.TYPE_NEW_OFFLINE:
					Log.d("OfflineDemo", String.format("add offlinemapnum:%d", state));
					break;
				case MKOfflineMap.TYPE_VER_UPDATE:
					Log.d("OfflineDemo", String.format("new offlinemapver"));
					break;
					}
				}
			}
		
		);
		return mOffline;		
	}
}
