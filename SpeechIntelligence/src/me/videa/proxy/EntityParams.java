package me.videa.proxy;

import android.content.Context;

/**
 * 用于文件上传时的参数封装
 * @author Vickie Tang
 *
 */
public class EntityParams extends ProxyParam<ProxyEntity>{
	
	public EntityParams(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
	}

	@Override
	public void addParams(ProxyEntity entity) {
		// TODO Auto-generated method stub
		get().setmParams(entity);
	}

	@Override
	protected String paramToString() {
		// TODO Auto-generated method stub
		return null;
	}

}
