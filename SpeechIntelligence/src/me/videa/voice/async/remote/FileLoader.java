package me.videa.voice.async.remote;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import me.videa.show.HistoryDataCache;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FileLoader {
	
	private String mUrl;
	private String mLocalpath;
	private String mCurrentPath;
	private ProgressCallback mCallback;
	private int total_size;
	private int current_size;
	private String filename;
	
	public FileLoader() {
		// TODO Auto-generated constructor stub
	}
	
	public FileLoader(String url, String currentpath){
		this.mUrl = url;
		this.mCurrentPath = currentpath;
	}
	
	public void setProgressCallback(ProgressCallback callback){
		this.mCallback = callback;
	}
	
	/**
	 * 获取远程文件的目录及文件信息
	 * @return
	 */
	public List<Map<String, String>> queryRemoteFileMessage(){
		String result = "";
		int statusCode = 0;
		HttpPost hp = new HttpPost(mUrl);
		try {
			HttpResponse httpResponse = null;
			if(mCurrentPath != null && !mCurrentPath.equals("")){
				ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
				NameValuePair nameValuePair = new BasicNameValuePair("path", mCurrentPath);
				params.add(nameValuePair);
				hp.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));			
			}
			HttpClient client = new DefaultHttpClient(new BasicHttpParams());
			httpResponse = client.execute(hp);
			// statusCode == 200 正常
			statusCode = httpResponse.getStatusLine().getStatusCode();
			System.out.println("statusCode=" + statusCode);
			InputStream inputStream = null;
			if(statusCode == 200){
				HttpEntity entity = httpResponse.getEntity();
				if(entity !=  null){
					entity = new BufferedHttpEntity(entity);
					inputStream = entity.getContent();
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
					String temp = null;
					while((temp = bufferedReader.readLine()) != null){
						result += temp;
						System.out.println(temp);
					}
					System.out.println(result);
				}
			}			
			if(inputStream != null){
				inputStream.close();
			}			
			return resultToMaps(result);
		} catch (Exception e) {
			hp.abort();
			e.printStackTrace();
		}
		return null;
		
	}
	
	public File download(){
		InputStream inputStream = null;
		FileOutputStream fileOutputStream = null;
		File file = null;
		current_size = 0;
		try {
			URL url = new URL(mUrl);
			URLConnection connection = url.openConnection();
			inputStream = connection.getInputStream();
			total_size = connection.getContentLength();
			if(total_size == 0){
				return null;
			}
			filename = mUrl.substring(mUrl.lastIndexOf("/"));
			fileOutputStream = new 
					FileOutputStream(mLocalpath + File.separator + filename);
			byte[] buffer = new byte[1024];
			int byteread;
			while((byteread = inputStream.read(buffer)) != -1){
				current_size += byteread;
				fileOutputStream.write(buffer, 0, byteread);
				mCallback.setProgress(String.valueOf(current_size/total_size));
			}
			fileOutputStream.write(inputStream.read());
			file = new File(mLocalpath + File.separator + filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(inputStream != null){
				try {
					inputStream.close();
					if(fileOutputStream != null){
						fileOutputStream.close();
					}					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		}
		return file;
	}
	
	/**
	 * 将结果封装为List对象
	 * @param result
	 * @return
	 */
	private List<Map<String, String>> resultToMaps(String result){
		List<Map<String, String>> list = new LinkedList<Map<String,String>>();
		HistoryDataCache historyDataCache = HistoryDataCache.initCache();
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			JSONObject jsonObject = new JSONObject(result);
			JSONArray array = jsonObject.getJSONArray("file_list");
			for (int i = 0; i < array.length(); i++) {
				map.put("filename", ((JSONObject)array.get(i)).getString("filename").toString());
				map.put("filesize", ((JSONObject)array.get(i)).getString("filename").toString());
				list.add(map);
				historyDataCache.addCache(mCurrentPath, list);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return historyDataCache.getCache(mCurrentPath);
	}

}
