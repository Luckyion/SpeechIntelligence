package me.videa.proxy;

/**
 * 提供的一种参数绑定接口</br>
 * 实现该接口中的{@link #proxy()}方法，</br>
 * 进行参数的封装（功能与{@link ProxyThread#setmParam(ProxyParam)相同} ）
 * 在应用时，二选一即可。
 * @author Vickie Tang
 *
 */
public interface ProxyRequest {
	

	@SuppressWarnings("rawtypes")
	/**
	 * 封装请求参数
	 * @return ProxyParam
	 */
	public ProxyParam proxy();

}
