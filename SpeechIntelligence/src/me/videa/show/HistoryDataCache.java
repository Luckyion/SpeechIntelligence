package me.videa.show;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HistoryDataCache {
	
	private static HistoryDataCache historyDataCache;
	
	/**
	 * 当前目录栈，当按返回键的时候，依次从栈中拿出数据</br>
	 * 当进入目录时，将目录内容压入栈。
	 */
	private static HashMap<String, List<Map<String, String>>> mCache;
	
	private HistoryDataCache() {
		// TODO Auto-generated constructor stub
	}
	
	public static HistoryDataCache initCache(){
		if(historyDataCache == null){
			historyDataCache = new HistoryDataCache();			
		}
		if(mCache == null){
			mCache = new HashMap<String, List<Map<String, String>>>();
		}
		return historyDataCache;
	}
	
	/**
	 * 将新刷新的数据缓存在第一位
	 * @param cache
	 */
	public void addCache(String key, List<Map<String, String>> cache){
		if(mCache == null){
			mCache = new HashMap<String, List<Map<String, String>>>();
		}
		mCache.put(key, cache);
	}
	/**
	 * 取出最后缓存的数据
	 * @return
	 */
	public List<Map<String, String>> getCache(String key){
		return mCache.get(key);
	}
	/**
	 * 清空缓存,并销毁缓存对象
	 */
	public void clear(){
		mCache.clear();
	}

}
