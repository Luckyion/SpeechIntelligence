package me.videa.functions.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

public class InfoTransmit {
		
	public static boolean isPost = false;
	/**
	 * deal with the result of server return.
	 * @param entity
	 * @return
	 */
	public static String getResultFromHttp(HttpEntity entity)
	{
		//�Է��ص����������д���
		StringBuffer buffer = new StringBuffer();
		try{
			BufferedReader  reader = new BufferedReader(new InputStreamReader(entity.getContent()));
			String temp =null;
			while((temp = reader.readLine())!=null)
			{
				buffer.append(temp);
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		return buffer.toString();
	}	
	
	
	/**
	 * Get the server status and result.
	 * @param siteUrl
	 * @param jsonString
	 * @return result when the server return status code equals with 200.
	 */
	public static String getResult(String siteUrl){
		String result = "";
		try {
			//����Get��������ύ���� request
			DefaultHttpClient client = new DefaultHttpClient();
			HttpConnectionParams.setConnectionTimeout(client.getParams(), 30 * 1000);
			HttpConnectionParams.setSoTimeout(client.getParams(), 30 * 1000);
			HttpProtocolParams.setUserAgent(client.getParams(), "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.9) Gecko/20100315 Firefox/3.5.9");
			HttpGet httpget = new HttpGet();
			httpget.setURI(new URI(siteUrl));
			HttpResponse response = client.execute(httpget);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				result = EntityUtils.toString(entity);
				httpget.abort();
				client.getConnectionManager().shutdown();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
		
	}
	
}
