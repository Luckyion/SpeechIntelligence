package me.videa.voice.show;

public interface VoiceRecognitionListener {
	
	/**
	 * 初始化状态
	 * @param code
	 */
	public void onInit(String code);
	/**
	 * 识别错误
	 * @param code
	 */
	public void onError(String code);
	/**
	 * 识别成功，并返回结果
	 * @param result  识别结果
	 */
	public void onReComoplete(String result);

}
