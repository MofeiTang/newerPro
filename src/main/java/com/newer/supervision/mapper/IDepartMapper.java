package com.newer.supervision.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.supervision.domain.ItemRepository;
import com.newer.supervision.domain.Result;
import com.newer.supervision.domain.User;

public interface IDepartMapper {
	@Select("SELECT t1.item_name,t1.source,t1.source_time,t1.牵头部门 HeadDepart,t1.over_time,t2.协作部门 SuppertDepart,t1.item_content FROM(SELECT item_repository.ITEM_NAME,over_time,item_repository.SOURCE,item_repository.SOURCE_TIME,item_repository.ITEM_CONTENT,organication.`ORGNAME` 牵头部门 FROM item_repository,item_org,organication WHERE item_repository.ITEM_CODE=item_org.ITEM_CODE AND item_org.ORGID=organication.ORGID AND item_repository.item_code=#{code} AND item_repository.ITEM_STATUS='5' AND item_org.isprimary=1)t1,(SELECT item_repository.ITEM_NAME,item_repository.SOURCE,item_repository.SOURCE_TIME,item_repository.ITEM_CONTENT,organication.`ORGNAME` 协作部门 FROM item_repository,item_org,organication WHERE item_repository.ITEM_CODE=item_org.ITEM_CODE AND item_org.ORGID=organication.ORGID AND item_repository.ITEM_STATUS='5' AND item_repository.item_code=#{code} AND item_org.isprimary=2)t2 WHERE t1.item_name=t2.item_name")
	public Result queryDept(@Param("code")Integer code);
	
	@Select("SELECT t1.item_name,t1.source,t1.source_time,t1.牵头部门 HeadDepart,t2.协作部门 SuppertDepart,t1.item_content FROM (SELECT item_repository.ITEM_NAME,item_repository.SOURCE,item_repository.SOURCE_TIME,item_repository.ITEM_CONTENT,organication.`ORGNAME` 牵头部门 FROM item_repository,item_org,organication WHERE item_repository.ITEM_CODE=item_org.ITEM_CODE AND item_org.ORGID=organication.ORGID  AND item_org.orgid=1 )t1,(SELECT item_repository.ITEM_NAME,item_repository.SOURCE,item_repository.SOURCE_TIME,item_repository.ITEM_CONTENT,organication.`ORGNAME` 协作部门 FROM item_repository,item_org,organication WHERE item_repository.ITEM_CODE=item_org.ITEM_CODE AND item_org.ORGID=organication.ORGID AND item_org.orgid=2 )t2 WHERE t1.item_name=t2.item_name")
	public Result queryDeptAll();
	//SELECT item_repository.ITEM_NAME,dict.type_name item_Status,item_repository.OVER_TIME,item_repository.item_code FROM item_repository,dict WHERE dict.TYPE='item_status' AND dict.type_id=item_repository.ITEM_STATUS and item_repository.ITEM_STATUS='5'
	@Select("SELECT item_repository.ITEM_NAME,dict.type_name item_Status,item_repository.OVER_TIME,item_repository.item_code FROM item_repository,dict,item_org io \r\n" + 
			"WHERE dict.TYPE='item_status' AND dict.type_id=item_repository.ITEM_STATUS AND io.`ORGID`=#{orgid} AND io.`USERID` IS NULL AND io.`ITEM_CODE`=item_repository.`ITEM_CODE` \r\n" + 
			"AND item_repository.ITEM_STATUS='5'")
	public List<ItemRepository> queryDepartDaiBan(Long orgid);
	
	@Select("SELECT * FROM item_repository WHERE item_code=#{code}")
	public ItemRepository queryItemRespository(@Param("code")Integer code);
	
	/**
	 * 查询部门基本信息
	 * @param userid
	 * @return
	 */
	@Select("SELECT *,dict.TYPE_NAME adverse_Comp FROM USER,item_repository,dict WHERE  dict.type='frequency' AND dict.type_id=item_repository.CALL_BACK and item_repository.`ITEM_CODE`=#{item_code} AND user.`USERID`=#{uid}")
	public ItemRepository queryItemRespositroyByUserID(@Param("uid")Long uid,@Param("item_code")String item_code);
	
	/**
	 * 根据经理id查出部门id，根据部门id
	 * 查询部门下所有员工id
	 * @param userid
	 * @return
	 */
	@Select("SELECT * FROM USER WHERE orgid=(SELECT orgid FROM USER WHERE userid=#{userid})AND job=3")
	public List<User> queryDeptUser(@Param("userid")Long userid);
	
	/**
	 * 修改item_org表的userid
	 */
	@Update("update item_org set userid=#{USERID} where item_code=#{item_code} AND orgid=#{id}")
	public int updateUserId(@Param("USERID")Long USERID,@Param("item_code")String item_code,@Param("id")Long id);
	
	/**
	 * 判断部门都是否指定了负责人
	 * @param code
	 * @return
	 */
	@Select("SELECT COUNT(*) FROM item_org WHERE item_code=#{code} AND userid IS NOT NULL")
	public int getOrgNum(String code);
	
	/**
	 * 修改到期时间
	 * @return
	 */
	@Update("UPDATE item_repository SET over_time=#{time}")
	public int updateOverTime(@Param("time")Date time);
	/**
	 * 
	 * 根据姓名查询id
	 */
	@Select("SELECT * FROM USER WHERE realname=#{realname}")
	@Results({
		@org.apache.ibatis.annotations.Result(property="orgid.orgId",column="ORGID")
	})
	public User queryUserIdByName(@Param("realname")String realname);
	
	/**
	 * 修改item_status
	 */
	@Select("UPDATE item_repository SET item_status='6' where item_code=#{code}")
	public void updateStatus(@Param("code")String code);




}
