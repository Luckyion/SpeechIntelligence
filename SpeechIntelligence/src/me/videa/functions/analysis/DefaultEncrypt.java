package me.videa.functions.analysis;


public abstract class DefaultEncrypt implements Encrypt{
	
	public DefaultEncrypt() {
		// TODO Autogenerated constructor stub
		super();
	}

	@Override
	public	abstract String getMd5Key();

	@Override
	public
	String getDefaultKey() {
		// TODO Autogenerated method stub
		return null;
	}

}

