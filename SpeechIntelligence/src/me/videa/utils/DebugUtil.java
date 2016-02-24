package me.videa.utils;

import android.util.Log;

/**
 * 调试工具类 android.util.Log常用的方法有以下5个：Log.v() ,Log.d() ,Log.i() ,Log.w() ,Log.e()
 * 。按照日志级别从高到低为ERROR, WARN, INFO, DEBUG, VERBOSE.至于日志级别本身的含义.
 * 
 * 1.下面是对各种日志级别的输出介绍:
 * 
 * 　1、Log.v 的输出颜色为黑色的，输出大于或等于VERBOSE日志级别的信息
 * 
 * 　2、Log.d的输出颜色是蓝色的，输出大于或等于DEBUG日志级别的信息
 * 
 * 　3、Log.i的输出为绿色，输出大于或等于INFO日志级别的信息
 * 
 * 　4、Log.w的输出为橙色, 输出大于或等于WARN日志级别的信息
 * 
 * 　5、Log.e的输出为红色，仅输出ERROR日志级别的信息.
 * 
 * @author Administrator
 * 
 */
public class DebugUtil {
	/**
	 * 调试控制符
	 */
	public static final boolean DEBUG = true;

	public static void v(String tag, String msg) {
		if (DEBUG) {
			Log.v(tag, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (DEBUG) {
			Log.d(tag, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (DEBUG) {
			Log.i(tag, msg);
		}
	}

	public static void w(String tag, String msg) {
		if (DEBUG) {
			Log.w(tag, msg);
		}
	}

	public static void e(String tag, String msg) {
		if (DEBUG) {
			Log.w(tag, msg);
		}
	}

	public static void a(String tag, String msg) {
		if (DEBUG) {
			Log.w(tag, msg);
		}
	}
}
