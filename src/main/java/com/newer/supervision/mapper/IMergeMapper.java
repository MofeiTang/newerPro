package com.newer.supervision.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
//import org.junit.runners.Parameterized.Parameters;

import com.newer.supervision.domain.Result;
import com.newer.supervision.domain.User;

public interface IMergeMapper {
	/**
	 * 查询所有事件项
	 * 
	 */
	@Select("SELECT DISTINCT item_repository.`ITEM_CODE`,item_repository.item_name,user.`USERNAME`,item_repository.`OVER_TIME`,organication.`ORGNAME` headDepart,item_process.`OPT_TIME` \r\n" + 
			"FROM item_org,item_repository,organication,item_process,USER,MERGE\r\n" + 
			"WHERE item_org.`ISPRIMARY`=1 \r\n" + 
			"AND item_org.`ORGID`=organication.`ORGID` \r\n" + 
			"AND item_org.`USERID`=user.userid \r\n" + 
			"AND item_org.`ITEM_CODE`=item_repository.`ITEM_CODE` \r\n" + 
			"AND item_org.`ORGID`=organication.`ORGID` \r\n" + 
			"AND item_org.`USERID`=user.`USERID` \r\n" + 
			"AND item_repository.`ITEM_CODE`=item_process.`ITEM_CODE` \r\n" + 
			"AND item_repository.item_code=item_org.`ITEM_CODE` \r\n" + 
			"AND item_process.`USER_ID`=user.userid \r\n" + 
			"AND Merge.`item_code`=item_repository.`ITEM_CODE`\r\n" + 
			"AND Merge.`fuid`IS NULL")
	public List<Result> queryMergeAll();
	
	
	/**
	 * 查询子事件项
	 */
	@Select("SELECT DISTINCT item_repository.`ITEM_CODE`,item_repository.item_name,user.`USERNAME`,item_repository.`OVER_TIME`,organication.`ORGNAME` headDepart,item_process.`OPT_TIME`,merge.`fuid`\r\n" + 
			"			FROM item_org,item_repository,organication,item_process,USER,MERGE\r\n" + 
			"			WHERE item_org.`ISPRIMARY`=1 \r\n" + 
			"			AND item_org.`ORGID`=organication.`ORGID` \r\n" + 
			"			AND item_org.`USERID`=user.userid  \r\n" + 
			"			AND item_org.`ITEM_CODE`=item_repository.`ITEM_CODE`\r\n" + 
			"			AND item_org.`ORGID`=organication.`ORGID` \r\n" + 
			"			AND item_org.`USERID`=user.`USERID` \r\n" + 
			"			AND item_repository.`ITEM_CODE`=item_process.`ITEM_CODE` \r\n" + 
			"			AND item_repository.item_code=item_org.`ITEM_CODE` \r\n" + 
			"			AND item_process.`USER_ID`=user.userid \r\n" + 
			"			AND Merge.`item_code`=item_repository.`ITEM_CODE`\r\n" + 
			"			AND Merge.`fuid`=#{fuid}")
	public List<Result>queryChildByMid(@Param("fuid")String fuid);
	
	/**
	 * 查询是否有子类
	 */
	@Select("select ziid from merge where mid=#{mid}")
	public String queryIsHaveChild(@Param("mid")String mid);
	/**
	 * 查询是否有父类
	 */
	@Select("select fuid from merge where mid=#{mid}")
	public String queryIsHaveParrent(@Param("mid")String mid);
	/**
	 * 查询领导人
	 */
	@Select("SELECT * FROM USER where job=1")
	public User queryAdmin();
	/**
	 * 根据事项id来查询单个合并事项
	 * @return
	 */
	@Select("SELECT DISTINCT item_repository.`ITEM_CODE`,item_repository.item_name,user.`USERNAME`,item_repository.`OVER_TIME`,organication.`ORGNAME` headDepart,item_process.`OPT_TIME` FROM item_org,item_repository,organication,item_process,USER WHERE item_org.`ISPRIMARY`=1 AND item_org.`ORGID`=organication.`ORGID` AND item_org.`USERID`=user.userid AND item_org.`ITEM_CODE`=item_repository.`ITEM_CODE` AND item_org.`ORGID`=organication.`ORGID` AND item_org.`USERID`=user.`USERID` AND item_repository.`ITEM_CODE`=item_process.`ITEM_CODE` AND item_repository.item_code=item_org.`ITEM_CODE` AND item_process.`USER_ID`=user.userid and item_repository.item_code=#{item_code}")
	public Result queryMergeOne(String item_code);
	
	/**
	 * 查询基本信息
	 */
	@Select("    SELECT * FROM \r\n" + 
			"(SELECT item_repository.source,item_repository.`SOURCE_TIME`,item_repository.`SERIAL_NUM`,\r\n" + 
			"item_repository.`ITEM_NAME`,item_repository.`ITEM_CODE`,item_repository.`ITEM_TIME`,item_repository.`ADVERSE_COMP`,\r\n" + 
			"item_repository.`SECRITY`,item_repository.`ITEM_CONTENT`,item_repository.`CALL_BACK`,item_repository.`NEXT_CALLTIME`,\r\n" + 
			"user.`REALNAME`,organication.`ORGNAME` HeadDepart,dict.`TYPE_NAME`\r\n" + 
			"FROM item_org,item_repository,USER,organication,dict\r\n" + 
			"WHERE item_org.`USERID`=user.`USERID` AND item_repository.`ITEM_CODE`=item_org.`ITEM_CODE`\r\n" + 
			"AND organication.`ORGID`=item_org.`ORGID`\r\n" + 
			"AND item_org.`ISPRIMARY`=1\r\n" + 
			"AND item_repository.`ITEM_STATUS`=dict.`TYPE_ID`\r\n" + 
			"AND dict.`TYPE`='item_status'\r\n" + 
			"AND item_repository.item_code=#{item_code})t1,\r\n" + 
			"(SELECT  organication.`ORGNAME` suppertDepart\r\n" + 
			"FROM item_org,item_repository,USER,organication,dict\r\n" + 
			"WHERE item_org.`USERID`=user.`USERID` AND item_repository.`ITEM_CODE`=item_org.`ITEM_CODE`\r\n" + 
			"AND organication.`ORGID`=item_org.`ORGID`\r\n" + 
			"AND item_org.`ISPRIMARY`=2\r\n" + 
			"AND item_repository.`ITEM_STATUS`=dict.`TYPE_ID`\r\n" + 
			"AND dict.`TYPE`='item_status'\r\n" + 
			"AND item_repository.item_code=#{item_code})t2")
			public Result queryJB(String item_code);
	/**
	 * 查询delstate
	 * @param item_code
	 * @return
	 */
	@Select("SELECT DICT.`TYPE_NAME` FROM item_repository,dict WHERE dict.`TYPE`='ITEM_STATUS' AND DICT.`TYPE_ID`=item_repository.`DEL_STATUS` AND item_repository.`ITEM_CODE`=#{item_code} ")
	public String queryDelState(String item_code);
	
	/**
	 * 查询itemstate
	 * @param item_code
	 * @return
	 */
	@Select("SELECT DICT.`TYPE_NAME` FROM item_repository,dict WHERE dict.`TYPE`='ITEM_STATUS' AND DICT.`TYPE_ID`=item_repository.`ITEM_STATUS` AND item_repository.`ITEM_CODE`=#{item_code} ")
	public String queryItemState(String item_code);
	/**
	 * 根据item_code查询合并表的主键
	 * @param item_code
	 * @return
	 */
	@Select("SELECT MID FROM MERGE WHERE item_code=#{item_code}")
	public String queryMid(String item_code);
	
	/**
	 * 根据item_code查询合并表的父id
	 * @param item_code
	 * @return
	 */
	@Select("SELECT fuid FROM MERGE WHERE item_code=#{item_code}")
	public String queryFuid(String item_code);
	
	
	/**
	 * 根据item_code查询合并表的子id
	 * @param item_code
	 * @return
	 */
	@Select("SELECT ziid FROM MERGE WHERE item_code=#{item_code}")
	public String queryZiid(String item_code);
	/**
	 * 修改合并表的父id
	 * @param fuid
	 * @param mid
	 * @return
	 */
	@Update("update merge set fuid=#{fuid} where mid=#{mid}")
	public int hebin_setParrent(@Param("fuid")String fuid,@Param("mid")String mid);
	/**
	 * 修改合并表的子id
	 * @param ziid
	 * @param mid
	 * @return
	 */
	@Update("update merge set ziid=#{ziid} where mid=#{mid}")
	public int hebin_setchild(@Param("ziid")String ziid,@Param("mid")String mid);
	
	/**
	 * 取消合并
	 */
	@Update("update merge set fuid=null where item_code=#{item_code}")
	public int qxhb(@Param("item_code")String item_code);
	
}
