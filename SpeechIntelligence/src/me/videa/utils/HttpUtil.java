package me.videa.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;

public class HttpUtil {
	// 连接超时
	public static final int CONNECTION_TIMEOUT = 5000;
	// 等待数据的最大时
	public static final int SO_TIMEOUT = 50000;

	/**
	 * post方式请求数据
	 * 
	 * @param params
	 *            参数
	 * @param url
	 *            链接
	 * @return byte数据
	 */
	public static byte[] doPost(ArrayList<NameValuePair> params, String url) {
		int statusCode = 0;
		HttpPost hp = new HttpPost(url);
		try {

			hp.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

			HttpClient client = new DefaultHttpClient(new BasicHttpParams());

			HttpConnectionParams.setConnectionTimeout(client.getParams(),
					CONNECTION_TIMEOUT);
			HttpConnectionParams.setSoTimeout(client.getParams(), SO_TIMEOUT);

			HttpResponse httpResponse = client.execute(hp);
			// statusCode == 200 正常
			statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				return null;
			}
			System.out.println("statusCode=" + statusCode);
			String str = "";
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
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
			// str = new String(data, "utf-8");
			return data;
		} catch (Exception e) {
			hp.abort();
			e.printStackTrace();
		}
		return null;
	}
}
