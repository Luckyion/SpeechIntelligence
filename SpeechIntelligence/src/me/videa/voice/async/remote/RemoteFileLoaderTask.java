package me.videa.voice.async.remote;

import java.util.List;
import java.util.Map;

import me.videa.utils.BasicConfig;
import android.content.Context;
import android.os.AsyncTask;

/**
 * 加载远程文件异步线程</br>
 * @author pactera
 *
 */
public class RemoteFileLoaderTask extends AsyncTask<String, String, List<Map<String, String>>>{
	
	private Context mContext;
	private RemoteFileLoaderCallback mCallback;
	private String mCurrentPath;
	
	public RemoteFileLoaderTask	(){
		
	}
	
	public RemoteFileLoaderTask(Context context, String currentPath, RemoteFileLoaderCallback callback){
		this.mCallback = callback;
		this.mContext = context;
		this.mCurrentPath = currentPath;
	}

	@Override
	protected List<Map<String, String>> doInBackground(String... params) {
		// TODO Auto-generated method stub	
		String url = BasicConfig.FILE_URL;
		List<Map<String, String>> result = new FileLoader(url, mCurrentPath).queryRemoteFileMessage();
		return result;
	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(List<Map<String, String>> result) {
		// TODO Auto-generated method stub
		mCallback.setResult(result, mCurrentPath);
		super.onPostExecute(result);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(String... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}
	
	

}
