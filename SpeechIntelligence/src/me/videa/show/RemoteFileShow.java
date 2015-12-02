package me.videa.show;

import java.util.List;
import java.util.Map;

import me.videa.voice.R;
import me.videa.voice.async.remote.RemoteFileLoaderCallback;
import me.videa.voice.async.remote.RemoteFileLoaderTask;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 展示远程文件目录
 * @author pactera
 *
 */
public class RemoteFileShow extends Activity{
	
	private final static String TAG = "RemoteFileShow";
	
	TextView mCurrent_path;
	ListView mListView;
	List<Map<String, String>> mListData;
	RemoteFileShowAdapter adapter;
	Context mContext;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remote_file_show);
		mContext = this;
		initViews();
		loadData();
	}
	
	private void loadData(){
		new RemoteFileLoaderTask(RemoteFileShow.this, mCurrent_path.getText().toString(), new RemoteFileLoaderCallback() {
			
			@Override
			public void setResult(List<Map<String, String>> result, String currentPath) {
				// TODO Auto-generated method stub
				Log.d(TAG, currentPath);
				adapterData(result, currentPath);
			}
		}).execute("");
	}
	
	
	private void adapterData(List<Map<String, String>> result, String currentPath){
		if(result == null){
			Toast.makeText(RemoteFileShow.this, R.string.load_err, Toast.LENGTH_LONG).show();
			return;
		}
		//如果当前路径为null，则退出activity
		if(currentPath == null){
			finish();
		}
		this.mListData = result;
		this.mCurrent_path.setText(currentPath);
		adapter = new RemoteFileShowAdapter(mContext, mListData);
		mListView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		
	}
	
	private void initViews(){
		mCurrent_path = (TextView) findViewById(R.id.current_path_name);
		mListView = (ListView) findViewById(R.id.current_content);
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		Log.d(TAG, "退出目录 ");
		super.finish();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		String mPath = mCurrent_path.getText().toString();
		if(mPath == "/"){
			mPath = null;
		}
		if(mPath != null){
			//若当前目录不是根目录，则退回上级目录
			//若为根目录，则退出Activity
			if(mPath.indexOf("/") > 0){
				mPath = mPath.substring(0, mPath.lastIndexOf("/"));
				adapterData(HistoryDataCache.initCache().getCache(mPath), mPath);
				return;
			}			
		}
		super.onBackPressed();
	}	

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	

}
