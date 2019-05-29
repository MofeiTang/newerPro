package com.newer.supervision.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 数据字典表
 * 
 * @author newerOA-one
 *
 */
public class Dict implements Serializable{
	//序列化
	private static final long serialVersionUID = 1L;

	private String type;//大类
	private String type_ID;//小类ID
	private String type_Name;//小类名称
	private String desc;//小类描述
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date  opt_Time;//操作时间
	private Integer order;//排序号
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType_ID() {
		return type_ID;
	}
	public void setType_ID(String type_ID) {
		this.type_ID = type_ID;
	}
	public String getType_Name() {
		return type_Name;
	}
	public void setType_Name(String type_Name) {
		this.type_Name = type_Name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getOpt_Time() {
		return opt_Time;
	}
	public void setOpt_Time(Date opt_Time) {
		this.opt_Time = opt_Time;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	
}
