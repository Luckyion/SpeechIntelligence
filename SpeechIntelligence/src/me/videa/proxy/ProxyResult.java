package me.videa.proxy;

import java.io.Serializable;

import me.videa.base.db.entity.EntityBase;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

@Table(name="PROXYRESULT")
public class ProxyResult extends EntityBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6163184886986159631L;
	@Column(column = "PROXYID")
	private String proxyId;//代理对象ID   自动生成的唯一值
	@Column(column = "USERID")
	private String userID;//代理绑定用户id   
	@Column(column = "MARK")
	private String mark;//代理对象标识   默认为Context对象的类名
	@Column(column = "CONTENT")
	private String content;//代理对象请求参数内容   请求参数转换成字符串的结果
	@Column(column = "PROXYURL")
	private String proxyUrl;//请求URL 
	@Column(column = "PROXYMETHOD")
	private String proxyMethod;//Service请求方法
	@Column(column = "PROXYNAMESPACE")
	private String proxyNameSpace;//Service请求命名空间
	@Column(column = "REQUESTTYPE")
	private String requestType;//代理说明： 1. WebService 2. Servlet 3.File
	@Column(column = "STATUS")
	private String status;//提交状态     0 表示未提交； -1 表示已提交
	@Column(column = "ISNOTIFY")
	private String isNotify;//是否已通知   0表示未未通知；  -1 表示已通知
	@Column(column = "ISHANDLED")
	private String isHandled;//通知后是否被处理   0 表示未处理 ；  -1 表示已处理
	@Column(column = "FAILEDTIMES")
	private String failedTimes;//网络状态正常时请求失败次数   0 表示没有提交失败的记录
	
	
	
	
	public String getProxyId() {
		return proxyId;
	}




	public void setProxyId(String proxyId) {
		this.proxyId = proxyId;
	}




	public String getMark() {
		return mark;
	}




	public void setMark(String mark) {
		this.mark = mark;
	}




	public String getUserID() {
		return userID;
	}




	public void setUserID(String userID) {
		this.userID = userID;
	}




	public String getContent() {
		return content;
	}




	public void setContent(String content) {
		this.content = content;
	}




	public String getProxyUrl() {
		return proxyUrl;
	}




	public void setProxyUrl(String proxyUrl) {
		this.proxyUrl = proxyUrl;
	}




	public String getProxyMethod() {
		return proxyMethod;
	}




	public void setProxyMethod(String proxyMethod) {
		this.proxyMethod = proxyMethod;
	}




	public String getProxyNameSpace() {
		return proxyNameSpace;
	}




	public void setProxyNameSpace(String proxyNameSpace) {
		this.proxyNameSpace = proxyNameSpace;
	}





	public String getRequestType() {
		return requestType;
	}




	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public String getIsNotify() {
		return isNotify;
	}




	public void setIsNotify(String isNotify) {
		this.isNotify = isNotify;
	}




	public String getIsHandled() {
		return isHandled;
	}




	public void setIsHandled(String isHandled) {
		this.isHandled = isHandled;
	}


	


	public String getFailedTimes() {
		return failedTimes;
	}




	public void setFailedTimes(String failedTimes) {
		this.failedTimes = failedTimes;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	@Override
    public String toString() {
        return "ProxyResult{" +
                "id=" + getId() + 
                ", proxyId='" + proxyId + '\'' +
                ", userID='" + userID + '\'' +
                ", mark='" + mark + '\'' +
                ", content='" + content + '\'' +
                ", proxyUrl='" + proxyUrl + '\'' +
                ", proxyMethod='" + proxyMethod + '\'' +
                ", proxyNameSpace='" + proxyNameSpace + '\'' +
                ", requestType='" + requestType + '\'' +
                ", status='" + status + '\'' +
                ", isNotify='" + isNotify + '\'' +
                ", isHandled='" + isHandled + '\'' +
                ", failedTimes='" + failedTimes + '\'' +
                '}';
    }

}
