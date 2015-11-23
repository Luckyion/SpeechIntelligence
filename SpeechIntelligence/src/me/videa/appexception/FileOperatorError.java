package me.videa.appexception;


/**
 * {@code FileOperatorError} 
 * @author Vickie Tang
 *
 */
@SuppressWarnings("serial")
public class FileOperatorError extends AppError{
	
	public FileOperatorError(){
		super();
	}
	
	public FileOperatorError(String exceptionMessage){
		super(exceptionMessage);
	}
	
	public FileOperatorError(Throwable cause){
		super(cause);
	}
	
	public FileOperatorError(String exceptionMessage, Throwable cause){
		super(exceptionMessage, cause);
	}

}
