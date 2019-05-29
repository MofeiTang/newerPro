package com.newer.supervision.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 备用库表
 * 
 * @author newerOA-one
 *
 */
public class ItemRepository implements Serializable {
	// 序列化
	private static final long serialVersionUID = 1L;
	
	private Integer id; // 备用库id
	private String source; // 督办来源
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm",timezone="GMT+8")
	private Date source_Time; // 来源时间
	private String serial_Num; // 原流水号
	private String file_Type; // 文件类型
	private String drafter; // 原拟稿人
	private String drafter_Tel; // 原件联系电话
	private String item_Code; // 事项编号
	private String item_Name; // 事项名称
	private String adverse_Comp; // 对方单位
	private String secrity; // 密级
	private String item_Content; // 事项内容
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date over_Time; // 应办结时间
	private String call_Back; // 反馈频率
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date next_Calltime; // 下次反馈时间
	private String userName;     //对方领导人
	private String del_Status;   //事项的删除状态
	private String item_Dept_Id; // 部门意见
	private String leader_Idea; // 领导批示
	private String item_Status; // 事项状态
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date item_Time;
	private String remark; // 备注
	private String grogess;
	
	public Date getItem_Time() {
		return item_Time;
	}
	public void setItem_Time(Date item_Time) {
		this.item_Time = item_Time;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public String getSerial_Num() {
		return serial_Num;
	}
	public void setSerial_Num(String serial_Num) {
		this.serial_Num = serial_Num;
	}
	public String getFile_Type() {
		return file_Type;
	}
	public void setFile_Type(String file_Type) {
		this.file_Type = file_Type;
	}
	public String getDrafter() {
		return drafter;
	}
	public void setDrafter(String drafter) {
		this.drafter = drafter;
	}
	public String getDrafter_Tel() {
		return drafter_Tel;
	}
	public void setDrafter_Tel(String drafter_Tel) {
		this.drafter_Tel = drafter_Tel;
	}
	public String getItem_Code() {
		return item_Code;
	}
	public void setItem_Code(String item_Code) {
		this.item_Code = item_Code;
	}
	public String getItem_Name() {
		return item_Name;
	}
	public void setItem_Name(String item_Name) {
		this.item_Name = item_Name;
	}
	public String getAdverse_Comp() {
		return adverse_Comp;
	}
	public void setAdverse_Comp(String adverse_Comp) {
		this.adverse_Comp = adverse_Comp;
	}
	public String getSecrity() {
		return secrity;
	}
	public void setSecrity(String secrity) {
		this.secrity = secrity;
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
	public Date getNext_Calltime() {
		return next_Calltime;
	}
	public void setNext_Calltime(Date next_Calltime) {
		this.next_Calltime = next_Calltime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getItem_Dept_Id() {
		return item_Dept_Id;
	}
	public void setItem_Dept_Id(String item_Dept_Id) {
		this.item_Dept_Id = item_Dept_Id;
	}
	public String getLeader_Idea() {
		return leader_Idea;
	}
	public void setLeader_Idea(String leader_Idea) {
		this.leader_Idea = leader_Idea;
	}
	public String getItem_Status() {
		return item_Status;
	}
	public void setItem_Status(String item_Status) {
		this.item_Status = item_Status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDel_Status() {
		return del_Status;
	}
	public void setDel_Status(String del_Status) {
		this.del_Status = del_Status;
	}
	public String getGrogess() {
		return grogess;
	}
	public void setGrogess(String grogess) {
		this.grogess = grogess;
	}
	
}
