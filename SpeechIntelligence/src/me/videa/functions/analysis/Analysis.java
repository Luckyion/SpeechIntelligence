package me.videa.functions.analysis;

import java.util.List;

/**
 * 璇­涔夊垎鏋愬姛鑳藉畾涔
 * Step 1. 閫氳繃璇­闊虫垨鍏朵粬鑾峰彇璇­鍙¥</br>
 * Step 2. 閫氳繃璇­涔夊垎鏋愯幏寰楃浉瀵圭‘瀹氱殑璇­鍙¥</br>
 * Step 3. 妯＄硦鏌ヨ¯㈡暟鎹®搴撹幏寰楁墽琛屽姩浣</br>
 * Step 4. 鎸夌収鐩镐技搴︽帓搴忓緱鍒扮‘瀹氭垨鐩稿¯圭‘瀹氱殑鎵ц¡屽姩浣</br>
 * Step 5. 瀹屾垚</br>
 * @author Vickie
 * version 1.0
 *
 */
public interface Analysis {
	
	/**
	 * 鎸夌収璇嶈¯­鍒嗘瀽璇­涔夛紝骞惰繑鍥炲垎鏋愬悗鐨勮¯­涔夈€</br>
	 * 鑻ヨ¯­涔夊垎鏋愬け璐ワ紝杩斿洖鎸囧畾璇­涔夋彁绀恒€</br>
	 * @param sentence 闇€瑕佸垎鏋愮殑璇­鍙¥
	 * @param passwrod 楠岃瘉瀵嗛挜
	 * @param 鏄¯鍚︽ā绯婂垎鏋
	 * @return List<String> 鍒嗘瀽鍚庣殑鍑嗙‘璇­涔夊姩浣
	 */
	public List<String> analyse(String sentence, String password, boolean isFuzzy);	


}

