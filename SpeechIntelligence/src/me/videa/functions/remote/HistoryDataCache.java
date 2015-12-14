package me.videa.functions.remote;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HistoryDataCache {
	
	private static HistoryDataCache historyDataCache;
	
	/**
	 * 褰撳墠鐩®褰曟爤锛屽綋鎸夎繑鍥為敭鐨勬椂鍊欙紝渚濇¬′粠鏍堜腑鎷垮嚭鏁版嵁</br>
	 * 褰撹繘鍏ョ洰褰曟椂锛屽皢鐩®褰曞唴瀹瑰帇鍏ユ爤銆
	 */
	private static HashMap<String, List<Map<String, String>>> mCache;
	
	private HistoryDataCache() {
		// TODO Autogenerated constructor stub
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
	 * 灏嗘柊鍒锋柊鐨勬暟鎹®缂撳瓨鍦ㄧ¬¬涓€浣
	 * @param cache
	 */
	public void addCache(String key, List<Map<String, String>> cache){
		if(mCache == null){
			mCache = new HashMap<String, List<Map<String, String>>>();
		}
		mCache.put(key, cache);
	}
	/**
	 * 鍙栧嚭鏈€鍚庣紦瀛樼殑鏁版嵁
	 * @return
	 */
	public List<Map<String, String>> getCache(String key){
		return mCache.get(key);
	}
	/**
	 * 娓呯┖缂撳瓨,骞堕攢姣佺紦瀛樺¯硅薄
	 */
	public void clear(){
		mCache.clear();
	}

}

