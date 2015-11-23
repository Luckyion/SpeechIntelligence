package me.videa.appexception;


/**
 * @author pactera
 *
 */
public class AppError extends Exception{
	
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
