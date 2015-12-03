package me.videa.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.AsyncTask;
import android.util.Base64;

public class CacheReadThread extends AsyncTask<Void, Void, String>{
	
	Cache mCallback;
	File mFile;
	
	public CacheReadThread(Cache callback, File file) {
		// TODO Auto-generated constructor stub
		this.mCallback = callback;
		this.mFile = file;
	}

	@Override
	protected String doInBackground(Void... params) {
		// TODO Auto-generated method stub
		FileInputStream mFileInputStream;
		try {
			mFileInputStream = new FileInputStream(mFile);
			BufferedReader mBufferedReader = new BufferedReader(new InputStreamReader(mFileInputStream));
			String str;
			String res = "";
			while((str = mBufferedReader.readLine()) != null){
				res += str;
			}
			mBufferedReader.close();
			mFileInputStream.close();
			return new String(Base64.decode(res, Base64.CRLF), "UTF-8");		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		mCallback.setCache(result);
		super.onPostExecute(result);
	}
	
	

}
