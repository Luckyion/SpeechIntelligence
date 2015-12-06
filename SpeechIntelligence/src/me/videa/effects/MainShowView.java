package me.videa.effects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

public class MainShowView extends CircleView {

	private float radius;
	private final float radiusRelative = 20;
	private final float rectWidth = 400;
	private final float rectHeight = 150;

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
		if (mPaint == null) {
			mPaint = new Paint();
			mPaint.setAntiAlias(true);
		}
		drawDateView(canvas);
		drawTemperView(canvas);
	}

	/**
	 * 绘制显示日期的组件
	 * 
	 * @param canvas
	 */
	public void drawDateView(Canvas canvas) {
		mX = 200;
		mY = 300;
		// 绘制矩形时间框线
//		mPaint.setColor(Color.parseColor(mColorBrighter));
//		mPaint.setStrokeWidth(10);
//		drawLine(canvas, mX - 50, mY, mX + 50, mY);
//
//		mPaint.setColor(Color.parseColor(mColorBrighter));
//		drawRect(canvas, mX, 0, rectWidth, rectHeight);
//
//		mPaint.setColor(Color.BLACK);
//		drawRect(canvas, mX + radiusRelative / 4, radiusRelative / 4, rectWidth
//				- radiusRelative / 2, rectHeight - radiusRelative / 2);

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

		mPaint.setColor(Color.BLACK);
		mCircleRadius = radius + radiusRelative + 1;
		drawArc(canvas, mX, mY, mCircleRadius, 0, 180);
		
	}
	
	/**
	 * 绘制显示温度的组件
	 * @param canvas
	 */
	public void drawTemperView(Canvas canvas){
		mX = 200;
		mY = 300;
		mCircleRadius = 120;
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

		mPaint.setColor(Color.BLACK);
		mCircleRadius = radius + radiusRelative + 1;
		drawArc(canvas, mX, mY, mCircleRadius, 0, 180);
	}

	/**
	 * 根据数据重绘组件
	 */
	public void reDraw() {

	}

}
