package me.videa.proxy;

public class ProxyUUD {
	
	/**
	 * 生成唯一的UUD
	 * @return
	 */
	public static String getProxyUUD(){
		return String.valueOf(System.currentTimeMillis());
	}
	
	/**
	 * 校验uud是否合法
	 * @param uud
	 * @return
	 */
	public static String checkUUD(String uud){
		if(uud == null || uud.length() != getProxyUUD().length()){
			return getProxyUUD();
		}
		return uud;
	}

}
