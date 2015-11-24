package me.videa.functions.analysis;



public abstract class AbstractAnalyse implements Analysis{	
	
	private Encrypt mEncrypt;
	
	public AbstractAnalyse() {
		// TODO Auto-generated constructor stub
	}
	
	public AbstractAnalyse(Encrypt encrypt) {
		// TODO Auto-generated constructor stub
		this.mEncrypt = encrypt;
	}
	
	/**
	 * 获取密钥
	 * @param encryptType加密方式
	 * @return 密钥
	 */
	String getEncryptKey(String encryptType) {
		// TODO Auto-generated method stub
		if(encryptType.equals("md5")){
			return mEncrypt.getMd5Key();
		}
		return mEncrypt.getDefaultKey();
	}	
	
}
