package me.videa.uncaughtexceptionhandler;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Looper;
import android.util.Log;

/**
 * 捕获全局异常，当Application出现未能捕获的异常</br>
 * 或者其他异常造成程序出现“应用程序已停止运行”的错误时</br>
 * 采用自定义{@link Application}(如MyApplication)继承Application</br>
 * 并在{@link AndroidManifest.xml}中将<application></application>标签修改</br>
 * 自定义的Application。应用程序在出现不可控异常时将调用自定义Application</br>
 * 在其中做相应的异常捕获，并做后续处理，如获取设备信息，获取异常信息</br>
 * 并友好的提示用户出现了不可控的异常，导致应用程序退出。 </br>
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
     * 与Application保持同一单例模式，避免多线程处理时发生异常</br>
     * @return {@link UncaughtException} mUncaughtException
     */
    public synchronized static UncaughtException getInstance() {
        if (mUncaughtException == null) {
            mUncaughtException = new UncaughtException();
        }
        return mUncaughtException;
    }

    /**
     * 初始化设置默认的未捕获异常的处理器, 任意线程发生未捕获异常时调用
     */
    public void init() {
        Thread.setDefaultUncaughtExceptionHandler(mUncaughtException);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        // TODO Auto-generated method stub
        Log.e(TAG, "uncaughtException thread : " + thread + "||name=" + thread.getName() + "||id=" + thread.getId() + "||exception=" + ex);
        ex.printStackTrace();
        System.exit(0);
//        showDialog() ;
    }
    
    
    /**
     * 获取设备基本信息
     * @return 设备信息，如设备版本号、识别码等
     */
    private HashMap<String, String> getDeviceMessages(){
    	return new DeviceMessages(context).getDevicesMessages();
    }

    private void showDialog() {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                new AlertDialog.Builder(context).setTitle("提示").setCancelable(false).setMessage("大爷我崩溃了...")
                        .setNeutralButton("我知道了", new OnClickListener() {
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
