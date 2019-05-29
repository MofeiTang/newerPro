package com.newer.supervision.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 订阅表
 * 
 * @author newerOA-one
 *
 */
public class UserOrder implements Serializable{
	//序列化
	private static final long serialVersionUID = 1L;
	private ItemRepository item_Code;//事项编号
	private User user_Id;//用户编号
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date o_Time;//订阅时间
	private String o_News;//订阅备注
	private String progess; //是否订阅
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
	public Date getO_Time() {
		return o_Time;
	}
	public void setO_Time(Date o_Time) {
		this.o_Time = o_Time;
	}
	public String getO_News() {
		return o_News;
	}
	public void setO_News(String o_News) {
		this.o_News = o_News;
	}
	public String getProgess() {
		return progess;
	}
	public void setProgess(String progess) {
		this.progess = progess;
	}
	
}
