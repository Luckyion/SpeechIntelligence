package me.videa.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;


public class WebServiceHelper {

	// private static final String
	// NAMESPACE="http://controller.webservice.an.sgtms.sgcc.com";
	public static final String glxsNAMESPACE = "http://service.glxsws.controller.wb.service.com";
	public static final String glNAMESPACE = "http://glxs.controller.wb.service.com";
	public static final String gdNAMESPACE = "http://gd.controller.wb.service.com";
	public static final String jfNAMESPACE = "http://jfxs.controller.wb.service.com";
	public static final String comNAMESPACE = "http://common.controller.wb.service.com";
	public static final String zyzdNAMESPACE = "http://operationinstruct.controller.wb.service.com";
	public static final String tjNAMESPACE = "http://tj.controller.wb.service.com";
	public static final String panelNAMESPACE = "http://panel.controller.wb.service.com";
	private static final String pubNameSPACE = "http://pubcomponent.controller.wb.service.com";
	private static final String EWMSPACE = "http://ewm.controller.wb.service.com";
	public static final String LANDMARKSPACE = "http://zd.controller.wb.service.com";

	public static String getData(SoapObject soapObject, String url,
			String soupaction) {
		String xmlMessage = null;
		try {
			HttpTransportSE transport = new HttpTransportSE(url,
					HttpUtil.SO_TIMEOUT);

			// �汾�����¼��ݣ�SOAPЭ��汾�ţ�����Ҫ���õ�webService�а汾��һ��
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyOut = soapObject;

			// �����ԣ���ʹ���ʵ�����java������webservice������ΪtrueҲ����
			envelope.dotNet = true;
			// �����ԣ�setoutputsoapobject���ú�bodyoutһ��
			envelope.setOutputSoapObject(soapObject);
			// ʹ��call��������WebService����

			transport.call(soupaction, envelope);
			SoapObject sb = (SoapObject) envelope.bodyIn;
			if (sb != null) {
				xmlMessage = sb.getProperty(0).toString(); // ��ȡ�ӷ������˷��ص�xml�ַ�
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (xmlMessage == null) {
			xmlMessage = "{}";
		}
		return xmlMessage;
	}

	
	
	/**
	 * 获取WebService数据
	 * @param method
	 * @param ipAddress
	 * @param webserviceNameSpace
	 * @param param
	 * @return
	 */
	public static String getQueryResult(String method, String ipAddress, 
			String webserviceNameSpace, List<String> param){
		SoapObject soapObject = new SoapObject(webserviceNameSpace, method);
		if(param != null){
			for(int i = 0; i < param.size(); i++){
				soapObject.addProperty("param", param.get(i));
			}
			
		}	
		return getData(soapObject, ipAddress, webserviceNameSpace + "/" + method);
	}

}
