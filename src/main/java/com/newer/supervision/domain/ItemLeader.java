package com.newer.supervision.domain;
import java.io.Serializable;
/**
 * 事项与领导关系表
 * 
 * @author newerOA-one
 *
 */
public class ItemLeader implements Serializable{
	//序列化
	private static final long serialVersionUID = 1L;
	
	private ItemRepository  item_Code; //事项编号
	private User user_Id;	//用户编号
	public ItemRepository getItem_Code() {
		return item_Code;
	}
	public void setItem_Code(ItemRepository item_Code) {
		this.item_Code = item_Code;
	}
	public User getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(User user_Id) {
		this.user_Id = user_Id;
	}
	
}
