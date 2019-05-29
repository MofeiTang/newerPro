package com.newer.supervision.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 用户表
 * 
 * @author newerOA-one
 *
 */
public class User implements Serializable{
	//序列化
	private static final long serialVersionUID = 1L;
	
	private Long userid;//用户id
	private String username;//用户名
	private String realname;//用户真实姓名
	private String upassword;//密码
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date hiredate;//入职日期
	private String edu;//学历
	private String gender;//性别
	private Organication orgid;//部门
	private String job;//职务
	private String jobtype;//职务类型
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone="GMT+8")
	private Date opt_Time;//操作时间
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public String getEdu() {
		return edu;
	}
	public void setEdu(String edu) {
		this.edu = edu;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Organication getOrgid() {
		return orgid;
	}
	public void setOrgid(Organication orgid) {
		this.orgid = orgid;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getJobtype() {
		return jobtype;
	}
	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}
	public Date getOpt_Time() {
		return opt_Time;
	}
	public void setOpt_Time(Date opt_Time) {
		this.opt_Time = opt_Time;
	}
	
	
}
