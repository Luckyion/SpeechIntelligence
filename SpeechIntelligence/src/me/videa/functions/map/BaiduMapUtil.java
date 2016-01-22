package me.videa.functions.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.SnapshotReadyCallback;

public class BaiduMapUtil {
	
	
	/**
	 * 百度地图截图保存
	 * @param mBaiduMap  百度地图对象
	 * @param fileName  截图保存路径和文件名
	 */
	public static void getMapSnapshot(BaiduMap mBaiduMap, final String fileName){
		mBaiduMap.snapshot(new SnapshotReadyCallback() {
		      public void onSnapshotReady(Bitmap snapshot) {
				File file = new File(fileName);
					FileOutputStream out;
					try {
						out = new FileOutputStream(file);
						if (snapshot.compress(
								Bitmap.CompressFormat.PNG, 100, out)) {
							out.flush();
							out.close();
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		});
	}
	
}
