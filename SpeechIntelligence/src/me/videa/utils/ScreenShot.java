package me.videa.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import com.baidu.mapapi.map.MapView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Environment;
import android.view.Display;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.WindowManager;
import android.widget.Toast;

public class ScreenShot {
	
	private Activity mActivity;
	
	public ScreenShot(Activity activity) {
		// TODO Auto-generated constructor stub
		this.mActivity = activity;
	}
	
	/**
	 * 获取和保存当前屏幕的截图
	 * @return path
	 */
	public String getandSaveCurrentImage() {
		// 1.构建Bitmap
		WindowManager windowManager = mActivity.getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		int w = display.getWidth();
		int h = display.getHeight();
		Bitmap Bmp = Bitmap.createBitmap(w, h, Config.ARGB_8888);
		// 2.获取屏幕
		View decorview = mActivity.getWindow().getDecorView();
		decorview.setDrawingCacheEnabled(true);
		Bmp = decorview.getDrawingCache();
		String savePath = getSDCardPath() + "/nari/ScreenShot";
		String filepath = "";
		// 3.保存Bitmap
		try {
			File path = new File(savePath);
			// 文件
			String fileName = new Date().getTime() + "";
			filepath = savePath + File.separator + fileName + ".png";
			File file = new File(filepath);
			if (!path.exists()) {
				path.mkdirs();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = null;
			fos = new FileOutputStream(file);
			if (null != fos) {
				Bmp.compress(Bitmap.CompressFormat.PNG, 90, fos);
				fos.flush();
				fos.close();
				Toast.makeText(mActivity.getApplicationContext(), "截屏文件已保存至SDCard/nari/ScreenShot/下",
						Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return filepath;
	}
	
	public String getViewBitmap() {
		View cv = mActivity.getWindow().getDecorView();
		Bitmap Bmp = Bitmap.createBitmap(cv.getWidth(), cv.getHeight(),Config.ARGB_8888);
		cv.setDrawingCacheEnabled(true);
	      Canvas canvas = new Canvas(Bmp);
	      cv.draw(canvas);
//		Bitmap bitmap = v.getDrawingCache(); 
//		return bitmap;
     
//        Bitmap Bmp = Bitmap.createBitmap(v.getMeasuredWidth(), v.getMeasuredHeight(), Config.ARGB_8888);
//        while(cacheBitmap == null){
//        	Rect outRect = new Rect();
//        	outRect.set(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
//        	cacheBitmap = v.getDrawingCache(outRect);
//        }
//        v.setDrawingCacheEnabled(true);
//        Bitmap Bmp = v.getDrawingCache();
		String savePath = getSDCardPath() + "/nari/ScreenShot";
		String filepath = "";
		// 3.保存Bitmap
		try {
			File path = new File(savePath);
			// 文件
			String fileName = new Date().getTime() + "";
			filepath = savePath + File.separator + fileName + ".png";
			File file = new File(filepath);
			if (!path.exists()) {
				path.mkdirs();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = null;
			fos = new FileOutputStream(file);
			if (null != fos) {
				Bmp.compress(Bitmap.CompressFormat.PNG, 90, fos);
				fos.flush();
				fos.close();
				Toast.makeText(mActivity.getApplicationContext(), "截屏文件已保存至SDCard/nari/ScreenShot/下",
						Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        return filepath;
    }

	/**
	 * 获取SDCard的目录路径功能
	 */
	private String getSDCardPath() {
		File sdcardDir = null;
		// 判断SDCard是否存在
		boolean sdcardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		if (sdcardExist) {
			sdcardDir = Environment.getExternalStorageDirectory();
		}
		return sdcardDir.toString();
	}
}