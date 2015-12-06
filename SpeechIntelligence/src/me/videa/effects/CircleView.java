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
		// TODO Auto-generated constructor stub
	}

	public CircleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CircleView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 绘制圆形
	 * @param x  x坐标
	 * @param y  y坐标
	 * @param radius  半径
	 * @param paint   画笔
	 * @param canvas  画布
	 */
	protected void drawCircle(float x, float y, float radius, Canvas canvas) {
		mPaint.setShadowLayer(2, 3, 3,
				Color.parseColor(mCircleShadowColor));	
		canvas.drawCircle(x, y, radius, mPaint);	
	}
	
	/**
	 * 获取扇形
	 * @param canvas  画布
	 * @param o_x   x起点
	 * @param o_y   y终点
	 * @param r     半径
	 * @param startangel  初始角度
	 * @param endangel    最终角度
	 * @param paint       画笔 
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
