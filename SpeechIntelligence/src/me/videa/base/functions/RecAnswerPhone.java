package me.videa.base.functions;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * 接听电话
 * @author Vicke Tang
 *
 */
public class RecAnswerPhone extends PhoneStateListener{
	
	TelephonyManager telMgr;  
	
	public RecAnswerPhone() {
		// TODO Auto-generated constructor stub
	}
	
	 @Override 
     public void onCallStateChanged(int state, String incomingNumber) {  
         if(state==TelephonyManager.CALL_STATE_IDLE)//挂断  
         {  
             Log.e("IDLE",incomingNumber);  
         }  
         else if(state==TelephonyManager.CALL_STATE_OFFHOOK)//接听  
         {  
             Log.e("OFFHOOK",incomingNumber);  
         }  
         else if(state==TelephonyManager.CALL_STATE_RINGING)//来电  
         {  
         	
         }  
         super.onCallStateChanged(state, incomingNumber);  
     }
	
	
	/**
	 * 
	 * @param type
	 */
	public void recAnswerPhone(Enum<?> type){
//		telMgr.
	}

}
