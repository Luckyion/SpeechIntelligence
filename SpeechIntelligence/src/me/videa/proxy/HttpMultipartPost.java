package me.videa.proxy;

import java.io.File;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.os.AsyncTask;

public class HttpMultipartPost extends AsyncTask<HttpResponse, Integer, ProxyResult> {
	long totalSize;;
	private ProxyEntity mEntity;
	private ProxyTransferCallback mCallback;
	ProxyResult proxyResult;
	private Context mContext;
	public HttpMultipartPost(Context context, ProxyTransferCallback callback, ProxyEntity entity) {
		this.mContext = context;
		this.mCallback = callback;
		this.mEntity = entity;
		proxyResult = new ProxyResult();
	}

	@Override
	protected void onPreExecute() {
		
	}

	@Override
	protected ProxyResult doInBackground(HttpResponse... arg0) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext httpContext = new BasicHttpContext();
		HttpPost httpPost = new HttpPost(mEntity.getUploadUrl());

		try {
			CustomMultipartEntity multipartContent = new CustomMultipartEntity(
					new CustomMultipartEntity.ProgressListener() {
						@Override
						public void transferred(long num) {
							publishProgress((int) ((num / (float) totalSize) * 100));
						}
					});
			multipartContent.addPart("file_name", new StringBody(new File(mEntity.getLocalFilePath()).getName()));

			// We use FileBody to transfer an image
			multipartContent.addPart("uploaded_file", new FileBody(new File(
					mEntity.getLocalFilePath())));

			totalSize = multipartContent.getContentLength();
			// Send it
			httpPost.setEntity(multipartContent);
			HttpResponse response = httpClient.execute(httpPost, httpContext);
			String serverResponse = EntityUtils.toString(response.getEntity());
			if(serverResponse == null){
				return updateDb("0");
			}
			return updateDb("-1");
		}

		catch (Exception e) {
			mCallback.onTransferFailed(null);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onProgressUpdate(Integer... progress) {
		// pd.setProgress((int) (progress[0]));
		if(mCallback != null){
			mCallback.onTransferProgressChanged(progress);
		}
	}

	@Override
	protected void onPostExecute(ProxyResult result) {		
		mCallback.onTransferCompleted(proxyResult);
	}
	
	private ProxyResult updateDb(String status){
		proxyResult.setStatus(status);
		proxyResult.setProxyUrl(mEntity.getUploadUrl());
		proxyResult.setProxyId(ProxyUUD.checkUUD(mEntity.getBindId()));
		proxyResult.setContent(mEntity.getLocalFilePath());
		proxyResult.setIsHandled("-1");
		proxyResult.setIsNotify("-1");
		proxyResult.setMark(mContext.getClass().getName());
		proxyResult.setUserID(mEntity.getBindUser());
		proxyResult = ProxyResultDbHelper.updateAndReturnProxyResult(mContext, proxyResult);
		return proxyResult;
	}
}