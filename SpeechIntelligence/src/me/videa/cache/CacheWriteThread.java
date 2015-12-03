package me.videa.cache;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.util.Base64;

public class CacheWriteThread implements Runnable{
	
	CacheBean mBean;
	
	public CacheWriteThread(CacheBean cacheBean) {
		// TODO Auto-generated constructor stub
		this.mBean = cacheBean;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		FileOutputStream mFileOutputStream;
		try {
			System.out.println(mBean.getFileName());
			mFileOutputStream = new FileOutputStream(mBean.getFileName());
			mFileOutputStream.write(Base64.encode(mBean.getFileContent().getBytes(), Base64.CRLF));
			mFileOutputStream.flush();
			mFileOutputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
