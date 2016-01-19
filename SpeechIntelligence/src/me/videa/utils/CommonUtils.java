package me.videa.utils;

import java.util.Random;
import java.util.regex.Pattern;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 普通帮助类
 * 
 * @author Administrator
 * 
 */
public class CommonUtils {

	/**
	 * 得到任意区间的随机数
	 * 
	 * @param max
	 *            最大数
	 * @param min最小数
	 * 
	 * @return 区间内的随机数
	 */
	public static int getRandom(int max, int min) {
		// TODO Auto-generated method stub
		Random random = new Random();
		int getRandom = random.nextInt(max - min + 1) + min;
		return getRandom;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 *            字符串
	 * @return 是否为空
	 */
	public static boolean isEmpty(String str) {
		return null == str || "".equals(str);
	}

	/**
	 * 判断手机网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager mgr = (ConnectivityManager) context
				.getApplicationContext().getSystemService(
						Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] info = mgr.getAllNetworkInfo();
		if (info != null) {
			for (int i = 0; i < info.length; i++) {
				if (info[i].getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断字符串是否为数字
	 * 
	 * @param str
	 *            字符串
	 * @return 是否为数字
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
}
