package me.videa.functions.analysis;


public abstract class DefaultEncrypt implements Encrypt{
	
	public DefaultEncrypt() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	public	abstract String getMd5Key();

	@Override
	public
	String getDefaultKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
