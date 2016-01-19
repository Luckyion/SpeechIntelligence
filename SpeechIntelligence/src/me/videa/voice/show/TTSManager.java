package me.videa.voice.show;

import me.videa.base.functions.TextToSpeech;
import me.videa.effects.MainShowView;
import me.videa.utils.DebugUtil;
import me.videa.voice.show.beans.ExtraBean;
import me.videa.voice.show.beans.TTSBean;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class TTSManager implements TTSLinstener{
	
	private final String TAG = TTSManager.class.getSimpleName();
	
	private Handler mHandler;
	private Message message;
	private Bundle mBundle;
	private MainShowView mShowView;
	private TextToSpeech mTextToSpeech;
	private ExtraBean mBean;
	private TTSBean ttsBean;
	private Context mContext;
	private static TTSManager manager;
	private boolean isNotified = false;
	
	private TTSManager(Context context, Handler handler, MainShowView view){
		this.mHandler = handler;
		this.mShowView = view;
		this.mContext = context;
		mTextToSpeech = new TextToSpeech(context, this);
		mTextToSpeech.initSpeechSynthesizer();
	}
	
	public static TTSManager initManager(Context context, Handler handler, MainShowView view){
		if(manager == null){
			manager = new TTSManager(context, handler, view);
		}
		return manager;		
	}
	
	public static TTSManager get(){
		return manager;
	}
	
	/**
	 * 开始合成
	 * @param text
	 */
	public void start(String text){
		isNotified = false;
		mTextToSpeech.start(text);
	}
	/**
	 * 停止合成
	 */
	public void stop(){
		mTextToSpeech.stop();
	}
	/**
	 * 恢复合成
	 */
	public void resume(){
		mTextToSpeech.resume();
	}
	
	public void pause(){
		mTextToSpeech.pause();
	}
	
	/**
	 * 更新进度
	 * @param progress
	 */
	private void updateProgress(int progress){
		if(mShowView == null){
			return;
		}
		mBean = new ExtraBean();
		ttsBean = new TTSBean();
		ttsBean.setProgress(progress);
		mBean.setTtsBean(ttsBean);
		mShowView.reDraw(HandlerWhat.TTS_STATE, mBean);
	}
	
	/**
	 * 更新状态
	 * @param status
	 */
	private void updatePlayStatus(String status){
		if(mShowView == null){
			return;
		}
		mBean = new ExtraBean();
		ttsBean = new TTSBean();
		ttsBean.setStatus(status);
		mBean.setTtsBean(ttsBean);
		mShowView.reDraw(HandlerWhat.TTS_STATE, mBean);
	}

	@Override
	public void onSpeakProgress(int progress) {
		// TODO Auto-generated method stub		
		DebugUtil.d(TAG, "播放进度: " + progress);
		updateProgress(progress);
	}

	@Override
	public void onBufferProgress(int progress) {
		// TODO Auto-generated method stub
		Log.d(TAG, "缓存进度: " + progress);
	}

	@Override
	public void onSpeakBegin() {
		// TODO Auto-generated method stub
		updatePlayStatus("start");
	}

	@Override
	public void onSpeakPaused() {
		// TODO Auto-generated method stub
		updatePlayStatus("pause");
	}

	@Override
	public void onSpeakResumed() {
		// TODO Auto-generated method stub
		updatePlayStatus("resume");
	}

	@Override
	public void onSpeechComplete() {
		// TODO Auto-generated method stub
		if(!isNotified){
			isNotified = true;
			sendNotify();
		}
	}

	@Override
	public void onError(String code) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onSpeakStop() {
		// TODO Auto-generated method stub
		updatePlayStatus("stop");
	}

	@Override
	public void onInitialized() {
		// TODO Auto-generated method stub
		mHandler.sendEmptyMessage(HandlerWhat.TTS_STATE_INIT);
	}
	
	/**
	 * 发送广播
	 */
	void sendNotify(){
		Intent intent = new Intent();
		intent.setAction("me.videa.ready");
		mContext.sendBroadcast(intent);
	}

}
