package com.newer.supervision.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 机构表
 * 
 * @author newerOA-one
 *
 */
public class Organication implements Serializable{
	//序列化
	private static final long serialVersionUID = 1L;
	private Long orgId;//机构ID
	private String orgCode;//机构编码
	private String orgName;//机构名称
	private Organication parent_Orgid;//父机构ID
	private String idPath;//机构id路径
	private String namePath;//机构名称路径
	private String inTro;//简介
	private String  job;//职责
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date  opt_Time;//操作日期
	private Integer orgOrder;//排序号
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Organication getParent_Orgid() {
		return parent_Orgid;
	}
	public void setParent_Orgid(Organication parent_Orgid) {
		this.parent_Orgid = parent_Orgid;
	}
	public String getIdPath() {
		return idPath;
	}
	public void setIdPath(String idPath) {
		this.idPath = idPath;
	}
	public String getNamePath() {
		return namePath;
	}
	public void setNamePath(String namePath) {
		this.namePath = namePath;
	}
	public String getInTro() {
		return inTro;
	}
	public void setInTro(String inTro) {
		this.inTro = inTro;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Date getOpt_Time() {
		return opt_Time;
	}
	public void setOpt_Time(Date opt_Time) {
		this.opt_Time = opt_Time;
	}
	public Integer getOrgOrder() {
		return orgOrder;
	}
	public void setOrgOrder(Integer orgOrder) {
		this.orgOrder = orgOrder;
	}
	
	
}
