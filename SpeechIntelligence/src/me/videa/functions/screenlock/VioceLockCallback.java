package me.videa.functions.screenlock;

public interface VioceLockCallback {
	
	
	/**
	 * 发送语音控制锁屏指令
	 * @param code
	 */
	public void controlCode(int code);

}
