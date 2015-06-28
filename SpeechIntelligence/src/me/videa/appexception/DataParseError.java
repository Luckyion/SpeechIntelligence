package me.videa.appexception;


/**
 * {@code DataParseError} ��Ϊ���ݽ����쳣�ࡣ
 * �����������ݴ����ļ����ݽ�����XML���ݽ����ȡ�
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
