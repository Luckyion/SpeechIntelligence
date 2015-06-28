package me.videa.uncaughtexceptionhandler;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Looper;
import android.util.Log;

/**
 * ����ȫ���쳣����Application����δ�ܲ�����쳣</br>
 * ���������쳣��ɳ�����֡�Ӧ�ó�����ֹͣ���С��Ĵ���ʱ</br>
 * �����Զ���{@link Application}(��MyApplication)�̳�Application</br>
 * ����{@link AndroidManifest.xml}�н�<application></application>��ǩ�޸�</br>
 * �Զ����Application��Ӧ�ó����ڳ��ֲ��ɿ��쳣ʱ�������Զ���Application</br>
 * ����������Ӧ���쳣���񣬲��������������ȡ�豸��Ϣ����ȡ�쳣��Ϣ</br>
 * ���Ѻõ���ʾ�û������˲��ɿص��쳣������Ӧ�ó����˳��� </br>
 * @author Vickie Tang
 * @since version 1.0
 * 
 */
public class UncaughtException implements UncaughtExceptionHandler {
    private final static String TAG = "UncaughtException";
    private static UncaughtException mUncaughtException;
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private UncaughtException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * ��Application����ͬһ����ģʽ��������̴߳���ʱ�����쳣</br>
     * @return {@link UncaughtException} mUncaughtException
     */
    public synchronized static UncaughtException getInstance() {
        if (mUncaughtException == null) {
            mUncaughtException = new UncaughtException();
        }
        return mUncaughtException;
    }

    /**
     * ��ʼ������Ĭ�ϵ�δ�����쳣�Ĵ�����, �����̷߳���δ�����쳣ʱ����
     */
    public void init() {
        Thread.setDefaultUncaughtExceptionHandler(mUncaughtException);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        // TODO Auto-generated method stub
        Log.e(TAG, "uncaughtException thread : " + thread + "||name=" + thread.getName() + "||id=" + thread.getId() + "||exception=" + ex);
        showDialog() ;
    }
    
    
    /**
     * ��ȡ�豸������Ϣ
     * @return �豸��Ϣ�����豸�汾�š�ʶ�����
     */
    private HashMap<String, String> getDeviceMessages(){
    	return new DeviceMessages(context).getDevicesMessages();
    }

    private void showDialog() {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                new AlertDialog.Builder(context).setTitle("��ʾ").setCancelable(false).setMessage("��ү�ұ�����...")
                        .setNeutralButton("��֪����", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.exit(0);
                            }
                        }).create().show();
                Looper.loop();
            }
        }.start();
    }
}
