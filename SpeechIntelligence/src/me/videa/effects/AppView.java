package me.videa.effects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

public class AppView extends View{
	
	protected Paint mPaint;
	
	/**
	 * 最亮的圆圈颜色
	 */
	public static String mColorBrighter = "#00b7b5";
	/**
	 * 稍暗的圆圈颜色
	 */
	public static String mColor = "#01a7a5";
	/**
	 * 最暗的圆圈颜色
	 */
	public static String mColorDarker = "#003b39";

	public AppView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public AppView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public AppView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 绘制直线
	 * @param canvas   画布
 	 * @param pts      点数组
	 */
	protected void drawLine(Canvas canvas, float startX, float startY, float stopX, float stopY) {
		canvas.drawLine(startX, startY, stopX, stopY, mPaint);
	}
	
	/**
	 * 绘制文字 
	 * @param canvas  画布
	 * @param text    文字
	 * @param x       x 
	 * @param y       y
	 */
	protected void drawText(Canvas canvas, String text, float x, float y) {
		canvas.drawText(text, x, y, mPaint);
	}
	

}
