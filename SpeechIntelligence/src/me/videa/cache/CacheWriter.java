package me.videa.cache;


public class CacheWriter extends AbstractCache {
	
	public CacheWriter() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	public synchronized void writeCache(CacheBean cacheBean){
		// TODO Auto-generated method stub
		cacheBean.setFileName(getCachePath() + getEncryptName(cacheBean.getFileName()) + SUFFIX);
		new Thread(new CacheWriteThread(cacheBean)).start();		
	}

}
