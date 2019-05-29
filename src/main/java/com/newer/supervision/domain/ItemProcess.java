package com.newer.supervision.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 事项进展表
 * 
 * @author newerOA-one
 *
 */
public class ItemProcess implements Serializable{
	//序列化
	private static final long serialVersionUID = 1L;
	private ItemRepository item_Code;//事项编号
	private String item_Status;//事项状态
	private String item_Status_Desc;//事项状态描述
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd hh:mm",timezone="GMT+8")
	private Date opt_Time;//操作时间
	private User user_Id;//用户id
	private String statusName;
	private String db_Desc;
	private String Bj;
	
	
	public String getBj() {
		return Bj;
	}
	public void setBj(String bj) {
		Bj = bj;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getDb_Desc() {
		return db_Desc;
	}
	public void setDb_Desc(String db_Desc) {
		this.db_Desc = db_Desc;
	}
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
	public String getItem_Status_Desc() {
		return item_Status_Desc;
	}
	public void setItem_Status_Desc(String item_Status_Desc) {
		this.item_Status_Desc = item_Status_Desc;
	}
	public Date getOpt_Time() {
		return opt_Time;
	}
	public void setOpt_Time(Date opt_Time) {
		this.opt_Time = opt_Time;
	}
	public User getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(User user_Id) {
		this.user_Id = user_Id;
	}
	
	
}
