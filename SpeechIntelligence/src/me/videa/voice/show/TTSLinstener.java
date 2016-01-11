package me.videa.voice.show;

public interface TTSLinstener {
	/**
	 * 初始化完成
	 */
	public void onInitialized();
	/**
	 * 播放进度
	 * @param progress
	 */
	public void onSpeakProgress(int progress);
	/**
	 * 缓存进度
	 * @param progress
	 */
	public void onBufferProgress(int progress);
	/**
	 * 开始播放
	 */
	public void onSpeakBegin();
	/**
	 * 暂停播放
	 */
	public void onSpeakPaused();
	/**
	 * 恢复播放
	 */
	public void onSpeakResumed();
	/**
	 * 停止播放
	 */
	public void onSpeakStop();
	/**
	 * 播放完成
	 */
	public void onSpeechComplete();
	/**
	 * 发生错误
	 * @param code
	 */
	public void onError(String code);

}
