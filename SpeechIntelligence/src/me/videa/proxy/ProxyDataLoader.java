package me.videa.proxy;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import android.content.Context;

public class ProxyDataLoader {
	
	private Context mContext;
	
	public ProxyDataLoader(Context mContext) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
	}
	
	/**
	 *  添加数据库未提交成功的参数至缓存
	 */
	public void add(){
		DbUtils dbUtils = DbUtils.create(mContext);
		List<ProxyResult> results;
		try {
			results = dbUtils.findAll(Selector
					.from(ProxyResult.class).where("STATUS", "=", "0").and("NOTE", "=", "1"));
			addWSParam(results);
			results = dbUtils.findAll(Selector
					.from(ProxyResult.class).where("STATUS", "=", "0").and("NOTE", "=", "2"));
			addServletParam(results);
			results = dbUtils.findAll(Selector
					.from(ProxyResult.class).where("STATUS", "=", "0").and("NOTE", "=", "2"));
			addEntityParam(results);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	
	/**
	 * 添加Servlet参数
	 * @param results
	 */
   private void addServletParam(List<ProxyResult> results){	   
	   if(results != null && results.size() != 0){
		   ServParams servParams = null;	
			for(ProxyResult result : results){
				servParams = new ServParams(mContext, result.getProxyUrl());	
				servParams.setCache(true);
				servParams.addParams(getServParams(result.getContent()));				
			}
		}
   }
   /**
    * 添加Webserivce参数
    * @param results
    */
   private void addWSParam(List<ProxyResult> results){
	   if(results != null && results.size() != 0){
		   WSParams wsParams = null;		
			for(ProxyResult result : results){
				wsParams = new WSParams(mContext, result.getProxyUrl(), result.getProxyMethod(), result.getProxyNameSpace());
				wsParams.setCache(true);
				wsParams.addParams(getpWsParams(result.getContent()));				
			}
		}
   }
   /**
    * 添加文件参数
    * @param results
    */
   private void addEntityParam(List<ProxyResult> results){
	   if(results != null && results.size() != 0){
		   	EntityParams entityParams = null;
		   	ProxyEntity entity = null;		
			for(ProxyResult result : results){
				entity = new ProxyEntity();
				entity.setBindId(result.getProxyId());
				entity.setBindUser(result.getUserID());
				entity.setUploadUrl(result.getProxyUrl());
				String filepath = result.getContent();
				File file = new File(filepath);
				if(file.exists()){
					entity.setFileName(file.getName());
					entity.setLocalFilePath(filepath);
				}
				
			}
			entityParams = new EntityParams(mContext);
			entityParams.setCache(true);
			entityParams.addParams(entity);
		}
   }
	
	/**
	 * 获取Servlet请求参数
	 * @param params
	 * @param content
	 * @return
	 */
	private Map<String, String> getServParams(String content){
		if(content == null || content.equals("")){
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		String[] contents = null;
		if(content.contains("\\")){
			contents = content.split("\\");
			for(String string : contents){
				String[] pars = string.split("=");
				map.put(pars[0], pars[1]);
			}
		}else{
			String[] pars = content.split("=");
			map.put(pars[0], pars[1]);
		}
		return map;		
	}
	/**
	 * 获取WebService请求参数
	 * @param params
	 * @return
	 */
	private List<String> getpWsParams(String content){
		if(content == null || content.equals("")){
			return null;
		}
		String[] contents = null;
		List<String> list = new ArrayList<String>();
		if(content.contains("\\")){
			contents = content.split("\\");
			for(String string : contents){
				String[] pars = string.split("=");
				list.add(pars[1]);
			}
		}else{
			String[] pars = content.split("=");
			list.add(pars[1]);
		}
		return list;		
	}
}
