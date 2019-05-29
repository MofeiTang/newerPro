package com.newer.supervision.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ItemInformation implements Serializable{
	
	private String source; // 督办来源
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date source_Time; // 来源时间
	private String itemCode; // 事项编号
	private String item_Name; // 事项名称
	private String userName;  //公司领导人
	private List<ItemOrg> itemOrgs; //事项与部门的关系
	private String item_Content; // 事项内容
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date over_Time; // 应办结时间
	private String call_Back; // 反馈频率
	private ItemProcess process;//事项当前进展
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date next_Calltime; // 下次反馈时间
	private String file_Type; // 文件类型
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Date getSource_Time() {
		return source_Time;
	}
	public void setSource_Time(Date source_Time) {
		this.source_Time = source_Time;
	}

	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItem_Name() {
		return item_Name;
	}
	public void setItem_Name(String item_Name) {
		this.item_Name = item_Name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<ItemOrg> getItemOrgs() {
		return itemOrgs;
	}
	public void setItemOrgs(List<ItemOrg> itemOrgs) {
		this.itemOrgs = itemOrgs;
	}
	public String getItem_Content() {
		return item_Content;
	}
	public void setItem_Content(String item_Content) {
		this.item_Content = item_Content;
	}
	public Date getOver_Time() {
		return over_Time;
	}
	public void setOver_Time(Date over_Time) {
		this.over_Time = over_Time;
	}
	public String getCall_Back() {
		return call_Back;
	}
	public void setCall_Back(String call_Back) {
		this.call_Back = call_Back;
	}
	public ItemProcess getProcess() {
		return process;
	}
	public void setProcess(ItemProcess process) {
		this.process = process;
	}
	public Date getNext_Calltime() {
		return next_Calltime;
	}
	public void setNext_Calltime(Date next_Calltime) {
		this.next_Calltime = next_Calltime;
	}
	public String getFile_Type() {
		return file_Type;
	}
	public void setFile_Type(String file_Type) {
		this.file_Type = file_Type;
	}
	
	
}
