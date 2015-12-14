package me.videa.appexception;


/**
 * {@code NetworkError} 为网络访问异常类。
 * 包括访问超时、服务器错误等
 * @author pactera
 *
 */
@SuppressWarnings("serial")
public class NetworkError extends AppError{
	
	public NetworkError(){
		super();
	}
	
	public NetworkError(Throwable cause){
		super(cause);
	}
	
	public NetworkError(String exceptionMessage, Throwable cause){
		super(exceptionMessage, cause);
	}

}
