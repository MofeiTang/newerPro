package com.newer.supervision.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.supervision.domain.ItemProcess;
import com.newer.supervision.domain.Parameter;
import com.newer.supervision.domain.User;
import com.newer.supervision.domain.UserOrder;



public interface ItemListMapper {
	
	/**
	 * 获得事项进展
	 * @return
	 */
	@Select("SELECT k.item_code,item_name,orgname,realname,over_time,item_status,user_id FROM\r\n" + 
			"(SELECT n.item_code,item_name,orgname,realname,over_time,type_name AS item_status FROM \r\n" + 
			"(SELECT i.item_code,item_name,orgname,realname,over_time,m.item_status FROM item_process m JOIN\r\n" + 
			"(SELECT h.item_code,item_name,orgname,realname,over_time FROM item_repository g JOIN\r\n" + 
			"(SELECT item_code,orgname,realname FROM `user` e JOIN \r\n" + 
			"(SELECT d.item_code,orgname,userid FROM organication c JOIN \r\n" + 
			"(SELECT a.item_code,orgid,userid FROM item_repository a JOIN \r\n" + 
			"item_org b ON a.`ITEM_CODE`=b.`ITEM_CODE` AND b.`ISPRIMARY`=1) d \r\n" + 
			"ON c.ORGID=d.orgid) f\r\n" + 
			"ON e.userid=f.userid) h\r\n" + 
			"ON g.`ITEM_CODE`=h.item_code) i ON m.`ITEM_CODE`=i.item_code AND m.bj=1) n\r\n" + 
			" JOIN dict j ON n.item_status=j.type_id AND j.type='item_status') k\r\n" + 
			"LEFT JOIN (SELECT * FROM user_order WHERE user_id=#{id}) l  ON k.item_code=l.item_code;")
	@Results({
		@Result(property="item_Code.item_Code",column="ITEM_CODE"),
		@Result(property="item_Code.item_Name",column="ITEM_NAME"),
		@Result(property="item_Code.grogess",column="USER_ID"),
		@Result(property="user_Id.realname",column="REALNAME"),
		@Result(property="item_Code.over_Time",column="OVER_TIME"),
		@Result(property="user_Id.orgid.orgName",column="ORGNAME"),
		@Result(property="item_Status",column="ITEM_STATUS")
	})
	public List<ItemProcess> getItemProgess(Long id);
	
	/**
	 * 模糊查询
	 * @param id
	 * @return
	 */
	public List<ItemProcess> likeCheck(Parameter par);
	
	/**
	 * 订阅
	 * @param order
	 * @return
	 */
	@Insert("INSERT INTO user_order(ITEM_CODE,USER_ID,O_TIME) VALUES(#{code},#{id},now())")
	public Integer takeItem(@Param("code") String code,@Param("id") Long id);
	
	/**
	 * 设置事项状态
	 * @param code
	 * @param id
	 * @return
	 */
	@Update("UPDATE item_repository ir,`user` u SET ir.`PROGESS`='1' WHERE ir.`ITEM_CODE`=#{code} AND u.`USERID`=#{id}")
	public Integer setTake(@Param("code") String code,@Param("id") Long id);
	
	
	/**
	 * 统计推进中
	 * @param id
	 * @return
	 */
	public List<ItemProcess> getPropelling(@Param("id") Long id);
	
	/**
	 * 统计累计完成
	 */
	public List<ItemProcess> getComplete(@Param("id") Long id);
	
	/**
	 * 我的事项
	 * @param id
	 * @return
	 */
//	@Select("SELECT k.item_code,item_name,orgname,realname,over_time,item_status,user_id FROM \r\n" + 
//			"(SELECT n.item_code,item_name,orgname,realname,over_time,type_name AS item_status FROM  \r\n" + 
//			"(SELECT i.item_code,item_name,orgname,realname,over_time,m.item_status FROM item_process m JOIN \r\n" + 
//			"(SELECT h.item_code,item_name,orgname,realname,over_time FROM item_repository g JOIN \r\n" + 
//			"(SELECT item_code,orgname,realname FROM `user` e JOIN  \r\n" + 
//			"(SELECT d.item_code,orgname,userid FROM organication c JOIN  \r\n" + 
//			"(SELECT a.item_code,orgid,userid FROM item_repository a JOIN  \r\n" + 
//			"item_org b ON a.`ITEM_CODE`=b.`ITEM_CODE` AND b.`ISPRIMARY`=1) d  \r\n" + 
//			"ON c.ORGID=d.orgid) f \r\n" + 
//			"ON e.userid=f.userid AND e.`USERID`=#{id}) h \r\n" + 
//			"ON g.`ITEM_CODE`=h.item_code) i ON m.`ITEM_CODE`=i.item_code AND m.bj=1) n \r\n" + 
//			"JOIN dict j ON n.item_status=j.type_id AND j.type='item_status') k \r\n" + 
//			"LEFT JOIN (SELECT * FROM user_order WHERE user_id=#{id}) l  ON k.item_code=l.item_code")
//	@Results({
//		@Result(property="item_Code.item_Code",column="ITEM_CODE"),
//		@Result(property="item_Code.item_Name",column="ITEM_NAME"),
//		@Result(property="item_Code.grogess",column="USER_ID"),
//		@Result(property="user_Id.realname",column="REALNAME"),
//		@Result(property="item_Code.over_Time",column="OVER_TIME"),
//		@Result(property="user_Id.orgid.orgName",column="ORGNAME"),
//		@Result(property="item_Status",column="ITEM_STATUS")
//	})
	public List<ItemProcess> getMyitem(@Param("id") Long id);
	
	/**
	 * 本月完成
	 * @param id
	 * @return
	 */
	public List<ItemProcess> getmonthcomplete(@Param("id") Long id);
	
	/**
	 * 本月更新
	 * @param id
	 * @return
	 */
	public List<ItemProcess> getmonthupdate(@Param("id") Long id);
	
	/**
	 * 统计待办事项超时
	 * @param id
	 * @return
	 */
	public List<ItemProcess> getOvertime(@Param("id") Long id);
	
	/**
	 * 统计延期
	 * @param id
	 * @return
	 */
	public List<ItemProcess> getDelay(@Param("id") Long id);
}
