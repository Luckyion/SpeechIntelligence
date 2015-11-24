package me.videa.application;


/**
 * Session管理类
 * @author Vickie Tang
 * @version 1.0
 *
 */
public class SessionManager {
	
	private static Session session;
	private static SessionManager sessionManager;

	private SessionManager() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 获得SessionManager对象
	 * @return SessionManager
	 */
	public static SessionManager getSessionManager(){
		if(sessionManager == null){
			sessionManager = new SessionManager();
			session = new Session();
		}
		return sessionManager;
	}
	
	/**
	 * 获得Session对象
	 * @return session
	 */
	public Session getSession(){
		return session;
	}
	
	/**
	 * 修改或设置Session中的值
	 * @param key 键
	 * @param value  值
	 */
	public void setSession(String key, String value){
		session.put(key, value);
	}

}
