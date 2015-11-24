package me.videa.proxy;

/**
 * 缓存代理机制网络数据请求时，当调用者需要跟踪提交状态时，通过该接口反馈请求状态</br>
 * 接口的回调方法回调后，通过一定的封装处理并通过接口{@link ProxyCallback}中的</br>
 * 回调方法回调给调用者。
 * @author pactera
 *
 */
public interface ProxyTransferCallback {
	
	/**
	 * 当上传进度发生变化时回调。
	 * @param progress
	 */
	public void onTransferProgressChanged(Integer... progress);
	/**
	 * 当上传完成时回调。
	 * @param result
	 */
	public void onTransferCompleted(ProxyResult result);
	/**
	 * 当上传失败时回调。
	 * @param errorCode
	 */
	public void onTransferFailed(ProxyResult errorCode);

}
