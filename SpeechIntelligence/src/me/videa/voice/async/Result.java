package me.videa.voice.async;

import java.util.HashMap;

public class Result extends HashMap<String, Object>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6282374412006829482L;
	
	private Object result;
	private boolean isSuc;
	private String errorMsg;
	
	

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public boolean isSuc() {
		return isSuc;
	}

	public void setSuc(boolean isSuc) {
		this.isSuc = isSuc;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
