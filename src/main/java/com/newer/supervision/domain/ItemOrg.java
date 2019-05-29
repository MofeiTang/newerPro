package com.newer.supervision.domain;

import java.io.Serializable;
/**
 * 事项与部门关系表表
 * 
 * @author newerOA-one
 *
 */
public class ItemOrg implements Serializable{
	//序列化
	private static final long serialVersionUID = 1L;
	private ItemRepository  item_Code; //事项编号
	private Organication orgid;//部门编号
	private User userid;//用户编号
	private String isprimary;//是否为主办部门1是；0否；
	public ItemRepository getItem_Code() {
		return item_Code;
	}
	public void setItem_Code(ItemRepository item_Code) {
		this.item_Code = item_Code;
	}
	public Organication getOrgid() {
		return orgid;
	}
	public void setOrgid(Organication orgid) {
		this.orgid = orgid;
	}
	public User getUserid() {
		return userid;
	}
	public void setUserid(User userid) {
		this.userid = userid;
	}
	public String getIsprimary() {
		return isprimary;
	}
	public void setIsprimary(String isprimary) {
		this.isprimary = isprimary;
	}
	
	
	
}
