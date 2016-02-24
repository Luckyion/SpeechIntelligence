package me.videa.functions.web;

import java.io.Serializable;

public class RequestOptions implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -343266431720880029L;
	
	private String mUrl;

	public String getmUrl() {
		return mUrl;
	}

	public void setmUrl(String mUrl) {
		this.mUrl = mUrl;
	}

}
