package me.videa.cache;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.lidroid.xutils.cache.MD5FileNameGenerator;

import android.os.Environment;

public abstract class AbstractCache {
	
	public static final long EXPRIED = 86400000 * 7;
	protected static final String SUFFIX = ".vk";

	/**
	 * 读取Cache文件</br> 该方法是异步调用方法，防止缓存文件过大时读取缓慢导致界面卡顿。</br> 通过回调接口
	 * {@link Cache#setCache(String)}方法返回读取结果。</br> 当Cache文件未存储或已过期，返回null.
	 * 
	 * @param cacheFile
	 *            缓存文件名称，只传入文件名即可，不必指定后缀名称
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	void readCache(String cacheFile) {
		// TODO Auto-generated method stub
	}

	/**
	 * 写入Cache文件
	 * 
	 * @param cacheFile
	 *            缓存文件
	 * @throws IOException
	 */
	void writeCache(CacheBean cacheBean) {
		// TODO Auto-generated method stub

	}

	/**
	 * 获得缓存文件路径
	 * 
	 * @return String
	 */
	protected String getCachePath() {
		if (Environment.getExternalStorageState() != null) {
			return Environment.getExternalStorageDirectory().toString()
					+ File.separator + "nari" + File.separator + "cache"
					+ File.separator;
		}
		return null;
	}
	
	/**
	 * 获得加密文件名称
	 * @param filePath
	 * @return
	 */
	protected String getEncryptName(String filePath) {
		return new MD5FileNameGenerator().generate(filePath);
	}
}
