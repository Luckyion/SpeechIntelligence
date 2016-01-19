package me.videa.functions.novelloader;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import me.videa.functions.local.FileLoaderBean;
import me.videa.utils.LocalFileUtil;
import me.videa.voice.async.AsyncCallback;
import me.videa.voice.async.Result;
import android.os.AsyncTask;

public class NovelSearcher extends AsyncTask<Void, Integer, Void>{
	
	private List<FileLoaderBean> mFileLoaderBeans;
	private String mPath;
	private AsyncCallback mCallback;
	
	public NovelSearcher(AsyncCallback callback) {
		this.mCallback = callback;
		mFileLoaderBeans = new LinkedList<FileLoaderBean>();
		mPath = LocalFileUtil.getSDCardPath();
	}
	
	/**
	 * 搜索
	 */
	public void search(){
		File file = new File(mPath);
		if(!file.exists()){
			return;
		}
		File[] files = file.listFiles();
		for(File f : files){
			if(f.isFile() && isTxt(f)){
				FileLoaderBean mBean;
				mBean = new FileLoaderBean();
				mBean.setFileName(f.getName());
				mBean.setFilePath(f.getPath());
				mBean.setFileSize(LocalFileUtil.getFileSize(f.length()));
				mBean.setType("0");
				mFileLoaderBeans.add(mBean);
				if(mFileLoaderBeans.size() % 5 == 0){
					publishProgress(0);
				}
			}else if(f.isDirectory()){
				mPath = mPath + File.separator + f.getAbsolutePath();
				search();
			}
		}
	}
	
	/**
	 * 判断是否是txt文件
	 * @param file
	 * @return
	 */
	private boolean isTxt(File file){
		String filename = file.getName();
		String extra = null;
		if(filename.contains(".")){
			extra = filename.substring(filename.lastIndexOf("."));
		}
		if(extra != null && extra.equals(".txt")){
			return true;
		}
		return false;
	}

	@Override
	protected Void doInBackground(Void... params) {
		search();
		return null;
	}
	
	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		Result mResult = new Result();
		mResult.setSuc(true);
		mResult.setErrorMsg(null);
		mResult.setResult(mFileLoaderBeans);
		mCallback.callback(mResult);
		super.onProgressUpdate(values);
	}
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		for(FileLoaderBean string : mFileLoaderBeans){
			System.out.println(string);
		}
		Result mResult = new Result();
		mResult.setSuc(true);
		mResult.setErrorMsg(null);
		mResult.setResult(mFileLoaderBeans);
		mCallback.callback(mResult);
		super.onPostExecute(result);
	}


}
