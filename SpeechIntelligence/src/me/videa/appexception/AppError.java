package me.videa.appexception;


/**
 * 应用程序异常信息封装类
 * @author pactera
 *
 */
public class AppError extends Exception{
	
	
	/**
	 * 默认ID
	 */
	private static final long serialVersionUID = -7035455902681517269L;
	
	public AppError(){
		super();
	}
	
	public AppError(String exceptionMessage){		
		super(exceptionMessage);
	}
	
	public AppError(String exceptionMessage, Throwable cause){
		super(exceptionMessage, cause);
	}
	
	public AppError(Throwable cause){
		super(cause);
	}
	

}
