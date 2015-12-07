package me.videa.effects;

import me.videa.application.MyApplication;
import me.videa.voice.show.ExtraBean;
import me.videa.voice.show.HandlerWhat;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
	private static int mNetCircleRadius = 225;
	private String mNetSting = "";
	private String mIp = "";

	public MainShowView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public MainShowView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MainShowView(Context context) {
		super(context);		
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if(mPaint == null){
			mPaint = new Paint();
			mPaint.setAntiAlias(true);
		}
		drawDateView(canvas);
		drawTemperView(canvas);
		drawPowerView(canvas);
		drawMicPhoneView(canvas);
		drawTextViewArea(canvas);
		drawNetStateView(canvas);
	}

	/**
	 * 绘制显示日期的组件
	 * 
	 * @param canvas
	 */
	public void drawDateView(Canvas canvas) {
		mX = 300;
		mY = 300;
		// 绘制矩形时间框线
//		mPaint.setColor(Color.parseColor(mColorBrighter));
//		mPaint.setStrokeWidth(10);
//		drawLine(canvas, mX - 50, mY, mX + 50, mY);

		mPaint.setColor(Color.parseColor(mColorBrighter));
		drawRect(canvas, mX, 0, rectWidth, rectHeight);

		mPaint.setColor(Color.BLACK);
		drawRect(canvas, mX + radiusRelative / 4, radiusRelative / 4, rectWidth
				- radiusRelative / 2, rectHeight - radiusRelative / 2);

		// 绘制圆形日期显示框线
		mCircleRadius = 200;
		mPaint.setColor(Color.parseColor(mColorBrighter));
		drawCircle(mX, mY, mCircleRadius, canvas);

		radius = mCircleRadius - radiusRelative;
		mPaint.setColor(Color.BLACK);
		drawCircle(mX, mY, radius, canvas);

		radius = radius - radiusRelative;
		mPaint.setColor(Color.parseColor(mColor));
		drawCircle(mX, mY, radius, canvas);

		radius = radius - radiusRelative;
		mPaint.setColor(Color.BLACK);
		drawCircle(mX, mY, radius, canvas);

//		mPaint.setColor(Color.BLACK);
//		mCircleRadius = radius + radiusRelative + 1;
//		drawArc(canvas, mX, mY, mCircleRadius, 0, 180);
		
	}
	
	/**
	 * 绘制显示温度的组件
	 * @param canvas
	 */
	public void drawTemperView(Canvas canvas){		
		mCircleRadius = 150;
		mX = mWidth - (3 * mCircleRadius / 2);
		mY = mCircleRadius;	
		mPaint.setColor(Color.parseColor(mColorBrighter));
		drawCircle(mX, mY, mCircleRadius, canvas);

		radius = mCircleRadius - radiusRelative;
		mPaint.setColor(Color.BLACK);
		drawCircle(mX, mY, radius, canvas);

		radius = radius - radiusRelative;
		mPaint.setColor(Color.parseColor(mColor));
		drawCircle(mX, mY, radius, canvas);

		radius = radius - radiusRelative - (radiusRelative / 2);
		mPaint.setColor(Color.BLACK);
		drawCircle(mX, mY, radius, canvas);

//		mPaint.setColor(Color.BLACK);
//		mCircleRadius = radius + (radiusRelative * 3 / 2) + 1;
//		drawArc(canvas, mX, mY, mCircleRadius, 0, 270);
	}
	
	/**
	 * 绘制电量显示
	 */
	public void drawPowerView(Canvas canvas){
		mCircleRadius = 150;
		mX = 300;
		mY = mHeight / 2;	
		mPaint.setColor(Color.parseColor(mColorDarker));
		drawCircle(mX, mY, mCircleRadius, canvas);

		radius = mCircleRadius - (radiusRelative / 2);
		mPaint.setColor(Color.BLACK);
		drawCircle(mX, mY, radius, canvas);

		radius = radius - radiusRelative;
		mPaint.setColor(Color.parseColor(mColor));
		drawCircle(mX, mY, radius, canvas);

		radius = radius - radiusRelative - (3 * radiusRelative / 2);
		mPaint.setColor(Color.BLACK);
		drawCircle(mX, mY, radius, canvas);
		
		radius = radius - radiusRelative;
		mPaint.setColor(Color.BLACK);
		drawCircle(mX, mY, radius, canvas);
		
		radius = radius - radiusRelative;
		mPaint.setColor(Color.BLACK);
		drawCircle(mX, mY, radius, canvas);

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
		mX = mWidth - (3 * mCircleRadius / 2);
		mY = mHeight - (3 * mCircleRadius);	
		mPaint.setColor(Color.parseColor(mColorBrighter));
		drawCircle(mX, mY, mCircleRadius, canvas);

		radius = mCircleRadius - radiusRelative;
		mPaint.setColor(Color.BLACK);
		drawCircle(mX, mY, radius, canvas);

		radius = radius - radiusRelative;
		mPaint.setColor(Color.parseColor(mColor));
		drawCircle(mX, mY, radius, canvas);

		radius = radius - radiusRelative - (radiusRelative / 2);
		mPaint.setColor(Color.BLACK);
		drawCircle(mX, mY, radius, canvas);
	}
	
	/**
	 * 绘制文本显示组件
	 * @param canvas
	 */
	public void drawTextViewArea(Canvas canvas){
		rectTextHeight = mHeight / 3;
		rectTextWidth = mWidth / 2;
		mX = mHeight / 4;
		mY = mWidth / 2;
		mPaint.setColor(Color.parseColor(mColorBrighter));
		drawRect(canvas, mX, mY, rectTextWidth, rectTextHeight);

		mPaint.setColor(Color.BLACK);
		drawRect(canvas, mX + radiusRelative / 2, mY + radiusRelative / 2, rectTextWidth
				- radiusRelative , rectTextHeight - radiusRelative);
	}
	/**
	 * 绘制网络组件
	 * @param canvas
	 */
	public void drawNetStateView(Canvas canvas){
		mX = 0;
		mY = mHeight - (mNetCircleRadius / 2);
		mPaint.setColor(Color.parseColor(mColorBrighter));
		drawCircle(mX, mY, mNetCircleRadius, canvas);

		radius = mNetCircleRadius - radiusRelative;
		mPaint.setColor(Color.BLACK);
		drawCircle(mX, mY, radius, canvas);

		radius = radius - radiusRelative;
		mPaint.setColor(Color.parseColor(mColor));
		drawCircle(mX, mY, radius, canvas);

		radius = radius - (2 * radiusRelative);
		mPaint.setColor(Color.BLACK);
		drawCircle(mX, mY, radius, canvas);
		
		radius = radius - radiusRelative;
		mPaint.setColor(Color.parseColor(mColor));
		drawCircle(mX, mY, radius, canvas);
		
		mPaint.setColor(Color.RED);		
		mPaint.setStrokeWidth(5);
		mPaint.setTextSize(50);
		drawText(canvas, mNetSting, mX + 5, mY - 50);
		
		mPaint.setColor(Color.parseColor(mColor));
		mPaint.setTextSize(100);
		drawText(canvas, mIp, mX + mNetCircleRadius, mY - 25);
	}

	/**
	 * 根据数据重绘组件
	 */
	public void reDraw(int which, ExtraBean extra) {
		switch (which) {
		case HandlerWhat.NET_STATE:		
			Log.d(TAG, "NET_STATE : " + extra.getSingleStrength());
			mNetSting = extra.getSingleStrength() + "";
			mIp = extra.getIp();
			break;

		default:
			break;
		}
		postInvalidate();
	}

}
