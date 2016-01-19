package me.videa.functions.local;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import me.videa.utils.LocalFileUtil;
import me.videa.voice.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class FileExplore extends LinearLayout implements OnItemClickListener{
	
	private final String TAG = "FileExplore";
	
	
	private Context mContext;
	LayoutInflater mInflater;
	RelativeLayout mLayout;
	ListView mListView;
	FileAdapter mAdapter;
	LinkedList<FileLoaderBean> mFiles;
	private boolean isOpen = false;
	SelectorResult mSelectorResult;
	String mRoot;
	boolean isRoot = true;
	boolean isPause = false;
	boolean isLoading = false;
	String mPath;
	Map<String, LinkedList<FileLoaderBean>> mCache;
	LinkedList<FileLoaderBean> mCacheBeans;
	List<FileLoaderBean> mFileLoaderBeans;

	public FileExplore(Context context, SelectorResult result, String root) {
		super(context);		
		mContext = context;
		this.mSelectorResult = result;
		mRoot = root == null ? LocalFileUtil.getSDCardPath() : root;
		mInflater = LayoutInflater.from(context);
		mLayout = (RelativeLayout) mInflater.inflate(R.layout.activity_file_list, null);
		mListView = (ListView) mLayout.findViewById(R.id.file_list);
		mFiles = new LinkedList<FileLoaderBean>();
		mAdapter = new FileAdapter(context, mFiles);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
		this.addView(mLayout);
		mPath = mRoot;
		mCache = new HashMap<String, LinkedList<FileLoaderBean>>();
		load();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {		
		mCacheBeans = new LinkedList<FileLoaderBean>();
		mCacheBeans.addAll(mFiles);
		mCache.put(mPath, mCacheBeans);
		mPath = mFiles.get(position).getFilePath();
		Log.d(TAG, mPath);
		isRoot = false;
		File file = new File(mPath);
		if(file.isFile()){
			if(isOpen){
				LocalFileUtil.openFile(file, mContext);
				}else{
					setResult(mPath);
				}
			return;
		}
		load();		
	}
	
	
	private void load(){	
		mFiles.clear();
		isLoading = true;
		new FileLoader().execute();
	}
	
	
	private String getFileSize(long size){
		if(size < 1024){
			return getSize((float)size) + "B";
		}else
		if(size < (1024 * 1024)){
			return getSize((float)size/1024) + "KB";
		}else{
			return getSize((float)size / (1024 * 1024)) + "MB";
		}
	}
	
	private String getSize(float d){
		DecimalFormat df = new DecimalFormat("0.00");
		String db = df.format(d);
		return db;
	}
	
	private String getType(File file){
		if(file.isFile() && LocalFileUtil.isImage(file)){
			return "2";
		}else if(file.isFile()){
			return "0";
		}
		if(file.isDirectory()){
			return "1";
		}
		return "0";
	}
	
	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	class FileLoader extends AsyncTask<Void, Integer, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			FileLoaderBean mBean;
			File file = new File(mPath);
			if(!file.exists()){
				return null;
			}		
			mFileLoaderBeans = new LinkedList<FileLoaderBean>();
			File[] files = file.listFiles();
			for(File f : files){
				if(isPause){
					break;
				}
				mBean = new FileLoaderBean();
				mBean.setFileName(f.getName());
				mBean.setFilePath(f.getPath());
				mBean.setFileSize(getFileSize(f.length()));
				String type = getType(f);
				if(type.equals("2")){
					Bitmap mBitmap = LocalFileUtil.getBitmap(f.getPath());
					mBean.setBitmap(mBitmap);
				}
				mBean.setType(type);
				mFileLoaderBeans.add(mBean);
				if(mFileLoaderBeans.size() % 5 == 0){
					publishProgress(0);
				}
			}
			return null;
		}	
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			if(!isPause){
				mFiles.clear();
				mFiles.addAll(mFileLoaderBeans);
				mAdapter.notifyDataSetChanged();
			}			
			super.onProgressUpdate(values);
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			isLoading = false;
			if(!isPause){
				mFiles.clear();
				mFiles.addAll(mFileLoaderBeans);				
			}
			isPause = false;
			mAdapter.notifyDataSetChanged();
			super.onPostExecute(result);
		}
		
	}
	
	void setResult(String path){
		Bundle mBundle = new Bundle();
		mBundle.putString("data", path);
		mSelectorResult.setResult(mBundle);
	}

	/**
	 * 按下Back键的动作
	 */
	public void onBackPressed() {
		if(isLoading)
			isPause = true;
		if(mPath.equals(mRoot)){
			setResult(mPath);
			return;
		}
		mPath = mPath.substring(0, mPath.lastIndexOf("/"));
//		mFiles.clear();
		LinkedList<FileLoaderBean> mBeans = mCache.get(mPath);
		mFiles.clear();
		mFiles.addAll(mBeans);
		mAdapter.notifyDataSetChanged();
	}
	

}
