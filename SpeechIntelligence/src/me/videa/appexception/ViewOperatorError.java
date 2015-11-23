package me.videa.appexception;


/**
 * {@code ViewOperatorError} 
 * @author Vickie Tang
 *
 */
@SuppressWarnings("serial")
public class ViewOperatorError extends AppError{
	
	public ViewOperatorError(){
		super();
	}
	
	public ViewOperatorError(String exceptionMessage){
		super(exceptionMessage);
	}
	
	public ViewOperatorError(Throwable cause){
		super(cause);
	}
	
	public ViewOperatorError(String exceptionMessage, Throwable cause){
		super(exceptionMessage, cause);
	}

}
