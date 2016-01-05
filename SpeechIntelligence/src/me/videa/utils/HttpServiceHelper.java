package me.videa.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.protocol.HTTP;

public class HttpServiceHelper {
	
	public static String queryMsg(String url,ArrayList<NameValuePair> params){
		int statusCode = 0;
		HttpPost hp = new HttpPost(url);
		try {

			HttpResponse httpResponse = null;
			if(params != null && !params.equals("")){
				hp.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));			
			}
			HttpClient client = new DefaultHttpClient(new BasicHttpParams());
			httpResponse = client.execute(hp);
			// statusCode == 200 正常
			statusCode = httpResponse.getStatusLine().getStatusCode();
			System.out.println("statusCode=" + statusCode);
			String str = "";
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];

			// long count = entity.getContentLength();
			// long curCount = 0;
			int len = -1;
			InputStream is = httpResponse.getEntity().getContent();
			while ((len = is.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
				// curCount += len;
				len = -1;
			}
			byte[] data = outStream.toByteArray();
			outStream.close();
			is.close();
			str = new String(data, "utf-8");
			return str;
		} catch (Exception e) {
			hp.abort();
			e.printStackTrace();
		}
		return null;
	}

}
