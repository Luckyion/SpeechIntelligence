package me.videa.proxy;

/**
 * 当缓存任务队列中没有当前添加的数据提交任务时，由任务队列</br>
 * 唤醒数据加载线程，将缓存在数据库中的未提交成功的数据加载至任务队列中。
 * @author Vickie Tang
 *
 */
public interface CacheNotify {
	
	public void sendNotification();

}
