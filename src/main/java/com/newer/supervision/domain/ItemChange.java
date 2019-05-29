package com.newer.supervision.domain;
import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 事项变更记录表
 * 
 * @author newerOA-one
 *
 */
public class ItemChange implements Serializable{
	//序列化
	private static final long serialVersionUID = 1L;
	private ItemRepository item_Code; //事项编号
	private String item_Status;//事项状态
	private User user_Id;//操作人
	private String item_Detail;//事项明细
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date item_Time;//事项变更时间
	public ItemRepository getItem_Code() {
		return item_Code;
	}
	public void setItem_Code(ItemRepository item_Code) {
		this.item_Code = item_Code;
	}
	public String getItem_Status() {
		return item_Status;
	}
	public void setItem_Status(String item_Status) {
		this.item_Status = item_Status;
	}
	public User getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(User user_Id) {
		this.user_Id = user_Id;
	}
	public String getItem_Detail() {
		return item_Detail;
	}
	public void setItem_Detail(String item_Detail) {
		this.item_Detail = item_Detail;
	}
	public Date getItem_Time() {
		return item_Time;
	}
	public void setItem_Time(Date item_Time) {
		this.item_Time = item_Time;
	}
}
