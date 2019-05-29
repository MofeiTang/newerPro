package com.newer.supervision.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Result implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ITEM_NAME;
	private String SOURCE;
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date SOURCE_TIME;
	private String ITEM_CONTENT;
	private String HeadDepart;
	private String SuppertDepart;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date over_time;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date opt_time;
	private String item_code;
	private String username;
	private String realname;
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date item_time;
	private String SERIAL_NUM;
	private Date ITEM_TIME;
	private String SECRITY;
	private String CALL_BACK;
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date NEXT_CALLTIME;
	private String ADVERSE_COMP;
	private boolean displayState;
	private String fuid;
	public boolean isDisplayState() {
		return displayState;
	}
	public void setDisplayState(boolean displayState) {
		this.displayState = displayState;
	}
	public String getFuid() {
		return fuid;
	}
	public void setFuid(String fuid) {
		this.fuid = fuid;
	}
	public String getADVERSE_COMP() {
		return ADVERSE_COMP;
	}
	public void setADVERSE_COMP(String aDVERSE_COMP) {
		ADVERSE_COMP = aDVERSE_COMP;
	}
	public String getSERIAL_NUM() {
		return SERIAL_NUM;
	}
	public void setSERIAL_NUM(String sERIAL_NUM) {
		SERIAL_NUM = sERIAL_NUM;
	}
	public Date getITEM_TIME() {
		return ITEM_TIME;
	}
	public void setITEM_TIME(Date iTEM_TIME) {
		ITEM_TIME = iTEM_TIME;
	}
	public String getSECRITY() {
		return SECRITY;
	}
	public void setSECRITY(String sECRITY) {
		SECRITY = sECRITY;
	}
	public String getCALL_BACK() {
		return CALL_BACK;
	}
	public void setCALL_BACK(String cALL_BACK) {
		CALL_BACK = cALL_BACK;
	}
	public Date getNEXT_CALLTIME() {
		return NEXT_CALLTIME;
	}
	public void setNEXT_CALLTIME(Date nEXT_CALLTIME) {
		NEXT_CALLTIME = nEXT_CALLTIME;
	}
	public Date getOpt_time() {
		return opt_time;
	}
	public void setOpt_time(Date opt_time) {
		this.opt_time = opt_time;
	}
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public Date getItem_time() {
		return item_time;
	}
	public void setItem_time(Date item_time) {
		this.item_time = item_time;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public Date getOver_time() {
		return over_time;
	}
	public void setOver_time(Date over_time) {
		this.over_time = over_time;
	}
	public String getITEM_NAME() {
		return ITEM_NAME;
	}
	public void setITEM_NAME(String iTEM_NAME) {
		ITEM_NAME = iTEM_NAME;
	}
	public String getSOURCE() {
		return SOURCE;
	}
	public void setSOURCE(String sOURCE) {
		SOURCE = sOURCE;
	}
	public Date getSOURCE_TIME() {
		return SOURCE_TIME;
	}
	public void setSOURCE_TIME(Date sOURCE_TIME) {
		SOURCE_TIME = sOURCE_TIME;
	}
	public String getITEM_CONTENT() {
		return ITEM_CONTENT;
	}
	public void setITEM_CONTENT(String iTEM_CONTENT) {
		ITEM_CONTENT = iTEM_CONTENT;
	}
	public String getHeadDepart() {
		return HeadDepart;
	}
	public void setHeadDepart(String headDepart) {
		HeadDepart = headDepart;
	}
	public String getSuppertDepart() {
		return SuppertDepart;
	}
	public void setSuppertDepart(String suppertDepart) {
		SuppertDepart = suppertDepart;
	}
	

}
