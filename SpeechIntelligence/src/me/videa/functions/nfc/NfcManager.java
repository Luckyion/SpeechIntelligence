package me.videa.functions.nfc;

import android.content.Context;
import android.nfc.NfcAdapter;
import android.widget.Toast;

public class NfcManager {
	
	NfcAdapter mAdapter;
	
	public NfcManager() {
		// TODO Auto-generated constructor stub
	}
	
	public NfcAdapter getNfcAdapter(Context context){
		mAdapter = NfcAdapter.getDefaultAdapter(context);  
	    if (mAdapter == null) {  
	        return null;  
	    }  
	    if (mAdapter != null && !mAdapter.isEnabled()) {  
	    	Toast.makeText(context, "请在系统设置中先启用NFC功能！", Toast.LENGTH_LONG).show();
	        return null;  
	    }
	    return mAdapter;
	}	
	
	
	
	

}
