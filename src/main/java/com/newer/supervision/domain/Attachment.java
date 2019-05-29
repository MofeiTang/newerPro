package com.newer.supervision.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 附件表
 * 
 * @author newerOA-one
 *
 */
public class Attachment implements Serializable{
	//序列化
	private static final long serialVersionUID = 1L;
	private Integer id;	//资源id
	private String name;	//附件名称
	private String path; //附件路径
	private ItemRepository item_Code; //事项编码
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date opt_Time;//操作时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public ItemRepository getItem_Code() {
		return item_Code;
	}
	public void setItem_Code(ItemRepository item_Code) {
		this.item_Code = item_Code;
	}
	public Date getOpt_Time() {
		return opt_Time;
	}
	public void setOpt_Time(Date opt_Time) {
		this.opt_Time = opt_Time;
	}
	
	
}
