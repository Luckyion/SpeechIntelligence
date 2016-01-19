package me.videa.functions.local;

import java.io.Serializable;

import android.graphics.Bitmap;

public class FileLoaderBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3317610507464123865L;
	private String fileName;
	private String fileSize;
	private String filePath;
	
	/**
	 * type: 
	 * <p> 0： 文件</p>
	 * <p> 1: 文件夹</p>
	 * <p> 2: 图片 <p>
	 */
	private String type;
	private Bitmap bitmap;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}	

}
