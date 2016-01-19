package me.videa.functions.novelloader;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import me.videa.functions.local.FileAdapter;
import me.videa.functions.local.FileLoaderBean;
import me.videa.functions.local.SelectorResult;
import me.videa.utils.DebugUtil;
import me.videa.utils.LocalFileUtil;
import me.videa.voice.R;
import me.videa.voice.async.AsyncCallback;
import me.videa.voice.async.Result;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;

public class NovelShow extends LinearLayout implements OnItemClickListener, AsyncCallback{
	
	private static final String TAG = "NovelShow";
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
	SelectorResult mResult;

	public NovelShow(Context context, SelectorResult result) {
		super(context);
		mContext = context;
		this.mResult = result;
		mRoot = LocalFileUtil.getSDCardPath();
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
		new NovelSearcher(this).execute();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		FileLoaderBean mBean = mFiles.get(position);
		DebugUtil.d(TAG, mBean.getFileName());
		Bundle mBundle = new Bundle();
		mBundle.putString("data", mBean.getFilePath());
		mResult.setResult(mBundle);
	}

	@Override
	public void callback(Result result) {
		// TODO Auto-generated method stub
		List<FileLoaderBean> mFileLoaderBeans = (List<FileLoaderBean>) result.getResult();
		mFiles.addAll(mFileLoaderBeans);
		mAdapter.notifyDataSetChanged();
	}

}
