package me.videa.functions.analysis;

import java.util.List;

/**
 * 语义分析功能定义
 * Step 1. 通过语音或其他获取语句</br>
 * Step 2. 通过语义分析获得相对确定的语句</br>
 * Step 3. 模糊查询数据库获得执行动作</br>
 * Step 4. 按照相似度排序得到确定或相对确定的执行动作</br>
 * Step 5. 完成</br>
 * @author Vickie
 * version 1.0
 *
 */
public interface Analysis {
	
	/**
	 * 按照词语分析语义，并返回分析后的语义。</br>
	 * 若语义分析失败，返回指定语义提示。</br>
	 * @param sentence 需要分析的语句
	 * @param passwrod 验证密钥
	 * @param 是否模糊分析
	 * @return List<String> 分析后的准确语义动作
	 */
	public List<String> analyse(String sentence, String password, boolean isFuzzy);	


}
