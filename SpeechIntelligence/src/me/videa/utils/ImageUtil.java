package me.videa.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.util.Base64;

/**
 * bitmap操作工具类
 * 
 * @author Administrator
 * 
 */
public class ImageUtil {

	/**
	 * 把bitmap转换成String 加密
	 * 
	 * @param bm
	 *            bitmap
	 * @return 转成的String字符串
	 */
	public static String bitmapToString(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] b = baos.toByteArray();
		return Base64.encodeToString(b, Base64.DEFAULT);

	}

	/**
	 * 把bitmap转换成byte数组
	 * 
	 * @param bm
	 *            bitmap
	 * @return 转成的byte数组
	 */
	public static byte[] bitmapToByteArr(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] b = baos.toByteArray();
		return b;

	}

	/**
	 * 把String字符串转为bitmap 加密
	 * 
	 * @param str
	 *            bitm转成的String字符串
	 * @return String字符串转成的bitmap
	 */
	public static Bitmap stringToBitmap(String str) {
		byte[] b = Base64.decode(str, Base64.DEFAULT);
		return BitmapFactory.decodeByteArray(b, 0, b.length);
	}

	/**
	 * byte数组转为bitmap
	 * 
	 * @param b
	 *            byte数组
	 * @return bitmap对象
	 */
	public Bitmap ByteArrToBimap(byte[] b) {
		if (b.length != 0) {
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		} else {
			return null;
		}
	}

	/**
	 * bitmap转为drawable
	 * 
	 * @param context
	 *            上下文
	 * @param bm
	 *            bitmap对象
	 * @return drawable对象
	 */
	public static Drawable BitmapToDrawable(Context context, Bitmap bm) {
		BitmapDrawable bd = new BitmapDrawable(context.getResources(), bm);
		return bd;
	}

	/**
	 * drawable缩放
	 * 
	 * @param drawable
	 *            drawable对象
	 * @param w
	 *            预设的宽
	 * @param h
	 *            预设的高
	 * @return drawable对象
	 */
	public static Drawable scaleDrawable(Drawable drawable, int w, int h) {
		int width = drawable.getIntrinsicWidth();
		int height = drawable.getIntrinsicHeight();
		// drawable转换成bitmap
		Bitmap oldbmp = drawableToBitmap(drawable);
		// 创建操作图片用的Matrix对象
		Matrix matrix = new Matrix();
		// 计算缩放比例
		float sx = ((float) w / width);
		float sy = ((float) h / height);
		// 设置缩放比例
		matrix.postScale(sx, sy);
		// 建立新的bitmap，其内容是对原bitmap的缩放后的图
		Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
				matrix, true);
		return new BitmapDrawable(newbmp);
	}

	/**
	 * 把bitmap写入文件
	 * 
	 * @param bmp
	 *            bitmap
	 * @param width
	 *            宽
	 * @param height
	 *            高
	 * @param path
	 *            路径
	 */
	public static void writeBitmapPhoto(Bitmap bmp, int width, int height,
			String path) {
		File file = new File(path);
		try {
			Bitmap bm = Bitmap.createBitmap(bmp, 0, 0, width, height);
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(file));
			if (bm.compress(Bitmap.CompressFormat.JPEG, 100, bos)) {
				bos.flush();
				bos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 把drawable转为bitmap
	 * 
	 * @param drawable
	 *            drawable对象
	 * @return bitmap
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {
		BitmapDrawable bd = (BitmapDrawable) drawable;
		Bitmap bm = bd.getBitmap();
		return bm;
	}

	/**
	 * 缩小图片的方法
	 * 
	 * @param bitmap
	 *            图片对象
	 * @param targetWidth
	 *            预设的缩小的宽
	 * @param targetHeight
	 *            预设的缩小的高
	 * @return Bitmap 缩小的图片对象
	 */
	public static Bitmap scaleBitmap(Bitmap bitmap, int targetWidth,
			int targetHeight) {
		System.gc();
		Bitmap sourceBitmap = bitmap;
		Bitmap scaleBitmap = null;
		float bmpWidth = sourceBitmap.getWidth();
		float bmpHeight = sourceBitmap.getHeight();

		int newTargetHeight = 0;
		int newtargetWidth = 0;

		newTargetHeight = (int) ((targetWidth * bmpHeight) / bmpWidth);

		newtargetWidth = (int) ((targetHeight * bmpWidth) / bmpHeight);

		if (bmpWidth < targetWidth && bmpHeight < targetHeight) {
			return sourceBitmap;
		} else if (newTargetHeight > targetWidth) {
			scaleBitmap = Bitmap.createScaledBitmap(bitmap, targetWidth,
					newTargetHeight, false);
		} else {
			scaleBitmap = Bitmap.createScaledBitmap(bitmap, newtargetWidth,
					targetHeight, false);
		}
		return scaleBitmap;
	}

	/**
	 * 得到指定宽高的图片文件的缩略图
	 * 
	 * @param imagePath
	 *            图片的路径
	 * @param width
	 *            缩略图的宽度
	 * @param height
	 *            缩略图的高度
	 * @return 缩略图
	 */
	public static Bitmap getImageThumbnail(String imagePath, int width,
			int height) {
		Bitmap bitmap = null;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_4444;
		options.inPurgeable = true;
		options.inInputShareable = true;
		options.inJustDecodeBounds = true;
		// 获取这个图片的宽和高，注意此处的bitmap为null
		bitmap = BitmapFactory.decodeFile(imagePath, options);
		options.inJustDecodeBounds = false; // 设为 false
		// 计算缩放比
		int h = options.outHeight;
		int w = options.outWidth;
		int beWidth = w / width;
		int beHeight = h / height;
		int be = 1;
		if (beWidth < beHeight) {
			be = beWidth;
		} else {
			be = beHeight;
		}
		if (be <= 0) {
			be = 1;
		}
		options.inSampleSize = be;
		// 重新读入图片，读取缩放后的bitmap，注意这次要把options.inJustDecodeBounds 设为 false
		bitmap = BitmapFactory.decodeFile(imagePath, options);
		// 利用ThumbnailUtils来创建缩略图，这里要指定要缩放哪个Bitmap对象
		bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
				ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
		return bitmap;
	}

	/**
	 * 得到指定路径下的图片文件的缩略图
	 * 
	 * @param path
	 *            文件路径
	 */
	public static Bitmap getImageThumbnail(String path) {
		Bitmap bitmap = BitmapFactory.decodeFile(path, null);
		return bitmap;

	}

	/**
	 * 给bitmap画圆角
	 * 
	 * @param bitmap
	 *            bitmap
	 * @param oval
	 *            角度
	 * @return bitmap 圆角图片
	 */
	public static Bitmap getRoundCornerBitmap(Bitmap bitmap, int oval) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final Paint paint = new Paint();

		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		canvas.drawRoundRect(rectF, oval, oval, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rectF, paint);
		return output;
	}

	/**
	 * 把bitmap写入文件
	 * 
	 * @param path
	 *            文件路径
	 * @param bmp
	 *            bitmap对象
	 * @return 文件路径
	 */
	public static String writeBitmapPhoto(String path, Bitmap bmp) {
		File f = new File(path);
		FileOutputStream fOut = null;
		try {
			if (!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			f.createNewFile();
			fOut = new FileOutputStream(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bmp.compress(Bitmap.CompressFormat.PNG, 100, fOut);
		try {
			fOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	/**
	 * 把多媒体文件添加到图库
	 * 
	 * @param context
	 *            上下文
	 * @param path
	 *            文件路径
	 */
	public static void galleryAddPic(Context context, String path) {
		Intent mediaScanIntent = new Intent(
				Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		File f = new File(path);
		Uri contentUri = Uri.fromFile(f);
		mediaScanIntent.setData(contentUri);
		context.sendBroadcast(mediaScanIntent);
	}

}
