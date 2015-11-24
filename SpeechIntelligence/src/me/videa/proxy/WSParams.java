package me.videa.proxy;

import java.util.List;

import android.content.Context;

/**
 * WebService调用的参数实现
 * @author pactera
 *
 */
public class WSParams extends ProxyParam<List<String>>{
	
	private String mUrl;
	
	private String mMethod;
	
	private String mNameSpace;
	
	public WSParams(Context context){
		super(context);
	}
	
	public WSParams(Context context, String mUrl, String mMethod, String mNameSpace){
		this(context);
		this.mMethod = mMethod;
		this.mUrl = mUrl;
		this.mNameSpace = mNameSpace;
	}

	@Override
	void addParams(List<String> params) {
		// TODO Auto-generated method stub
		get().setmParams(params);
	}

	public String getmUrl() {
		return mUrl;
	}

	public void setmUrl(String mUrl) {
		this.mUrl = mUrl;
	}

	public String getmMethod() {
		return mMethod;
	}

	public void setmMethod(String mMethod) {
		this.mMethod = mMethod;
	}

	public String getmNameSpace() {
		return mNameSpace;
	}

	public void setmNameSpace(String mNameSpace) {
		this.mNameSpace = mNameSpace;
	}

	@Override
	protected String paramToString() {
		// TODO Auto-generated method stub
		String result = "";
		for(String param : mParams){
			result += "param=" + param + "\\";
		}
		if(!result.equals("")){
			return result.substring(0, result.lastIndexOf("\\"));		
		}
		return result;
	}	
	
	

}
