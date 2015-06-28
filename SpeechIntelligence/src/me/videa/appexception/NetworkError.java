package me.videa.appexception;


/**
 * {@code NetworkError} Ϊ��������쳣�ࡣ
 * �������ʳ�ʱ�������������
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
