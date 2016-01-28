package me.videa.functions.nfc;

import java.nio.charset.Charset;

import me.videa.utils.DebugUtil;
import me.videa.voice.R;
import me.videa.voice.show.HandlerWhat;
import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcAdapter.OnNdefPushCompleteCallback;
import android.nfc.NfcEvent;
import android.os.Handler;
import android.os.Parcelable;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class NfcView extends RelativeLayout implements
		CreateNdefMessageCallback, OnNdefPushCompleteCallback {

	private final static String TAG = "NfcView";

	private LayoutInflater mInflater;
	private LinearLayout mLayout;
	private Handler mHandler;
	
	
	public NfcView(Context context, Handler handler) {
		super(context);
		mHandler = handler;
		mInflater = LayoutInflater.from(context);
		mLayout = (LinearLayout) mInflater.inflate(R.layout.view_nfc_main, null);
	}

	@Override
	public void onNdefPushComplete(NfcEvent event) {
		// TODO Auto-generated method stub
		mHandler.obtainMessage(HandlerWhat.NFC_STATUS).sendToTarget();
	}

	@Override
	public NdefMessage createNdefMessage(NfcEvent event) {
		Time time = new Time(); 
        time.setToNow(); 
        String text = ("Beam me up!\n\n" + 
                "Beam Time: " + time.format("%H:%M:%S")); 
        NdefMessage msg = new NdefMessage( 
                new NdefRecord[] { createMimeRecord( 
                        "application/com.example.android.beam", text.getBytes()) 
        }); 
        return msg;
	}
	
	/** 
     * Parses the NDEF Message from the intent and prints to the TextView 
     */
    void processIntent(Intent intent) { 
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra( 
                NfcAdapter.EXTRA_NDEF_MESSAGES); 
        // only one message sent during the beam 
        NdefMessage msg = (NdefMessage) rawMsgs[0]; 
        // record 0 contains the MIME type, record 1 is the AAR, if present 
        DebugUtil.d(TAG, new String(msg.getRecords()[0].getPayload()));
    } 
   
   /** 
     * Creates a custom MIME type encapsulated in an NDEF record 
     * 
     * @param mimeType 
     */
    public NdefRecord createMimeRecord(String mimeType, byte[] payload) { 
        byte[] mimeBytes = mimeType.getBytes(Charset.forName("US-ASCII")); 
        NdefRecord mimeRecord = new NdefRecord( 
                NdefRecord.TNF_MIME_MEDIA, mimeBytes, new byte[0], payload); 
        return mimeRecord; 
    }

}
