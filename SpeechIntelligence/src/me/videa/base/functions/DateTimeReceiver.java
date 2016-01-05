package me.videa.base.functions;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;

import me.videa.utils.TimeUtils;
import me.videa.voice.show.HandlerWhat;
import me.videa.voice.show.TimeBean;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class DateTimeReceiver extends BroadcastReceiver{
	
	private final String TAG = "DateTimeReceiver";   

    private Handler mHandler;
	
	public DateTimeReceiver(Handler handler) {
		// TODO Auto-generated constructor stub
		this.mHandler = handler;
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		if(Intent.ACTION_TIME_TICK.equals(intent.getAction())){
			Log.d(TAG, "时间发生变化咯~");
			Message message = new Message();
			Bundle mBundle = new Bundle();
			mBundle.putSerializable("time", TimeUtils.getTimeBean());
			message.what = HandlerWhat.DATE_STATE;
			message.setData(mBundle);
			mHandler.sendMessage(message);
		}
	}

}
