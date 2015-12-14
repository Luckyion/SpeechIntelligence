package me.videa.functions.analysis;

import java.util.LinkedList;
import java.util.List;

import me.videa.base.db.entity.Action;

public interface DbAnalysis {
	/**
	 * 鏌ヨ¯㈡寚瀹氳¯­涔夋墍瀵瑰簲鐨勫姩浣
	 * @param compSentence1 璇­涔夎В鏋愮殑璇­鍙¥
	 * @param isFuzzy 鏄¯鍚︽ā绯婃煡璇¢
	 * @return
	 */
	public List<Action> query(String compSentence1, String password, boolean isFuzzy);
	
	/**
	 * 鎸夌収鐩镐技搴︽帓搴忓悗鐨勫姩浣
	 * @return 鎺掑簭鍚庣殑鍔ㄤ綔
	 */
	public LinkedList<Action> soft();
	/**
	 * 淇濆瓨璇­涔夊姩浣
	 * @param action
	 * @return 鏄¯鍚︽垚鍔
	 */
	public boolean save(Action action);

}

