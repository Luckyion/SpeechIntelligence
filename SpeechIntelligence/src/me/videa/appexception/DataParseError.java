package me.videa.appexception;


/**
 * {@code DataParseError} 
 * @author Vickie Tang
 *
 */
@SuppressWarnings("serial")
public class DataParseError extends AppError{
	
	public DataParseError(){
		super();
	}
	
	public DataParseError(String exceptionMessage){
		super(exceptionMessage);
	}
	
	public DataParseError(Throwable cause){
		super(cause);
	}
	
	public DataParseError(String exceptionMessage, Throwable cause){
		super(exceptionMessage, cause);
	}

}
