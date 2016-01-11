package me.videa.base.functions;

import me.videa.utils.ApkInstaller;
import me.videa.voice.R;
import me.videa.voice.show.TTSLinstener;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;

public class TextToSpeech {
	private static String TAG = TextToSpeech.class.getSimpleName(); 	
	
	private Context mContext;	
	private TTSLinstener mLinstener;
	// 语音合成对象
	private SpeechSynthesizer mTts;
	// 默认发音人
	private String voicer="xiaoyan";	
	private String[] cloudVoicersEntries;
	private String[] cloudVoicersValue ;	
	// 缓冲进度
	private int mPercentForBuffering = 0;
	// 播放进度
	private int mPercentForPlaying = 0;	
	// 引擎类型
	private String mEngineType = SpeechConstant.TYPE_CLOUD;
	// 语音+安装助手类
	ApkInstaller mInstaller ;	
	private Toast mToast;
	private SharedPreferences mSharedPreferences;	
	
	public TextToSpeech(Context context, TTSLinstener linstener) {
		this.mContext = context;
		this.mLinstener = linstener;
	}
	
	
	/**
	 * 设置引擎类型
	 * @param 
	 * <p>type 0： auto  </p>
	 * <p> type 1: local </p>
	 * <p> type 2: cloud </p>
	 */
	public void setEngineType(int type){
		switch (type) {
		case 0:
			mEngineType = SpeechConstant.TYPE_AUTO;
			break;
		case 1:
			mEngineType = SpeechConstant.TYPE_LOCAL;
			break;
		case 2:
			mEngineType = SpeechConstant.TYPE_CLOUD;
			break;

		default:
			break;
		}
	}
	
	/**
	 * 初始化语音合成
	 */
	public void initSpeechSynthesizer(){
		mTts = SpeechSynthesizer.createSynthesizer(mContext, mTtsInitListener);		
		// 云端发音人名称列表
		cloudVoicersEntries = mContext.getResources().getStringArray(R.array.voicer_cloud_entries);
		cloudVoicersValue = mContext.getResources().getStringArray(R.array.voicer_cloud_values);				
		mSharedPreferences = mContext.getSharedPreferences(TtsSettings.PREFER_NAME, Context.MODE_PRIVATE);
		mToast = Toast.makeText(mContext,"",Toast.LENGTH_SHORT);		
		mInstaller = new ApkInstaller((Activity)mContext);
		mEngineType =  SpeechConstant.TYPE_LOCAL;
	}
	
	/**
	 * 检测是否安装
	 */
	public void checkInstall(){
		if (!SpeechUtility.getUtility().checkServiceInstalled()) {
			mInstaller.install();
		}
	}
	
	
	public void openTTSSettings(){
		Intent intent = new Intent(mContext, TtsSettings.class);
		mContext.startActivity(intent);
	}
	
	
	public void ttsSetting(){
		if(SpeechConstant.TYPE_CLOUD.equals(mEngineType)){
			
		}else{
			// 本地设置跳转到语音+中
			if (!SpeechUtility.getUtility().checkServiceInstalled()) {
				mInstaller.install();
			}else {
				SpeechUtility.getUtility().openEngineSettings(null);				
			}
		}
	}
	
	/**
	 * 
	 * 开始合成
	 *
	 * @param text
	 */
	public void start(String text){
		setParam();
		int code = mTts.startSpeaking(text, mTtsListener);
//		/** 
//		 * 只保存音频不进行播放接口,调用此接口请注释startSpeaking接口
//		 * text:要合成的文本，uri:需要保存的音频全路径，listener:回调接口
//		*/
//		String path = Environment.getExternalStorageDirectory()+"/tts.pcm";
//		int code = mTts.synthesizeToUri(text, path, mTtsListener);
		
		if (code != ErrorCode.SUCCESS) {
			if(code == ErrorCode.ERROR_COMPONENT_NOT_INSTALLED){
				//未安装则跳转到提示安装页面
				mInstaller.install();
			}else {
				mLinstener.onError("语音合成失败,错误码: " + code);
				showTip("语音合成失败,错误码: " + code);	
			}
		}
	}
	
	/**
	 * 停止合成
	 */
	public void stop(){
		mTts.stopSpeaking();
	}
	
	/**
	 * 暂停播放
	 */
	public void pause(){
		mTts.pauseSpeaking();
	}
	/**
	 * 恢复播放
	 */
	public void resume(){
		mTts.resumeSpeaking();
	}
	
	public void selectSpeechPerson(){
		showPersonSelectDialog();
	}
	
	private int selectedNum=0;
	/**
	 * 发音人选择。
	 */
	private void showPersonSelectDialog() {
		if(mEngineType.equals( SpeechConstant.TYPE_CLOUD)){
			new AlertDialog.Builder(mContext).setTitle("在线合成发音人选项")
			.setSingleChoiceItems(cloudVoicersEntries, // 单选框有几项,各是什么名字
					selectedNum, // 默认的选项
					new DialogInterface.OnClickListener() { // 点击单选框后的处理
				public void onClick(DialogInterface dialog,
						int which) { // 点击了哪一项
					voicer = cloudVoicersValue[which];
//					if ("catherine".equals(voicer) || "henry".equals(voicer) || "vimary".equals(voicer)) {
//						 ((EditText) findViewById(R.id.tts_text)).setText(R.string.text_tts_source_en);
//					}else {
//						((EditText) findViewById(R.id.tts_text)).setText(R.string.text_tts_source);
//					}
					selectedNum = which;
					dialog.dismiss();
				}
			}).show();
		}
		if(mEngineType.equals( SpeechConstant.TYPE_LOCAL)){
			if (!SpeechUtility.getUtility().checkServiceInstalled()) {
				mInstaller.install();
			}else {
				SpeechUtility.getUtility().openEngineSettings(SpeechConstant.ENG_TTS);				
			}
		}
	}

	/**
	 * 初始化监听。
	 */
	private InitListener mTtsInitListener = new InitListener() {
		@Override
		public void onInit(int code) {
			Log.d(TAG, "InitListener init() code = " + code);
			if (code != ErrorCode.SUCCESS) {
				mLinstener.onError("初始化失败,错误码："+code);
        		showTip("初始化失败,错误码："+code);
        	} else {
				// 初始化成功，之后可以调用startSpeaking方法
        		// 注：有的开发者在onCreate方法中创建完合成对象之后马上就调用startSpeaking进行合成，
        		// 正确的做法是将onCreate中的startSpeaking调用移至这里
        		showTip("语音合成初始化完成");
        		mLinstener.onInitialized();
			}		
		}
	};

	/**
	 * 合成回调监听。
	 */
	private SynthesizerListener mTtsListener = new SynthesizerListener() {
		@Override
		public void onSpeakBegin() {
			mLinstener.onSpeakBegin();
			showTip("开始播放");
		}

		@Override
		public void onSpeakPaused() {
			mLinstener.onSpeakPaused();
			showTip("暂停播放");
		}

		@Override
		public void onSpeakResumed() {
			mLinstener.onSpeakResumed();
			showTip("继续播放");
		}

		@Override
		public void onBufferProgress(int percent, int beginPos, int endPos,
				String info) {
			// 合成进度
			mPercentForBuffering = percent;
			mLinstener.onBufferProgress(mPercentForBuffering);
		}

		@Override
		public void onSpeakProgress(int percent, int beginPos, int endPos) {
			// 播放进度
			mPercentForPlaying = percent;
			mLinstener.onSpeakProgress(mPercentForPlaying);
		}

		@Override
		public void onCompleted(SpeechError error) {
			if (error == null) {
				showTip("播放完成");
				mLinstener.onSpeechComplete();
			} else if (error != null) {
				mLinstener.onError(error.getErrorDescription());
				showTip(error.getPlainDescription(true));
			}
		}

		@Override
		public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
			
		}
	};

	private void showTip(final String str) {
		mToast.setText(str);
		mToast.show();
	}

	/**
	 * 参数设置
	 * @param param
	 * @return 
	 */
	private void setParam(){
		// 清空参数
		mTts.setParameter(SpeechConstant.PARAMS, null);
		// 根据合成引擎设置相应参数
		if(mEngineType.equals(SpeechConstant.TYPE_CLOUD)) {
			mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
			// 设置在线合成发音人
			mTts.setParameter(SpeechConstant.VOICE_NAME,voicer);
		}else {
			mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_LOCAL);
			// 设置本地合成发音人 voicer为空，默认通过语音+界面指定发音人。
			mTts.setParameter(SpeechConstant.VOICE_NAME,"");
		}
		//设置合成语速
		mTts.setParameter("50", "50");
		//设置合成音调
		mTts.setParameter("50", "50");
		//设置合成音量
		mTts.setParameter("50", "50");
		//设置播放器音频流类型
		mTts.setParameter("3", "3");
		
		// 设置播放合成音频打断音乐播放，默认为true
		mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");
		
		// 设置合成音频保存路径，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
		mTts.setParameter(SpeechConstant.PARAMS,"tts_audio_path="+Environment.getExternalStorageDirectory()+"/test.pcm");
	}
	
	/**
	 * 销毁语音合成服务
	 */
	public void destory(){
		mTts.stopSpeaking();
		// 退出时释放连接
		mTts.destroy();
	}
}
