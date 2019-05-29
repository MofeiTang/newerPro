package com.newer.supervision.mapper;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.newer.supervision.domain.ItemLeader;
import com.newer.supervision.domain.ItemOrg;
import com.newer.supervision.domain.ItemProcess;
import com.newer.supervision.domain.UserOrder;
/**
 * 映射器接口
 * @author Administrator
 *
 */
public interface IIndexMapper {
	/**
	 * 最近更新
	 * @return
	 */
	@Select("SELECT r.ITEM_NAME,o.ORGNAME,p.ITEM_STATUS,p.OPT_TIME,u.REALNAME,d.type_name \r\n" + 
			"FROM item_repository r,organication o,item_process p,USER u,dict d \r\n" + 
			"WHERE r.ITEM_CODE=p.ITEM_CODE AND o.ORGID=u.ORGID AND p.USER_ID=u.USERID AND d.type='ITEM_STATUS' AND p.`BJ`=1 \r\n" + 
			"AND d.TYPE_ID=p.ITEM_STATUS ORDER BY p.`OPT_TIME` DESC LIMIT 0,5")
	@Results({
		@Result(property="item_Code.item_Name",column="ITEM_NAME"),
		@Result(property="user_Id.orgid.orgName",column="ORGNAME"),
		@Result(property="item_Status",column="TYPE_NAME"),
		@Result(property="opt_Time",column="OPT_TIME"),
		@Result(property="user_Id.realname",column="REALNAME")
	})
	public List<ItemProcess> findxin();
	/**
	 * 推进中
	 * @return
	 */
	@Select("SELECT ir.ITEM_NAME,uu.`REALNAME` USERNAME,o.ORGNAME,u.REALNAME,ir.OVER_TIME,ir.NEXT_CALLTIME \r\n" + 
			"FROM item_repository ir,organication o,item_org io,USER u,item_leader il,`user` uu,item_process ip \r\n" + 
			"WHERE io.ORGID=o.ORGID AND io.USERID=u.USERID \r\n" + 
			"AND io.`ISPRIMARY`='1' AND io.`ITEM_CODE`=ip.`ITEM_CODE` \r\n" + 
			"AND ip.`ITEM_STATUS`='12' AND ip.`ITEM_CODE`=ir.`ITEM_CODE` AND ip.`BJ`=1 \r\n" + 
			"AND il.`ITEM_CODE`=ip.`ITEM_CODE` AND il.`USERID`=uu.`USERID` \r\n" + 
			"AND ip.`USER_ID`=io.`USERID`")
	@Results({
		@Result(property="item_Code.item_Name",column="ITEM_NAME"),
		@Result(property="user_Id.username",column="USERNAME"),
		@Result(property="user_Id.orgid.orgName",column="ORGNAME"),
		@Result(property="user_Id.realname",column="REALNAME"),
		@Result(property="item_Code.over_Time",column="OVER_TIME"),
		@Result(property="item_Code.next_Calltime",column="NEXT_CALLTIME")
	})
	public List<ItemLeader> findtuijin();
	
	/**
	 * 订阅模糊查询
	 * @param item_Name
	 * @return
	 */
	public List<ItemOrg> likedingyue(@Param("item_Name_") String item_Name,@Param("id") Long id);
	/**
	 * 订阅
	 * @return
	 */
	@Select("SELECT ir.`ITEM_CODE`,ir.`ITEM_NAME`,d.`TYPE_NAME`,o.orgname,ir.`OVER_TIME` FROM user_order uo,item_repository ir,dict d,organication o,item_org io,item_process ip WHERE uo.`ITEM_CODE`=io.`ITEM_CODE` AND io.`ISPRIMARY`='1' AND io.`ORGID`=o.orgid AND uo.`ITEM_CODE`=ir.`ITEM_CODE` AND d.`TYPE_ID`=ip.`ITEM_STATUS` AND d.`TYPE`='ITEM_STATUS' AND uo.`ITEM_CODE`=ip.`ITEM_CODE` AND uo.`USER_ID`=#{id}")
	@Results({
		@Result(property="item_Code.item_Code",column="item_Code"),
		@Result(property="item_Code.item_Name",column="ITEM_NAME"),
		@Result(property="item_Code.item_Status",column="TYPE_NAME"),
		@Result(property="user_Id.orgid.orgName",column="ORGNAME"),
		@Result(property="item_Code.over_Time",column="OVER_TIME")
//		@Result(property="orgid.opt_Time",column="OPT_TIME"),
	})
	public List<UserOrder> finddingyue(Long id);
	
	/**
	 * 取消订阅
	 * @param code
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM user_order WHERE `USER_ID`=#{id} AND `ITEM_CODE`=#{item_code}")
	public Integer delorder(@Param("item_code") String code,@Param("id") Long id);
	
	/**
	 * 统计
	 * @return
	 */
	/*总计*/
	/*累计完成*/
//	@Select("SELECT  COUNT(*) FROM USER u,item_repository ir,item_process ip WHERE u.USERID=#{userid}\r\n" + 
//			"AND ip.`USER_ID`=u.`USERID` AND ip.`ITEM_CODE`=ir.`ITEM_CODE`\r\n" + 
//			"AND ir.ITEM_STATUS='10'")
	public Long tongjiWC(@Param("userid") Long userid);
	/*推进中*/
//	@Select("SELECT COUNT(*) FROM item_process ip,item_org io WHERE ip.user_id=#{userid} AND io.`ISPRIMARY`='1' \r\n" + 
//			"AND io.`ITEM_CODE`=ip.`ITEM_CODE` AND item_status='12' AND ip.`BJ`=1")
	public Long tongjiTJZ(@Param("userid") Long userid);
	/*我的事项*/
	@Select("(SELECT COUNT(*) FROM `user` e JOIN  \r\n" + 
			"(SELECT d.item_code,orgname,userid FROM organication c JOIN  \r\n" + 
			"(SELECT a.item_code,orgid,userid FROM item_repository a JOIN  \r\n" + 
			"item_org b ON a.`ITEM_CODE`=b.`ITEM_CODE` AND b.`ISPRIMARY`=1) d  \r\n" + 
			"ON c.ORGID=d.orgid) f \r\n" + 
			"ON e.userid=f.userid AND e.`USERID`=#{userid})")
	public Long tongjiBYXZ(Long userid);
	
//	督办员管理的事项 
	@Select("select count(*) from item_repository")
	public Long dubanItem();
	
	//SELECT COUNT(*) FROM item_repository ir,(SELECT *,DATE_SUB(item_time,INTERVAL 3 DAY) times FROM item_repository) it WHERE\r\n" + 
	//"it.item_code=ir.item_code AND DATE_FORMAT(it.times,'%Y%m') = DATE_FORMAT(CURDATE() ,'%Y%m')
	/*本月完成*/
//	@Select("SELECT  COUNT(*) FROM USER u,item_repository ir,item_process ip WHERE u.USERID=#{userid}\r\n" + 
//			"AND ip.`USER_ID`=u.`USERID` AND ip.`ITEM_CODE`=ir.`ITEM_CODE`\r\n" + 
//			"AND ir.ITEM_STATUS='10' AND DATE_FORMAT(ip.`OPT_TIME`, '%Y%m') = DATE_FORMAT(CURDATE() ,'%Y%m')")
	public Long tongjiBYWC(@Param("userid") Long userid);
	/*本月更新*/
//	@Select("SELECT COUNT(*) FROM USER u,item_process ip,item_org io WHERE u.USERID=#{userid}\r\n" + 
//			"AND u.`USERID`=io.userid AND io.item_code=ip.item_code AND ip.item_status='12'\r\n" + 
//			"AND DATE_FORMAT(ip.opt_time, '%Y%m') = DATE_FORMAT(CURDATE() ,'%Y%m') AND ip.`BJ`=1")
	public Long tongjiBYGX(@Param("userid") Long userid);
	/*当前延期*/
//	@Select("SELECT count(*) FROM USER u,item_repository ir,item_org io WHERE u.USERID=#{userid} \r\n" + 
//			"AND u.`USERID`=io.userid AND io.item_code=ir.item_code\r\n" + 
//			"AND UNIX_TIMESTAMP(ir.OVER_TIME)<UNIX_TIMESTAMP(NOW())")
	public Long tongjiYQ(@Param("userid") Long userid);
	/*当前待办事项超时*/
//	@Select("SELECT COUNT(ir.item_time) FROM USER u,item_repository ir,item_process ip WHERE u.USERID=#{userid} \r\n" + 
//			"AND u.`USERID`=ip.user_id AND ip.item_code=ir.item_code\r\n" + 
//			"AND UNIX_TIMESTAMP(ir.`NEXT_CALLTIME`)<UNIX_TIMESTAMP(NOW())")
	public Long tongjiCS(@Param("userid") Long userid);	
	/*上次登录时间*/
	@Select("SELECT OPT_TIME FROM USER where userid=#{id}")
	public String loginTime(Long id);
	/*设置登入时间*/
	@Select("update user set opt_time=now() where userid=#{id}")
	public Integer setLoginTime(Long id);
}
