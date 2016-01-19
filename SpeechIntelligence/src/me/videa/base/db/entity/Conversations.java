package me.videa.base.db.entity;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

@Table(name="CONVERSATIONS")
public class Conversations extends EntityBase{
	@Column(column = "CONVERSATION_ID")
	private String conversation_id;
	@Column(column = "CONVERSATIONS")
	private String conversations;
	@Column(column = "ACTION")
	private String action;
	@Column(column = "MARK")
	private String mark;
	@Column(column = "STATUS")
	private String status;
	
	
	
	public String getConversation_id() {
		return conversation_id;
	}



	public void setConversation_id(String conversation_id) {
		this.conversation_id = conversation_id;
	}



	public String getConversations() {
		return conversations;
	}



	public void setConversations(String conversations) {
		this.conversations = conversations;
	}



	public String getAction() {
		return action;
	}



	public void setAction(String action) {
		this.action = action;
	}



	public String getMark() {
		return mark;
	}



	public void setMark(String mark) {
		this.mark = mark;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	@Override
    public String toString() {
        return "CONVERSATIONS{" +
                ", conversation_id='" + conversation_id + '\'' +
                ", conversations='" + conversations + '\'' +
                ", action=" + action +
                ", mark='" + mark + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}
