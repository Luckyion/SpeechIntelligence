package me.videa.effects;

import me.videa.application.MyApplication;
import me.videa.utils.TimeUtils;
import me.videa.voice.show.HandlerWhat;
import me.videa.voice.show.beans.ExtraBean;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.util.Log;

public class MainShowView extends CircleView {
	
	
	private final static String TAG = "MainShowView";

	private float radius;
	private final float radiusRelative = 20;
	private final float rectWidth = 400;
	private final float rectHeight = 150;
	private float rectTextWidth;
	private float rectTextHeight;	
	
	private final int mWidth = MyApplication.mScreenWidth;
	private final int mHeight = MyApplication.mScreenHeight;	
	private final float mTextX = mHeight / 4;
	private final float mTextY = mWidth / 2;
	private static final int mNetCircleRadius = 200;
	private static final int mTemperCircleRadius = 150;
	private static final int mPowerCircleRadius = mTemperCircleRadius;
	
	
	private final float mLeftX = 30;
	private final float mLeftY = 30;
	private final float mDateX = 300;
	private final float mDateY = 300;
	private final float mTemperX = mWidth - (3 * mCircleRadius / 2);
	private final float mTemperY = mCircleRadius;
	private final float mPowerX = 300;
	private final float mPowerY = mHeight / 2;
	private final float mNetworkX = 0;
	private final float mNetworkY = mHeight - (mNetCircleRadius / 2);

	private final float mMicX = mWidth - (3 * mCircleRadius / 2);
	private final float mMicY = mHeight - (3 * mCircleRadius);	

	
	private String mTime = "00:00";
	private String mWeek = "Monday";
	private String mDay = "14";
	private String mMonth = "DEC";
	private String mTemper = "3";
	private final String mTemperC = "°";
	private String mMic = "0";
	private final String mPowerC = "%";
	private String mPower = "100";
	
	private final String mTemperTip = "Temperature";
	private final String mPowerTip = "Power";
	private final String mNetStateTip = "Network State";
	private final String mMicTip = "micro-phone";
	
	private final int mTextSize = 30;
	private final int mLeftCircleRadius = 30;
	
	private String mNetSting = "";
	private String mIp = "";

	public MainShowView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public MainShowView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MainShowView(Context context) {
		super(context);		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(mPaint == null){
			mPaint = new Paint();
			mPaint.setAntiAlias(true);
		}
		mPaint.reset();
		mPaint.setAntiAlias(true);
		drawLeft(canvas);
		drawHeartLine(canvas);
		drawDateView(canvas);
		drawTemperView(canvas);
		drawPowerView(canvas);
		drawMicPhoneView(canvas);
		drawTextViewArea(canvas);
		drawNetStateView(canvas);
		drawTime(canvas);
		drawTips(canvas);
		drawPower(canvas);
		drawTemper(canvas);
		DrawMic(canvas);		
	}

	/**
	 * 绘制时间显示组件
	 * 
	 * @param canvas
	 */
	public void drawDateView(Canvas canvas) {
		// 绘制时间显示的矩形
//		mPaint.setColor(Color.parseColor(mColorBrighter));
//		mPaint.setStrokeWidth(10);
//		drawLine(canvas, mX  50, mY, mX + 50, mY);

		mPaint.setColor(Color.parseColor(mColorBrighter));
		drawRect(canvas, mDateX, 0, rectWidth, rectHeight);

		mPaint.setColor(Color.BLACK);
		drawRect(canvas, mDateX + radiusRelative / 4, radiusRelative / 4, rectWidth
				- radiusRelative / 2, rectHeight - radiusRelative / 2);

		// 绘制日期显示的圆
		mCircleRadius = 200;
		mPaint.setColor(Color.parseColor(mColorBrighter));
		drawCircle(mDateX, mDateY, mCircleRadius, canvas);

		radius = mCircleRadius - radiusRelative;
		mPaint.setColor(Color.BLACK);
		drawCircle(mDateX, mDateY, radius, canvas);

		radius = radius - radiusRelative;
		mPaint.setColor(Color.parseColor(mColor));
		drawCircle(mDateX, mDateY, radius, canvas);

		radius = radius - radiusRelative;
		mPaint.setColor(Color.BLACK);
		drawCircle(mDateX, mDateY, radius, canvas);

//		mPaint.setColor(Color.BLACK);
//		mCircleRadius = radius + radiusRelative + 1;
//		drawArc(canvas, mX, mY, mCircleRadius, 0, 180);
		
	}
	
	/**
	 * 绘制温度组件
	 * @param canvas
	 */
	public void drawTemperView(Canvas canvas){
		
		mPaint.setColor(Color.parseColor(mColorBrighter));
		drawCircle(mTemperX, mTemperY, mTemperCircleRadius, canvas);

		radius = mTemperCircleRadius - radiusRelative;
		mPaint.setColor(Color.BLACK);
		drawCircle(mTemperX, mTemperY, radius, canvas);

		radius = radius - radiusRelative;
		mPaint.setColor(Color.parseColor(mColor));
		drawCircle(mTemperX, mTemperY, radius, canvas);

		radius = radius - radiusRelative - (radiusRelative / 2);
		mPaint.setColor(Color.BLACK);
		drawCircle(mTemperX, mTemperY, radius, canvas);

//		mPaint.setColor(Color.BLACK);
//		mCircleRadius = radius + (radiusRelative * 3 / 2) + 1;
//		drawArc(canvas, mX, mY, mCircleRadius, 0, 270);
	}
	
	/**
	 * 绘制电源组件
	 */
	public void drawPowerView(Canvas canvas){
		mPaint.setColor(Color.parseColor(mColorDarker));
		drawCircle(mPowerX, mPowerY, mPowerCircleRadius, canvas);

		radius = mPowerCircleRadius - (radiusRelative / 2);
		mPaint.setColor(Color.BLACK);
		drawCircle(mPowerX, mPowerY, radius, canvas);

		radius = radius - radiusRelative;
		mPaint.setColor(Color.parseColor(mColor));
		drawCircle(mPowerX, mPowerY, radius, canvas);

		radius = radius - radiusRelative - (3 * radiusRelative / 2);
		mPaint.setColor(Color.BLACK);
		drawCircle(mPowerX, mPowerY, radius, canvas);
		
		radius = radius - radiusRelative;
		mPaint.setColor(Color.BLACK);
		drawCircle(mPowerX, mPowerY, radius, canvas);
		
		radius = radius - radiusRelative;
		mPaint.setColor(Color.BLACK);
		drawCircle(mPowerX, mPowerY, radius, canvas);

//		mPaint.setColor(Color.BLACK);
//		mCircleRadius = radius + (radiusRelative * 3 / 2) + 1;
//		drawArc(canvas, mX, mY, mCircleRadius, 90, 180);
	}
	
	/**
	 * 绘制麦克风组件
	 * @param canvas
	 */
	public void drawMicPhoneView(Canvas canvas){
		mCircleRadius = 150;
		mPaint.setColor(Color.parseColor(mColorBrighter));
		drawCircle(mMicX, mMicY, mCircleRadius, canvas);

		radius = mCircleRadius - radiusRelative;
		mPaint.setColor(Color.BLACK);
		drawCircle(mMicX, mMicY, radius, canvas);

		radius = radius - radiusRelative;
		mPaint.setColor(Color.parseColor(mColor));
		drawCircle(mMicX, mMicY, radius, canvas);

		radius = radius - radiusRelative - (radiusRelative / 2);
		mPaint.setColor(Color.BLACK);
		drawCircle(mMicX, mMicY, radius, canvas);
	}
	
	/**
	 * 绘制文本区域
	 * @param canvas
	 */
	public void drawTextViewArea(Canvas canvas){
		rectTextHeight = mHeight / 3;
		rectTextWidth = mWidth / 2;		
		mPaint.setColor(Color.parseColor(mColorBrighter));
		drawRect(canvas, mTextX, mTextY, rectTextWidth, rectTextHeight);

		mPaint.setColor(Color.BLACK);
		drawRect(canvas, mTextX + radiusRelative / 2, mTextY + radiusRelative / 2, rectTextWidth
				- radiusRelative , rectTextHeight - radiusRelative);
	}
	/**
	 * 绘制网络状态组件
	 * @param canvas
	 */
	public void drawNetStateView(Canvas canvas){
		mPaint.setColor(Color.parseColor(mColorBrighter));
		drawCircle(mNetworkX, mNetworkY, mNetCircleRadius, canvas);

		radius = mNetCircleRadius - radiusRelative;
		mPaint.setColor(Color.BLACK);
		drawCircle(mNetworkX, mNetworkY, radius, canvas);

		radius = radius - radiusRelative;
		mPaint.setColor(Color.parseColor(mColor));
		drawCircle(mNetworkX, mNetworkY, radius, canvas);

		radius = radius - (2 * radiusRelative);
		mPaint.setColor(Color.BLACK);
		drawCircle(mNetworkX, mNetworkY, radius, canvas);
		
		radius = radius - radiusRelative;
		mPaint.setColor(Color.parseColor(mColor));
		drawCircle(mNetworkX, mNetworkY, radius, canvas);
		
		mPaint.setColor(Color.RED);		
		mPaint.setStrokeWidth(5);
		mPaint.setTextSize(50);
		drawText(canvas, mNetSting, mNetworkX + 5, mNetworkY);
		
		mPaint.setColor(Color.parseColor(mColor));
		mPaint.setTextSize(mTextSize);
		drawText(canvas, mIp, mNetworkX + mNetCircleRadius, mNetworkY);
	}
	
	/**
	 * Draw Time
	 * @param canvas
	 */
	private void drawTime(Canvas canvas){
		mPaint.setColor(Color.parseColor(mColorBrighter));
		mPaint.setTextAlign(Align.CENTER);
		mPaint.setTextSize(150);
		drawText(canvas, mDay, mDateX, mDateY);
		mPaint.setColor(Color.parseColor(mColor));
		mPaint.setTextSize(40);
		drawText(canvas, mMonth, mDateX, mDateY + 80);
		mPaint.setTextSize(100);
		drawText(canvas, mTime, mDateX + (3 * mCircleRadius) / 2, mDateY - (mCircleRadius + 2 * radiusRelative));
		mPaint.setTextSize(50);
		drawText(canvas, mWeek, mDateX + 2 * mCircleRadius, mDateY - mCircleRadius + 60);
	}
	
	/**
	 * Draw Tips
	 * @param canvas
	 */
	private void drawTips(Canvas canvas){
		mPaint.setTextSize(mTextSize);
		mPaint.setColor(Color.parseColor(mColor));
		drawText(canvas, mTemperTip, mTemperX, mTemperY + mTemperCircleRadius + radiusRelative);
		drawText(canvas, mPowerTip, mPowerX, mPowerY + mPowerCircleRadius + radiusRelative);
		drawText(canvas, mMicTip, mMicX, mMicY + mPowerCircleRadius + radiusRelative);
	}
	
	/**
	 * Draw temper
	 * @param canvas
	 */
	private void drawTemper(Canvas canvas){
		mPaint.setColor(Color.parseColor(mColor));
		mPaint.setTextSize(80);
		mPaint.setTextAlign(Align.CENTER);
		drawText(canvas, mTemper + mTemperC, mTemperX, mTemperY + 40);
	}
	/**
	 * Draw Power
	 * @param canvas
	 */
	private void drawPower(Canvas canvas){
		mPaint.setColor(Color.parseColor(mColor));
		mPaint.setTextSize(50);
		mPaint.setTextAlign(Align.CENTER);
		drawText(canvas, mPower + mPowerC, mPowerX, mPowerY + 25);
	}
	/**
	 * Draw Mic status
	 * @param canvas
	 */
	private void DrawMic(Canvas canvas){
		mPaint.setColor(Color.parseColor(mColor));
		mPaint.setTextSize(80);
		mPaint.setTextAlign(Align.CENTER);
		drawText(canvas, mMic, mMicX, mMicY + 40);
	}
	
	private void drawLeft(Canvas canvas){
		mPaint.setColor(Color.parseColor(mColor));
		drawLine(canvas, mLeftX, mLeftY, 
				mLeftX, mNetworkY);
		mPaint.setColor(Color.parseColor(mColorBrighter));
		drawCircle(mLeftX, mLeftY, 
				mLeftCircleRadius, canvas);		
		drawCircle(mLeftX, mLeftY + 120, 
				mLeftCircleRadius, canvas);
		drawCircle(mLeftX, mDateY, 
				mLeftCircleRadius, canvas);
		drawCircle(mLeftX, mPowerY, 
				mLeftCircleRadius, canvas);
	}
	
	/**
	 * Draw Heart Line
	 * @param canvas
	 */
	private void drawHeartLine(Canvas canvas){
		mPaint.setColor(Color.parseColor(mColorDarker));
		mPaint.setStrokeWidth(5);
		drawLine(canvas, mPowerX, mPowerY, mPowerX, mMicY);
		drawLine(canvas, mPowerX, mMicY, mMicX, mMicY);
	}

	/**
	 * 重绘组件
	 */
	public void reDraw(int which, ExtraBean extra) {
		switch (which) {
		case HandlerWhat.NET_STATE:		
			Log.d(TAG, "NET_STATE : " + extra.getSingleStrength());
			mNetSting = extra.getSingleStrength() + "";
			mIp = extra.getIp();
			break;
		case HandlerWhat.BATTERY_STATE:
			Log.d(TAG, "Battery level : " + extra.getBatteryLevel());
			mPower = extra.getBatteryLevel() + "";
			break;
		case HandlerWhat.DATE_STATE:
			mMonth = TimeUtils.getMonth(extra.getTimeBean().getMonth());
			mDay = extra.getTimeBean().getDay() + "";
			mWeek = TimeUtils.getWeek(extra.getTimeBean().getWeek());
			mTime = TimeUtils.getMinute(extra.getTimeBean().getHour()) + ":" + TimeUtils.getMinute(extra.getTimeBean().getMinite());
			break;
		case HandlerWhat.RECOGNITION_VOLUME_STATE:
			mMic = extra.getRecognitionBean().getProgress() + "";
			break;
		case HandlerWhat.TTS_STATE:
			mMic = extra.getTtsBean().getProgress() + "";
			break;
		default:
			break;
		}
		postInvalidate();
	}

}

