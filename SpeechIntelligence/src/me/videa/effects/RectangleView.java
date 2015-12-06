package me.videa.effects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;

public class RectangleView extends AppView{
	
	public RectangleView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public RectangleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public RectangleView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 绘制矩形
	 * @param canvas  画布
	 * @param x       x
	 * @param y       y
	 * @param width   矩形宽度
	 * @param height  矩形高度
	 */
	protected void drawRect(Canvas canvas, float x, float y, float width, float height) {
		RectF rect = new RectF(x, y, x + width, y + height);
		canvas.drawRect(rect, mPaint);
	}

}
