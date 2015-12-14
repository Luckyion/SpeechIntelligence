package me.videa.utils;

import java.util.LinkedList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class WebServiceHelper {
	
	private static int SO_TIMEOUT = 10;
	public static final String LANDMARKSPACE = "http://zd.controller.wb.service.com";



	public static String getData(SoapObject soapObject, String url,
			String soupaction) {
		String xmlMessage = null;
		try {
			HttpTransportSE transport = new HttpTransportSE(url,
					SO_TIMEOUT);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyOut = soapObject;
			envelope.dotNet = true;
			envelope.setOutputSoapObject(soapObject);
			transport.call(soupaction, envelope);
			SoapObject sb = (SoapObject) envelope.bodyIn;
			if (sb != null) {
				xmlMessage = sb.getProperty(0).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlMessage;
	}


	/**
	 * 鑾峰彇WebService鏁版嵁
	 * @param method
	 * @param ipAddress
	 * @param webserviceNameSpace
	 * @param param
	 * @return
	 */
	public static String getQueryResult(String method, String ipAddress, 
			String webserviceNameSpace, LinkedList<String> param){
		SoapObject soapObject = new SoapObject(webserviceNameSpace, method);
		if(param != null){
			for(int i = 0; i < param.size(); i++){
				soapObject.addProperty("param", param.get(i));
			}
			
		}	
		return getData(soapObject, ipAddress, webserviceNameSpace + "/" + method);
	}

}

