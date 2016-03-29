package me.videa.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.hp.hpl.sparta.Node;

import android.content.Context;

public class AssetsResourceLoader {
	/**
	 * 加载文本内容
	 * @param mContext
	 * @return
	 */
	public static InputStream loadFile(Context context, String fileName) throws IOException{
		return context.getAssets().open(fileName);
	}
	/**
	 * 以字符串形式获取Assets中的文件内容
	 * @param context
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String getXmlWithString(Context context, String fileName) throws IOException{
		String mRes = "";
		String tempStr;
		InputStream mInputStream = loadFile(context, fileName);
		BufferedReader mInputStreamReader = new BufferedReader(new InputStreamReader(mInputStream));
		while ((tempStr = mInputStreamReader.readLine()) != null) {
			mRes += tempStr;
		}
		return mRes;
	}
	
	public static String getXmlData(String xml, String action){		
		StringBuffer buffer = new StringBuffer();
		try
		{
			// DOM builder
			DocumentBuilder domBuilder = null;
			// DOM doc
			Document domDoc = null;	

			// init DOM
			DocumentBuilderFactory domFact = DocumentBuilderFactory.newInstance();
			domBuilder = domFact.newDocumentBuilder();
			InputStream is = new ByteArrayInputStream(xml.getBytes());
			domDoc = domBuilder.parse(is);

			// 获取根节点
			Element root = (Element) domDoc.getDocumentElement();
			
			NodeList functions = root.getElementsByTagName("function").item(0).getChildNodes();
			
			for(int i = 0; i < functions.getLength(); i++){
				NodeList properties = functions.item(i).getChildNodes();
				String nodeName = properties.item(0).getNodeName();
				if(action.trim().equals(nodeName)){
					
				}				
			}
			

		}catch(Exception e){
			e.printStackTrace();
		};
		buffer.append("【ALL】" + xml);
		return buffer.toString();
	}

}
