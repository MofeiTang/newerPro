package com.newer.supervision.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.newer.supervision.domain.ItemInformation;
import com.newer.supervision.domain.ItemOrg;
import com.newer.supervision.domain.ItemProcess;
import com.newer.supervision.domain.Organication;
import com.newer.supervision.domain.User;

public interface IItemInformationMapper {

	@Select("SELECT source,source_Time,item_code,item_Name,userName,item_Content,over_Time,call_back,next_Calltime,Type_name AS file_Type FROM (SELECT source,source_Time,item_code,item_Name,userName,item_Content,over_Time,Type_name AS call_back,next_Calltime,file_Type\r\n" + 
			" FROM item_repository a JOIN dict b ON (a.CALL_BACK=b.TYPE_ID AND b.TYPE='FREQUENCY') WHERE Item_Code=#{itemCode}) c JOIN dict d ON (c.FILE_TYPE=d.TYPE_ID AND d.TYPE='FILE_TYPE')")
	@Results({
		@Result(property="itemCode",column="item_code"),
		@Result(property="itemOrgs",column="item_code",
				many=@Many(select="com.newer.supervision.mapper.IItemInformationMapper.queryInformationOrgs")),
		@Result(property="process",column="item_code",
				one=@One(select="com.newer.supervision.mapper.IItemInformationMapper.queryProcess")),
	})
	public ItemInformation queryInformation(String itemCode);
	
	
	@Select("select item_code,orgid,userid,isprimary from item_org where item_code=#{item_code}")
	@Results({
		@Result(property="orgid",column="orgid",javaType=Organication.class,
				one=@One(select="com.newer.supervision.mapper.IItemInformationMapper.queryOrg")),
		@Result(property="userid",column="userid",javaType=User.class,
				one=@One(select="com.newer.supervision.mapper.IItemInformationMapper.queryUser")),
	})
	public List<ItemOrg> queryInformationOrgs(String item_code);
	
	/**
	 * 查询部门名称
	 * @param orgid
	 * @return
	 */
	@Select("select orgname from Organication where orgid=#{orgid}")
	public Organication queryOrg(String orgid);

	/**
	 * 查询用户名
	 * @param USERID
	 * @return
	 */
	@Select("select realname,username from `user` where userid=#{userid}")
	public User queryUser(String userid);
	
	/**
	 * 查询事项当前进展
	 */
	@Select("SELECT type_name AS item_status,item_status_desc,a.opt_time FROM item_process a JOIN dict b ON a.ITEM_STATUS=b.TYPE_ID AND b.TYPE='ITEM_STATUS' where item_code=#{item_code}")
	public ItemProcess queryProcess(String item_code);
}
