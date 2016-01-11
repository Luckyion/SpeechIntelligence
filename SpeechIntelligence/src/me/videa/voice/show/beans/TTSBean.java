package me.videa.voice.show.beans;

import java.io.Serializable;

public class TTSBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -102183819191597687L;
	
	private int progress;
	
	private int bufferProgress;
	
	private String status;
	
	private String result;

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public int getBufferProgress() {
		return bufferProgress;
	}

	public void setBufferProgress(int bufferProgress) {
		this.bufferProgress = bufferProgress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
	

}
