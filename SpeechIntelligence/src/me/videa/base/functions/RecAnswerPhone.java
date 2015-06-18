package me.videa.base.functions;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * 接听电话
 * @author pactera
 *
 */
public class RecAnswerPhone {
	
	TelephonyManager telMgr;  
    CallStateListener stateListner; 
	
	public RecAnswerPhone() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 
	 * @param type
	 */
	public void recAnswerPhone(Enum<?> type){
		
	}
	
	/** 
     * 监视电话状态 
     * @author GV 
     * 
     */ 
    public class CallStateListener extends PhoneStateListener {  
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
    } 

}
