package me.videa.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

/**
 * 获取手机信息工具类
 */
public class DeviceUtils {
	public static int displayWidth = 0;
	public static int displayHeight = 0;
	public static float displaydensity = 0;
	public static int displayDpi = 0;

	/**
	 * 返回屏幕分辨率的宽度 px
	 * 
	 * @param context
	 *            上下文
	 * @return 屏幕宽度
	 */
	public static int getDisplayWidth(Context context) {
		if (displayWidth == 0) {
			DisplayMetrics dm = new DisplayMetrics();
			((Activity) context).getWindowManager().getDefaultDisplay()
					.getMetrics(dm);
			displayWidth = dm.widthPixels;
		}
		return displayWidth;
	}

	/**
	 * 返回屏幕分辨率的高度 px
	 * 
	 * @param context
	 *            上下文
	 * @return 屏幕高度
	 */
	public static int getDisplayHeight(Context context) {
		if (displayHeight == 0) {
			DisplayMetrics dm = new DisplayMetrics();
			((Activity) context).getWindowManager().getDefaultDisplay()
					.getMetrics(dm);
			displayHeight = dm.heightPixels;
		}
		return displayHeight;
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 * 
	 * @param dpValue
	 *            dp值
	 * @param context
	 *            上下文
	 * @return pix值
	 */
	public static int dip2px(float dpValue, Context context) {
		if (displaydensity == 0) {
			DisplayMetrics dm = new DisplayMetrics();
			((Activity) context).getWindowManager().getDefaultDisplay()
					.getMetrics(dm);
			displaydensity = dm.density;
		}
		return (int) (dpValue * displaydensity + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 * 
	 * @param pxValue
	 *            px值
	 * @param context
	 *            上下文
	 * @return dp值
	 */
	public static int px2dip(float pxValue, Context context) {
		if (displaydensity == 0) {
			DisplayMetrics dm = new DisplayMetrics();
			((Activity) context).getWindowManager().getDefaultDisplay()
					.getMetrics(dm);
			displaydensity = dm.density;
		}
		return (int) (pxValue / displaydensity + 0.5f);
	}

	/**
	 * 得到手机上的IMEI号
	 * 
	 * @param context
	 *            上下文
	 * @return IMEI号
	 */
	public static String getIMEI(Context context) {
		TelephonyManager telManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		// 得到手机上的IMEI号
		String deviceId = telManager.getDeviceId();
		if (deviceId == null || "".equals(deviceId)) {
			return "";
		} else {
			return deviceId;
		}
	}

	/**
	 * 判断当前网络状态是否可用
	 * @param context 上下文
	 */
	public static boolean haveInternet(Context context) {
		ConnectivityManager conManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = conManager.getActiveNetworkInfo();
		if (info == null || !info.isConnected()) {
			return false;
		}
		// 漫游状态
		if (info.isRoaming()) {
			return true;
		}
		return true;
	}
}
