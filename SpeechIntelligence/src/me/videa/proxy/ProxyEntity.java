package me.videa.proxy;

import java.io.Serializable;

public class ProxyEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6921405724338516468L;
	
	private String uploadUrl;
	
	private String bindId;
	
	private String bindUserId;
	
	private String fileName;
	
	private String localFilePath;

	public String getUploadUrl() {
		return uploadUrl;
	}

	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	public String getBindId() {
		return bindId;
	}

	public void setBindId(String bindId) {
		this.bindId = bindId;
	}

	public String getBindUser() {
		return bindUserId;
	}

	public void setBindUser(String bindUserId) {
		this.bindUserId = bindUserId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getLocalFilePath() {
		return localFilePath;
	}

	public void setLocalFilePath(String localFilePath) {
		this.localFilePath = localFilePath;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
