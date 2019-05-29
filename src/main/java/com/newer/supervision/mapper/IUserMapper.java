package com.newer.supervision.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.supervision.domain.Page;
import com.newer.supervision.domain.User;



public interface IUserMapper {
	@Delete("delete from user where userid=#{userid}")
	public int remove(Long userid);
	@Insert("insert into user(userid,username,upassword,realname,edu,orgid,job,jobtype,hiredate)values(null,#{username},#{upassword},#{realname},#{edu},#{orgid.orgId},#{job},#{jobtype},#{hiredate})")
	public int addUser(User user);
	@Select("SELECT a.userid,a.username,a.realname,a.hiredate,b.type_name edu,c.orgname,d.type_name job,e.type_name jobtype,a.opt_time  FROM USER a \r\n" + 
			"LEFT JOIN dict b ON b.type='edu' AND a.edu=b.type_id \r\n" + 
			"LEFT JOIN organication c ON a.orgid=c.orgid \r\n" + 
			"LEFT JOIN dict d ON d.type='job' AND a.job=d.type_id \r\n" + 
			"LEFT JOIN dict e ON e.type='jobtype' AND a.jobtype=e.type_id \r\n" + 
			"WHERE 1=1 ORDER BY a.userid limit #{startLine},#{pageSize}")
	@Results({
		@Result(property="userid",column="USERID"),
		@Result(property="username",column="USERNAME"),
		@Result(property="realname",column="REALNAME"),
		@Result(property="hiredate",column="HIREDATE"),
		@Result(property="job",column="JOB"),
		@Result(property="edu",column="EDU"),	
		@Result(property="jobtype",column="JOBTYPE"),	
		@Result(property="orgid.orgName",column="ORGNAME")
	})
	public  List<User> find(Page p);
	@Select("SELECT COUNT(*) FROM user")
	public int findcount();
	@Select("SELECT userid,username,realname,hiredate,edu,orgid,job,jobtype,opt_time  FROM USER WHERE userid=#{userid}")
	@Results({
		@Result(property="userid",column="USERID"),
		@Result(property="username",column="USERNAME"),
		@Result(property="realname",column="REALNAME"),
		@Result(property="hiredate",column="HIREDATE"),
		@Result(property="job",column="JOB"),
		@Result(property="edu",column="EDU"),	
		@Result(property="jobtype",column="JOBTYPE"),	
		@Result(property="orgid.orgId",column="ORGID")
	})
	public  User findid(Long userid);
	@Select("select a.userid,a.username,a.realname,a.hiredate,b.type_name edu,c.orgname,d.type_name job,e.type_name jobtype,a.opt_time  from user a "
			+ "left join dict b on b.type='edu' and a.edu=b.type_id "
			+ "left join organication c on a.orgid=c.orgid "
			+ "left join dict d on d.type='job' and a.job=d.type_id "
			+ "left join dict e on e.type='jobtype' and a.jobtype=e.type_id "
			+ "where a.jobtype='1' order by a.userid ")
	@Results({
		@Result(property="userid",column="USERID"),
		@Result(property="username",column="USERNAME"),
		@Result(property="realname",column="REALNAME"),
		@Result(property="hiredate",column="HIREDATE"),
		@Result(property="job",column="JOB"),
		@Result(property="edu",column="EDU"),	
		@Result(property="jobtype",column="JOBTYPE"),	
		@Result(property="orgid.orgName",column="ORGNAME")
	})
	public  List<User> findld();
	@Update("UPDATE user SET username=#{username},upassword=#{upassword},realname=#{realname},edu=#{edu},orgid=#{orgid.orgId},job=#{job},jobtype=#{jobtype},opt_time=#{opt_Time},hiredate=#{hiredate} WHERE userid=#{userid}")
	public int update(User user);
}
