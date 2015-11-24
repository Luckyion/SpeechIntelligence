package me.videa.base.db.entity;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

@Table(name="MWT_IS_USER")
public class Action extends EntityBase{
	
	@Column(column = "ACTION_ID")
	private String action_id;
	@Column(column = "ACTION")
	private String action;
	@Column(column = "SWMANTIC")
	private String semantic;//加密语义
	@Column(column = "ENCRYPT_ROLE")
	private String encrypt_role;//加密规则
	@Column(column = "PASSWORD")
	private String password;//数据获取密钥
	@Column(column = "NOTE")
	private String note;//记录
	@Column(column = "MARK")
	private String mark;//备注
	@Column(column = "CREATE_TIME")
	private String create_time;//创建时间
	@Column(column = "MODIFY_TIME")
	private String modify_time;
	@Column(column = "LOCK_STATUS")
	private String lock_status;//锁定状态



	public String getAction_id() {
		return action_id;
	}



	public void setAction_id(String action_id) {
		this.action_id = action_id;
	}



	public String getAction() {
		return action;
	}



	public void setAction(String action) {
		this.action = action;
	}



	public String getSemantic() {
		return semantic;
	}



	public void setSemantic(String semantic) {
		this.semantic = semantic;
	}



	public String getEncrypt_role() {
		return encrypt_role;
	}



	public void setEncrypt_role(String encrypt_role) {
		this.encrypt_role = encrypt_role;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getNote() {
		return note;
	}



	public void setNote(String note) {
		this.note = note;
	}



	public String getMark() {
		return mark;
	}



	public void setMark(String mark) {
		this.mark = mark;
	}



	public String getCreate_time() {
		return create_time;
	}



	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}



	public String getModify_time() {
		return modify_time;
	}



	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}







	public String getLock_status() {
		return lock_status;
	}



	public void setLock_status(String lock_status) {
		this.lock_status = lock_status;
	}



	@Override
    public String toString() {
        return "MWT_IS_USER{" +
                "user_id=" + getId() +
                ", action_id='" + action_id + '\'' +
                ", action='" + action + '\'' +
                ", semantic=" + semantic +
                ", encrypt_role='" + encrypt_role + '\'' +
                ", password='" + password + '\'' +
                ", note='" + note + '\'' +
                ", mark='" + mark + '\'' +
                ", create_time='" + create_time + '\'' +
                ", modify_time='" + modify_time + '\'' +
                ", lock_status='" + lock_status + '\'' +
                '}';
    }

}
