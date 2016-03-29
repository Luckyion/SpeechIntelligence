package me.videa.functions.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class GetUserIp {
	public static String getWebIp(String urlStr){
		try{
			URL url = new URL(urlStr);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			String webContent = "";
			while((s = br.readLine()) != null){
				sb.append(s + "\r\n");
			}
			br.close();
			webContent = sb.toString();
			System.out.println(webContent);
			int start = webContent.indexOf("[") + 1;
			int end = webContent.indexOf("]") + 1;
			webContent = webContent.substring(start, end);
			return webContent;

		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}	
	}
	
	public static void  main(String[] args) {
		GetUserIp gui = new GetUserIp();
		String s = gui.getWebIp("http://www.baidu.com");
		System.out.println(s);
	}
}
