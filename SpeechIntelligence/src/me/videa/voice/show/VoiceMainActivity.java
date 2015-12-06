package me.videa.voice.show;

import me.videa.voice.R;
import me.videa.voice.service.SpeechIntelligence;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;

@ContentView(R.layout.voice_main_activity)
public class VoiceMainActivity extends Activity{
	
//	@ViewInject(R.id.voiceText)
//	private ListView mVoiceListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);/*
		DateCircleView mCircleView = new DateCircleView(this);
		this.addContentView(mCircleView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		*/
//		startVoiceService();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}
	
	/**
	 * 启动语音服务
	 */
	void startVoiceService(){
		Intent mService = new Intent(this, SpeechIntelligence.class);
		startService(mService);
	}
	
	

}
