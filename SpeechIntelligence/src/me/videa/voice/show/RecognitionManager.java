package me.videa.voice.show;

import me.videa.base.functions.VoiceRecognition;
import me.videa.base.settings.IatSettings;
import me.videa.effects.MainShowView;
import me.videa.voice.show.beans.ExtraBean;
import me.videa.voice.show.beans.RecognitionBean;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class RecognitionManager implements RecognitionListener{
	
	private Context mContext;
	private Handler mHandler;
	private MainShowView mShowView;
	private VoiceRecognition mRecognition;
	private ExtraBean mBean;
	private RecognitionBean mRecognitionBean;
	private boolean noSpeech = false;
	
	public RecognitionManager(Context context, Handler handler, MainShowView view){
		this.mContext = context;
		this.mHandler = handler;
		this.mShowView = view;
		mRecognition = new VoiceRecognition(context, this);
		mRecognition.init();
	}
	
	/**
	 * 更新进度
	 * @param progress
	 */
	private void updateProgress(int progress){
		mBean = new ExtraBean();
		mRecognitionBean = new RecognitionBean();
		mRecognitionBean.setProgress(progress);
		mBean.setRecognitionBean(mRecognitionBean);
		mShowView.reDraw(HandlerWhat.RECOGNITION_VOLUME_STATE, mBean);
		mRecognition.init();
	}

	/**
	 * 开始听
	 */
	public void start(){
		sleep(200);
		mRecognition.start();
	}
	/**
	 * 取消听
	 */
	public void cancel(){
		mRecognition.cancel();
	}
	/**
	 * 停止听
	 */
	public void stop(){
		mRecognition.stop();
	}
	/**
	 * 进入设置
	 */
	public void setting(){
		Intent intents = new Intent(mContext, IatSettings.class);
		mContext.startActivity(intents);
	}

	@Override
	public void onInitialized() {
		// TODO Auto-generated method stub
		start();
	}

	@Override
	public void onError(String code) {
		// TODO Auto-generated method stub
		stop();
		start();
	}

	@Override
	public void onResult(String results) {
		// TODO Auto-generated method stub
		Message message = new Message();
		Bundle mBundle = new Bundle();
		mBundle.putString("data", results);
		message.setData(mBundle);
		message.what = HandlerWhat.CONVERSATION_HOST;
		mHandler.sendMessage(message);
		start();//监听完成后继续监听
	}

	@Override
	public void onBeginOfSpeech() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onVolumeChanged(int volume) {
		// TODO Auto-generated method stub
		updateProgress(volume);
	}

	@Override
	public void onEndOfSpeech() {
		// TODO Auto-generated method stub
		
	}
	
	private void sleep(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
