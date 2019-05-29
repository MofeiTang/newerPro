package com.newer.supervision.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.supervision.domain.Dict;
import com.newer.supervision.domain.Organication;
import com.newer.supervision.domain.Page;
import com.newer.supervision.domain.User;

public interface IOrganicattonMapper {
	@Delete("delete from organication where orgid=#{orgid}")
	public int remove(Long orgid);
	@Insert("insert into organication(orgid,orgname,namepath,intro,job)values(null,#{orgName},#{namePath},#{inTro},#{job})")
	public int addOrganicatton(Organication org);
	@Select("select orgid,orgname,namepath,intro,job,opt_time  from organication limit #{startLine},#{pageSize}")
	public  List<Organication> find(Page p);
	@Select("select orgid,orgname,namepath,intro,job,opt_time  from organication where orgid=#{orgid}")
	public  Organication findorgid(Long orgid);
	@Select("SELECT COUNT(*) FROM organication")
	public int findcount();
	@Update("UPDATE organication SET orgname=#{orgName},namepath=#{namePath},intro=#{inTro},job=#{job},opt_time=#{opt_Time} WHERE orgid=#{orgId}")
	public int update(Organication org);
	@Select("select orgid,orgname,namepath,intro,job,opt_time  from organication")
	public  List<Organication> query();
	/**
	 * 获得部门路径
	 * @return
	 */
	@Select("SELECT orgid,namepath FROM organication WHERE parent_orgid=1")
	public List<Organication> getParentPath();
}
