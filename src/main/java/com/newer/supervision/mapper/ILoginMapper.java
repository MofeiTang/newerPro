package com.newer.supervision.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.newer.supervision.domain.User;

public interface ILoginMapper {
	@Select("Select * from User where username=#{UserName} and UPASSWORD=#{uPassWord}")
	@Results({
		@Result(property="orgid.orgId",column="ORGID")
	})
	public User Login(@Param("UserName")String UserName,@Param("uPassWord")String uPassWord);

}
