package me.videa.appexception;


/**
 * {@code DataParseError} 类为数据解析异常类。
 * 包括网络数据处理，文件数据解析如XML数据解析等。
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
