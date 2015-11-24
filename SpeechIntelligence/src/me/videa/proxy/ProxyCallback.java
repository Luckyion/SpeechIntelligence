package me.videa.proxy;

/**
 * 缓存机制回调接口，当界面需要获得返回缓存机制的网络数据请求结果时</br>
 * 可以实现该接口，通过接口的方法获取返回结果。 </br>
 * 如果通过缓存代理请求并需要实时获得返回值时，需要设置参数中的枚举对象</br>
 * {@link ProxyParam.REQTYPE}对象为NOW.
 * @author Vickie Tang
 *
 */
public interface ProxyCallback {
	
	/**
	 * 缓存代理执行完成时回调
	 * @param result
	 */
	public void onProxyCompleted(ProxyResult result);
	/**
	 * 缓存代理执行失败时回调
	 * @param errorCode
	 */
	public void onProxyFailed(ProxyResult errorCode);
	/**
	 * 缓存代理执行状态发生变化时回调
	 * @param state
	 */
	public void onProxyStateChanged(Integer... state);

}
