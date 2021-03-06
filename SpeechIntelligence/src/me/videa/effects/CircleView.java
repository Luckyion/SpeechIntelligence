package me.videa.effects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

public class CircleView extends RectangleView{	
	
	public static String mCircleShadowColor = "#010101";
	public static float mCircleRadius = 150;
	public static float mX = mCircleRadius;
	public static float mY = mCircleRadius;	
	
	public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Autogenerated constructor stub
	}

	public CircleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Autogenerated constructor stub
	}

	public CircleView(Context context) {
		super(context);
		// TODO Autogenerated constructor stub
	}
	
	/**
	 * 缁樺埗鍦嗗舰
	 * @param x  x鍧愭爣
	 * @param y  y鍧愭爣
	 * @param radius  鍗婂緞
	 * @param paint   鐢荤瑪
	 * @param canvas  鐢诲竷
	 */
	protected void drawCircle(float x, float y, float radius, Canvas canvas) {
		mPaint.setShadowLayer(2, 3, 3,
				Color.parseColor(mCircleShadowColor));	
		canvas.drawCircle(x, y, radius, mPaint);	
	}
	
	/**
	 * 鑾峰彇鎵囧舰
	 * @param canvas  鐢诲竷
	 * @param o_x   x璧风偣
	 * @param o_y   y缁堢偣
	 * @param r     鍗婂緞
	 * @param startangel  鍒濆§嬭§掑害
	 * @param endangel    鏈€缁堣§掑害
	 * @param paint       鐢荤瑪 
	 */
	public void drawArc(Canvas canvas, float o_x, float o_y, float r,
			float startangel, float endangel) {
		RectF rect = new RectF(o_x - r, o_y - r, o_x + r, o_y + r);
		Path path = new Path();
		path.moveTo(o_x, o_y);
		path.lineTo((float) (o_x + r * Math.cos(startangel * Math.PI / 180)),
				(float) (o_y + r * Math.sin(startangel * Math.PI / 180)));
		path.lineTo((float) (o_x + r * Math.cos(endangel * Math.PI / 180)),
				(float) (o_y + r * Math.sin(endangel * Math.PI / 180)));
		path.addArc(rect, startangel, endangel - startangel);
		canvas.clipPath(path);
		canvas.drawCircle(o_x, o_y, mCircleRadius, mPaint);
	}

}

