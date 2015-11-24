package me.videa.proxy;

import java.util.Map;
import java.util.Set;

import android.content.Context;


public class ServParams extends ProxyParam<Map<String, String>> {
	
	private Map<String, String> mMaps;
	
	public ServParams(Context context, String mUrl) {
		// TODO Auto-generated constructor stub
		super(context);
		get().setmUrl(mUrl);
	}

	@Override
	protected String paramToString() {
		// TODO Auto-generated method stub
		Set<String> keys = mParams.keySet();
		String result = "";
		for(String key : keys){
			result += key + "=" + mParams.get(key) + "\\";
		}
		if(!result.equals("")){
			return result.substring(0, result.lastIndexOf("\\"));		
		}
		return result;
	}

	@Override
	void addParams(Map<String, String> params) {
		// TODO Auto-generated method stub
		this.setmMaps(params);
		get().setmParams(params);
	}

	/**
	 * @return the mMaps
	 */
	public Map<String, String> getmMaps() {
		return mMaps;
	}

	/**
	 * @param mMaps the mMaps to set
	 */
	public void setmMaps(Map<String, String> mMaps) {
		this.mMaps = mMaps;
	}

}
