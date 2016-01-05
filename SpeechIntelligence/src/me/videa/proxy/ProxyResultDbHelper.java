package me.videa.proxy;

import java.util.List;

import android.content.Context;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;

public class ProxyResultDbHelper {
	
	
	public ProxyResultDbHelper() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 根据mark获取ProxyResult对象。
	 * @param mark
	 * @return
	 */
	public static ProxyResult findProxyResultByMark(Context mContext, String mark){
		DbUtils dbUtils = DbUtils.create(mContext);
		ProxyResult proxyResult = null;
		try {
			proxyResult = dbUtils.findFirst(Selector.from(ProxyResult.class).where(WhereBuilder.b("MARK", "=", mark)));
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proxyResult;
	}
	/**
	 * 保存ProxyResult对象
	 * @param proxyResult
	 * @return
	 */
	public static boolean saveProxyResult(Context mContext, ProxyResult proxyResult){
		boolean flag = false;
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			flag = dbUtils.saveBindingId(proxyResult);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 更新并返回更新后的结果
	 * @param mContext
	 * @param proxyResult
	 * @return
	 */
	public static ProxyResult updateAndReturnProxyResult(Context mContext, ProxyResult proxyResult){
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			dbUtils.update(proxyResult);
//			dbUtils.execQuery("update PROXYRESULT set STATUS='" + proxyResult.getStatus() + "' where PROXYID='" + proxyResult.getProxyId() + "'");
//			dbUtils.update(Selector.from(ProxyResult.class)
//					.where(WhereBuilder.b("PROXYID", "=", proxyResult.getProxyId())));
//			proxyRe = proxyResult;
//			dbUtils.saveBindingId(proxyResult);
//			proxyRe = dbUtils.findFirst(Selector.from(ProxyResult.class)
//					.where(WhereBuilder.b("PROXYID", "=", proxyResult.getProxyId())));
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return proxyResult;
	}
	
	/**
	 * 根据单个关键字查询全部的结果
	 * @param mContext
	 * @param key
	 * @param value
	 * @return
	 */
	public static List<ProxyResult> findProxyResultsByKey(Context mContext, String key, String value){
		DbUtils dbUtils = DbUtils.create(mContext);
		List<ProxyResult> proxyResults = null;
		try {
			proxyResults = dbUtils.findAll(Selector.from(ProxyResult.class)
					.where(WhereBuilder.b(key, "=", value)));
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proxyResults;
	}
	
	/**
	 * 根据单一键值对查询单一ProxyResult对象
	 * @param mContext
	 * @param key
	 * @param value
	 * @return
	 */
	public static ProxyResult findProxyResultByKey(Context mContext, String key, String value){
		DbUtils dbUtils = DbUtils.create(mContext);
		ProxyResult proxyResults = null;
		try {
			proxyResults = dbUtils.findFirst(Selector.from(ProxyResult.class)
					.where(WhereBuilder.b(key, "=", value)));
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proxyResults;
	}

}
