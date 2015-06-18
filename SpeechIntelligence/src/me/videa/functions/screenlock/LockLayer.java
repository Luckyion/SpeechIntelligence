package me.videa.functions.screenlock;

import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class LockLayer {
	private Activity mActivty;
	private WindowManager mWindowManager;
	private View mLockView;
	private LayoutParams mLockViewLayoutParams;
	private boolean isLocked;
	// 这个值具体用于实现全屏
	private final static int FLAG_APKTOOL_VALUE = 1280;

	public LockLayer(Activity act) {
		mActivty = act;
		init();
	}

	private void init() {
		isLocked = false;
		mWindowManager = mActivty.getWindowManager();
		mLockViewLayoutParams = new LayoutParams();
		mLockViewLayoutParams.width = LayoutParams.MATCH_PARENT;
		mLockViewLayoutParams.height = LayoutParams.MATCH_PARENT;
		// 这一行实现屏蔽Home
		mLockViewLayoutParams.type = LayoutParams.TYPE_SYSTEM_ERROR;
		mLockViewLayoutParams.flags = FLAG_APKTOOL_VALUE;
	}

	public synchronized void lock() {
		if (mLockView != null && !isLocked) {
			mWindowManager.addView(mLockView, mLockViewLayoutParams);
		}
		isLocked = true;
	}

	public synchronized void unlock() {
		if (mWindowManager != null && isLocked) {
			mWindowManager.removeView(mLockView);
		}
		isLocked = false;
	}

	public synchronized void setLockView(View v) {
		mLockView = v;
	}
}