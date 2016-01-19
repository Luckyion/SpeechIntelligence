package me.videa.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

public class LocalFileUtil {

	/**
	 * 获取sd卡路径
	 * 
	 * @return
	 */
	public static String getSDCardPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();
		}
		return sdDir.toString();
	}

	/**
	 * 判断文件是否是图片
	 * 
	 * @param file
	 * @return
	 */
	public static boolean isImage(File file) {
		String fileName = file.getName().toLowerCase(Locale.CHINA);
		if (fileName.endsWith(".jpg") || fileName.endsWith(".png")
				|| fileName.endsWith("jpeg") || fileName.endsWith(".gif")) {
			return true;
		}
		return false;
	}
	
	public static Bitmap getBitmap(String filepath){
		Bitmap bm = null;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 4; 
		options.inTempStorage = new byte[1024*1024*5]; //5MB的临时存储空间
		try {			
			bm = BitmapFactory.decodeFile(filepath, options);
		} catch (OutOfMemoryError oomError) {
			// TODO: handle exception
			System.gc();
			bm = BitmapFactory.decodeFile(filepath, options);
		}		
		return bm;
	}
	
	/**
	 * 根据文件后缀名获得对应的MIME类型。
	 * @param file
	 */
	public static String getMIMEType(File file)
	{
	    String type="*/*";
	    String fName=file.getName();
	    //获取后缀名前的分隔符"."在fName中的位置。
	    int dotIndex = fName.lastIndexOf(".");
	    if(dotIndex < 0){
	        return type;
	    }
	    /* 获取文件的后缀名 */
	    String end=fName.substring(dotIndex,fName.length()).toLowerCase(Locale.US);
	    if(end=="")return type;
	    //在MIME和文件类型的匹配表中找到对应的MIME类型。
	    for(int i=0;i<MIME_MapTable.length;i++){
	        if(end.equals(MIME_MapTable[i][0]))
	            type = MIME_MapTable[i][1];
	    }
	    return type;
	}
	/**
	 * 打开文件
	 * @param file
	 */
	public static boolean openFile(File file, Context mContext){
	    Uri uri = Uri.parse("file://" + file.getAbsolutePath());
	    Intent intent = new Intent();
	    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    //设置intent的Action属性
	    intent.setAction(Intent.ACTION_VIEW);
	    //获取文件file的MIME类型
	    String type = getMIMEType(file);
	    if(type.equals("application/zip") || type.equals("application/x-rar-compressed")){
	    	return false;
	    }
	    //设置intent的data和Type属性。
	    intent.setDataAndType(/*uri*/Uri.fromFile(file), type);
	    //跳转
	    mContext.startActivity(intent);
	    return true;
	}
	
	/**
	 * MIME类型与文件后缀名的匹配表
	 */
	public static final String[][] MIME_MapTable={
	    //{后缀名，    MIME类型}
	    {".3gp",    "video/3gpp"},
	    {".apk",    "application/vnd.android.package-archive"},
	    {".asf",    "video/x-ms-asf"},
	    {".avi",    "video/x-msvideo"},
	    {".bin",    "application/octet-stream"},
	    {".bmp",      "image/bmp"},
	    {".c",        "text/plain"},
	    {".class",    "application/octet-stream"},
	    {".conf",    "text/plain"},
	    {".cpp",    "text/plain"},
	    {".doc",    "application/msword"},
	    {".exe",    "application/octet-stream"},
	    {".gif",    "image/gif"},
	    {".gtar",    "application/x-gtar"},
	    {".gz",        "application/x-gzip"},
	    {".h",        "text/plain"},
	    {".htm",    "text/html"},
	    {".html",    "text/html"},
	    {".jar",    "application/java-archive"},
	    {".java",    "text/plain"},
	    {".jpeg",    "image/jpeg"},
	    {".jpg",    "image/jpeg"},
	    {".js",        "application/x-javascript"},
	    {".log",    "text/plain"},
	    {".m3u",    "audio/x-mpegurl"},
	    {".m4a",    "audio/mp4a-latm"},
	    {".m4b",    "audio/mp4a-latm"},
	    {".m4p",    "audio/mp4a-latm"},
	    {".m4u",    "video/vnd.mpegurl"},
	    {".m4v",    "video/x-m4v"},    
	    {".mov",    "video/quicktime"},
	    {".mp2",    "audio/x-mpeg"},
	    {".mp3",    "audio/x-mpeg"},
	    {".mp4",    "video/mp4"},
	    {".mpc",    "application/vnd.mpohun.certificate"},        
	    {".mpe",    "video/mpeg"},    
	    {".mpeg",    "video/mpeg"},    
	    {".mpg",    "video/mpeg"},    
	    {".mpg4",    "video/mp4"},    
	    {".mpga",    "audio/mpeg"},
	    {".msg",    "application/vnd.ms-outlook"},
	    {".ogg",    "audio/ogg"},
	    {".pdf",    "application/pdf"},
	    {".png",    "image/png"},
	    {".pps",    "application/vnd.ms-powerpoint"},
	    {".ppt",    "application/vnd.ms-powerpoint"},
	    {".prop",    "text/plain"},
	    {".rar",    "application/x-rar-compressed"},
	    {".rc",        "text/plain"},
	    {".rmvb",    "audio/x-pn-realaudio"},
	    {".rtf",    "application/rtf"},
	    {".sh",        "text/plain"},
	    {".tar",    "application/x-tar"},    
	    {".tgz",    "application/x-compressed"}, 
	    {".txt",    "text/plain"},
	    {".wav",    "audio/x-wav"},
	    {".wma",    "audio/x-ms-wma"},
	    {".wmv",    "audio/x-ms-wmv"},
	    {".wps",    "application/vnd.ms-works"},
	    //{".xml",    "text/xml"},
	    {".xml",    "text/plain"},
	    {".z",        "application/x-compress"},
	    {".zip",    "application/zip"},
	    {"",        "*/*"}    
	};
	
	/**
	 * 检测SD卡是否存在 
	 * @return T / F
	 */
	public static boolean isSDCardExists(){
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取SD卡目录
	 * @return
	 */
	public static String getSDCardDir(){
		return Environment.getExternalStorageDirectory().toString();
	}
	
	/**
	 * 检测文件是否存在
	 * @param path
	 * @param fileName
	 * @return
	 */
	public static boolean verify(String path, String fileName){
		File file = new File(path + File.separator + fileName);
		if(file.exists()){
			return true;
		}
		return false;
	}
	
	/**
	 * 删除文件
	 * @param path
	 * @param fileName
	 * @return
	 */
	public static boolean delete(String path, String fileName){
		File delPath = new File(path);
		if(!delPath.exists()){
			return false;
		}
		File delFile = new File(path + File.separator + fileName);
		delFile.deleteOnExit();
		if(!delFile.exists()){
			return false;
		}
		return delFile.delete();
	}
	
	/**
	 * 检测附件文件是否存在
	 * @param filename
	 * @return
	 */
	public static  boolean verify(String filename){
		if(!isSDCardExists()){
			return false;
		}		
		String file_path = getSDCardDir() + File.separator + "nari" + File.separator 
				+ "attachements";
		File file = new File(file_path + File.separator + filename);
		if(file.exists()){
			return true;
		}
		return false;
	}
	
	/**
	 * 删除附件文件
	 * @param filename
	 * @return
	 */
	public static boolean delete(String filename){
		String file_path = getSDCardDir() + File.separator + "nari" + File.separator 
				+ "attachements";
		File file = new File(file_path + File.separator + filename);
		if(file.exists()){
			file.delete();
		}
		return true;
	}
	
	/**
	 * 返回指定的时间格式字符串
	 * @param format 如：SimpleDateFormat.FULL
	 * @return 格式化的时间字符串
	 */
	public static String getFormatDate(int format){
		return SimpleDateFormat.getDateInstance(format).format(new Date());
	}
	
	/**
	 * 显示Toast提示
	 * @param mContext 上下文环境
	 * @param msg  显示的消息
	 * @param type 0： Toast.LENGTH_SHORT; type 1:Toast.LENGTH_LONG
	 */
	public static void showTip(Context mContext, String msg, int type){
		if(type == 0){
			Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
		}
		
	}
	
	/**
	 * 短时间显示Toast提示
	 * @param mContext  上下文环境
	 * @param msg  消息
	 */
	public static void showTip(Context mContext, String msg){
		Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * 获取文件大小
	 * @param size
	 * @return
	 */
	public static String getFileSize(long size){
		if(size < 1024){
			return getSize((float)size) + "B";
		}else
		if(size < (1024 * 1024)){
			return getSize((float)size/1024) + "KB";
		}else{
			return getSize((float)size / (1024 * 1024)) + "MB";
		}
	}
	
	private static String getSize(float d){
		DecimalFormat df = new DecimalFormat("0.00");
		String db = df.format(d);
		return db;
	}
	
}
