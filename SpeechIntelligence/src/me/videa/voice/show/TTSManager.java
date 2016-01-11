package me.videa.voice.show;

import me.videa.base.functions.TextToSpeech;
import me.videa.effects.MainShowView;
import me.videa.voice.show.beans.ExtraBean;
import me.videa.voice.show.beans.TTSBean;
import android.content.Context;
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
	
	public TTSManager(Context context, Handler handler, MainShowView view){
		this.mHandler = handler;
		this.mShowView = view;
		mTextToSpeech = new TextToSpeech(context, this);
		mTextToSpeech.initSpeechSynthesizer();
	}
	
	/**
	 * 开始合成
	 * @param text
	 */
	public void start(String text){
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
		mBean = new ExtraBean();
		ttsBean = new TTSBean();
		ttsBean.setStatus(status);
		mBean.setTtsBean(ttsBean);
		mShowView.reDraw(HandlerWhat.TTS_STATE, mBean);
	}

	@Override
	public void onSpeakProgress(int progress) {
		// TODO Auto-generated method stub
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
	
	

}
