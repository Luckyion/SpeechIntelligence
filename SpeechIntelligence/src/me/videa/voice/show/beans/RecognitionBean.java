package me.videa.voice.show.beans;

import java.io.Serializable;

public class RecognitionBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5858114274510856815L;
	
	private int progress;

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

}
