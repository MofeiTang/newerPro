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
import com.newer.supervision.domain.ItemRepository;

public interface IItemProcessMapper {

	/**
	 * 负责人待办
	 * @return
	 */
	@Select("SELECT a.ITEM_CODE,a.ITEM_NAME,b.TYPE_NAME AS ITEM_STATUS,a.NEXT_CALLTIME FROM item_repository a \r\n" + 
			"JOIN dict b ON a.ITEM_STATUS=b.TYPE_ID AND (b.TYPE_ID='6' OR b.TYPE_ID='8')\r\n" + 
			"WHERE b.type='item_status' AND a.ITEM_CODE IN (SELECT Item_Code FROM item_org WHERE userId=#{userId})")
	//SELECT a.ITEM_CODE,a.ITEM_NAME,b.TYPE_NAME AS ITEM_STATUS,a.NEXT_CALLTIME FROM item_repository a \r\n" + 
	//"JOIN dict b ON a.ITEM_STATUS=b.TYPE_ID AND (b.TYPE_ID='6' OR b.TYPE_ID='8') JOIN item_process ip \r\n" + 
	//"WHERE b.type='item_status' AND a.ITEM_CODE IN (SELECT Item_Code FROM item_org WHERE userId=#{userId}) \r\n" + 
	//"AND ip.item_status='12'AND ip.item_code=a.item_code
    public List<ItemRepository> queryDaiBan(@Param("userId") Long userId);
	//SELECT a.ITEM_CODE,a.ITEM_NAME,b.TYPE_NAME AS ITEM_STATUS,a.NEXT_CALLTIME FROM item_repository a JOIN dict b ON a.ITEM_STATUS=b.TYPE_ID AND (b.TYPE_ID='6' OR b.TYPE_ID='8') WHERE b.type='item_status' AND a.ITEM_CODE IN (SELECT Item_Code FROM item_org WHERE userId=#{userId}
	/**
	 * 督办员通过事项
	 */
	@Update("update item_process set db_desc=#{db_Desc},bj=1 where item_code=#{itemCode}")
	public void updateProcess(@Param("db_Desc") String db_Desc,@Param("itemCode") String itemCode);
	
	/**
	 * 首先将标记1删除
	 */
	@Delete("DELETE FROM item_process WHERE item_code=#{itemCode} AND bj=1")
	public void deleteProcess(String itemCode);
	
	/**
	 * 督办员不同意将意见赋值
	 */
	@Update("update item_process set db_desc=#{db_Desc} where item_code=#{itemCode} and bj=2")
	public void updateDisProcess(@Param("db_Desc") String db_Desc,@Param("itemCode") String itemCode);
	
	/**
	 * 如果进展未存在则添加进展
	 * @param itemProcess
	 */
	@Insert("insert into item_process values(#{itemProcess.item_Code.item_Code},#{itemProcess.item_Status},#{itemProcess.item_Status_Desc},#{itemProcess.opt_Time},#{itemProcess.user_Id.userid},#{itemProcess.db_Desc},#{itemProcess.bj})")
	public void updateItem(@Param("itemProcess") ItemProcess itemProcess);
	
	@Insert("insert into merge(item_code) values(#{code})")
	public int insertMerge(String code);
	
	/**
	 * 如果进展已存在则修改进展
	 * @param itemProcess
	 */
	@Update("update item_process set item_status=#{itemProcess.item_Status},item_Status_Desc=#{itemProcess.item_Status_Desc},opt_Time=#{itemProcess.opt_Time},db_Desc=#{itemProcess.db_Desc} where user_id=#{itemProcess.user_Id.userid} and item_code=#{itemProcess.item_Code.item_Code}")
	public void updItem(@Param("itemProcess") ItemProcess itemProcess);
	
	/**
	 * 通过事项编号查询该进展
	 * @param itemCode
	 * @param userId
	 * @return
	 */
	@Select("SELECT * FROM item_process WHERE item_code=#{itemCode} AND bj=2")
	@Results({
		@Result(property="item_Code.item_Code",column="ITEM_CODE"),
		@Result(property="user_Id.userid",column="user_ID"),
	})
	public ItemProcess queryProcess(@Param("itemCode") String itemCode);

	/**
	 * 待办任务已经完成修改事项状态
	 */
	@Update("update item_repository set item_status='7' where item_code=#{item_code}")
	public void updateDaiBan(String item_code);
	
	/**
	 * 待办任务完成修改事项状态(督办员同意)
	 */
	@Update("update item_repository set item_status='6',next_Calltime=#{itemProcess.item_Code.next_Calltime} where item_code=#{itemProcess.item_Code.item_Code}")
	public void updateAgree(@Param("itemProcess") ItemProcess itemProcess);
	
	/**
	 * 待办任务已经完成修改事项状态(督办员不同意)
	 */
	@Update("update item_repository set item_status='8' where item_code=#{item_code}")
	public void updateDisAgree(String item_code);
	
	/**
	 * 更新订阅进展
	 */
	@Update("update user_order set progess=1 where item_code=#{itemCode}")
	public void updateDingYue(String itemCode);
}
