package me.videa.functions.analysis;

import java.util.LinkedList;
import java.util.List;

import me.videa.base.db.entity.Action;

public interface DbAnalysis {
	/**
	 * 查询指定语义所对应的动作
	 * @param compSentence1 语义解析的语句
	 * @param isFuzzy 是否模糊查询
	 * @return
	 */
	public List<Action> query(String compSentence1, String password, boolean isFuzzy);
	
	/**
	 * 按照相似度排序后的动作
	 * @return 排序后的动作
	 */
	public LinkedList<Action> soft();
	/**
	 * 保存语义动作
	 * @param action
	 * @return 是否成功
	 */
	public boolean save(Action action);

}
