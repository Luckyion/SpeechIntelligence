package me.videa.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharePreference的帮助类
 * 
 * @author zhou_haijiang
 * 
 */
public class SharePreferenceHelper {
	// 上下文
	private Context context;
	// SharedPreferences对象
	private SharedPreferences settings;
	// SharedPreferences名称
	public static final String PREFS_NAME = "vickie";
	// Editor对象
	private SharedPreferences.Editor editor;
	// 该类对象
	private static SharePreferenceHelper instanse = null;

	/**
	 * 默认构造函数
	 */
	private SharePreferenceHelper() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 构造函数，初始化该对象
	 * 
	 * @param context
	 *            上下文
	 */
	private SharePreferenceHelper(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		settings = context.getSharedPreferences(PREFS_NAME,
				Context.MODE_PRIVATE);
		editor = settings.edit();
	}

	/**
	 * 得到该类对象
	 * 
	 * @param context
	 *            上下文
	 * @return 该对象
	 */
	public static SharePreferenceHelper getInstance(Context context) {
		// TODO Auto-generated method stub
		if (null == instanse) {
			instanse = new SharePreferenceHelper(context);
		}
		return instanse;

	}

	/**
	 * 存放string类型的键值对
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public void setString(String key, String value) {
		// TODO Auto-generated method stub
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * 
	 * 根据key得到String类型的值
	 * 
	 * @param key
	 *            键
	 * @param defValue
	 *            默认值
	 * @return 值
	 */
	public String getString(String key, String defValue) {
		// TODO Auto-generated method stub
		return settings.getString(key, defValue);
	}

	/**
	 * 存放Integer类型的键值对
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public void setInteter(String key, Integer value) {
		// TODO Auto-generated method stub
		editor.putInt(key, value);
		editor.commit();
	}

	/**
	 * 
	 * 根据key得到Integer类型的值
	 * 
	 * @param key
	 *            键
	 * @param defValue
	 *            默认值
	 * @return 值
	 */
	public int getInteger(String key, Integer defValue) {
		// TODO Auto-generated method stub
		return settings.getInt(key, defValue);
	}

	/**
	 * 存放Long类型的键值对
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public void setLong(String key, Long value) {
		// TODO Auto-generated method stub
		editor.putLong(key, value);
		editor.commit();
	}

	/**
	 * 
	 * 根据key得到Long类型的值
	 * 
	 * @param key
	 *            键
	 * @param defValue
	 *            默认值
	 * @return 值
	 */
	public Long getLong(String key, Long defValue) {
		// TODO Auto-generated method stub
		return settings.getLong(key, defValue);
	}

	/**
	 * 存放Float类型的键值对
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public void setFloat(String key, Float value) {
		// TODO Auto-generated method stub
		editor.putFloat(key, value);
		editor.commit();
	}

	/**
	 * 
	 * 根据key得到Float类型的值
	 * 
	 * @param key
	 *            键
	 * @param defValue
	 *            默认值
	 * @return 值
	 */
	public Float getFloat(String key, Float defValue) {
		// TODO Auto-generated method stub
		return settings.getFloat(key, defValue);
	}

	/**
	 * 存放Boolean类型的键值对
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public void setBoolean(String key, Boolean value) {
		// TODO Auto-generated method stub
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * 
	 * 根据key得到Boolean类型的值
	 * 
	 * @param key
	 *            键
	 * @param defValue
	 *            默认值
	 * @return 值
	 */
	public Boolean getBoolean(String key, Boolean defValue) {
		// TODO Auto-generated method stub
		return settings.getBoolean(key, defValue);
	}
}
