package me.videa.application;

/**
 * {@code GlobalMethod}全局方法定义接口</br>
 * 方法需要在Application中进行实现，实现后应用程序启动时调用方法并加载配置信息。
 * @author Vickie Tang
 * @version 1.0
 */
public interface GlobalMethod {
	
	public String configFilePath = "";
	/**
	 * 装载配置文件信息。
	 */
	public void loadConfig();
	
	/**
	 * 写入配置信息
	 */
	public void writeConfig();
	/**
	 * 初始化配置信息
	 */
	public void initConfig();

}
