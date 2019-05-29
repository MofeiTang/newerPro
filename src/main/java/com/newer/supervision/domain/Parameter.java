package com.newer.supervision.domain;

import java.io.Serializable;
/**
 * 模糊查询参数表
 * @author Administrator
 *
 */
public class Parameter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long userid;
	private String item_code;
	private String item_name;
	private String orgname;
	private Integer overtime;
	private Integer postpone;//延期
	private String item_status;
	private Integer progess;//是否订阅
	
	public Parameter() {
		
	}
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public Integer getOvertime() {
		return overtime;
	}
	public void setOvertime(Integer overtime) {
		this.overtime = overtime;
	}
	public Integer getPostpone() {
		return postpone;
	}
	public void setPostpone(Integer postpone) {
		this.postpone = postpone;
	}
	public String getItem_status() {
		return item_status;
	}
	public void setItem_status(String item_status) {
		this.item_status = item_status;
	}
	public Integer getProgess() {
		return progess;
	}
	public void setProgess(Integer progess) {
		this.progess = progess;
	}
	
	

}
