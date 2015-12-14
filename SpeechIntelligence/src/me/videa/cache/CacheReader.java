package me.videa.cache;

import java.io.File;

/**
 * Cache文件读取类</br>
 * 读取过程是异步的，需要实现{@link Cache}接口获取Cache文件内容。</br>
 * 调用方法：</br>
 * {@code
 * 		new CacheReader(new Cache(){</br>
 * 	&nbsp;&nbsp;&nbsp;&nbsp;public void setCache(String cache){</br>
 * 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			//...</br>
 * 		&nbsp;&nbsp;&nbsp;	}</br>
 * 	&nbsp;&nbsp;&nbsp;	}).readCache("xx");</br>
 * }
 * @author Vickie Tang
 *
 */
public class CacheReader extends AbstractCache {
	
	Cache mCache;
	
	public CacheReader(Cache cache) {
		// TODO Auto-generated constructor stub
		super();
		this.mCache = cache;
	}

	@Override
	public void readCache(String fileName) {
		// TODO Auto-generated method stub	
		File file = new File(getCachePath() + getEncryptName(fileName) + SUFFIX);
		if(!file.exists() || isExpired(file)){
			mCache.setCache(null);
			return;
		}
		new CacheReadThread(mCache, file).execute();
	}
	
	/**
	 * 缓存是否过期
	 * @param mFile
	 * @return true 过期   false 未过期
	 */
	private boolean isExpired(File mFile){
		long mCur = System.currentTimeMillis();
		long mLastModify = mFile.lastModified();
		return (mCur - mLastModify) > EXPRIED ? true : false;
	}

}
