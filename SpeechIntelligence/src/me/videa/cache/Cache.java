package me.videa.cache;

/**
 * 缓存读取结果回调接口</br>
 * 当缓存过期或未缓存时返回null.
 * @author Vickie Tang
 *
 */
public interface Cache {
	
	/**
	 * 回调方法</br>
	 * 当缓存过期或无缓存时返回null.
	 * @param cache
	 */
	public void setCache(String cache);


}
