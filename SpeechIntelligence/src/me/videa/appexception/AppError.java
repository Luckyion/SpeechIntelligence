package me.videa.appexception;


/**
 * Ӧ�ó����쳣��Ϣ��װ��
 * @author pactera
 *
 */
public class AppError extends Exception{
	
	
	/**
	 * Ĭ��ID
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
