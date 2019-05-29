package com.newer.supervision.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.supervision.domain.UserOrder;

public interface IMsgMapper {
	
	/**
	 * 查询订阅事项进展通知
	 * @param userId
	 * @return
	 */
	@Select("SELECT b.item_code,item_name,progess FROM item_repository a JOIN user_order b ON a.`ITEM_CODE`=b.item_code WHERE user_id=#{userId} and (progess=1 or progess=2)")
	@Results({
		@Result(property="item_Code.item_Code",column="item_code"),
		@Result(property="item_Code.item_Name",column="item_name")
	})
	public List<UserOrder> queryNewMsg(Long userId); 
	
	/**
	 * 删除消息提醒
	 * @param itemCode
	 * @param userId
	 */
	@Update("update user_order set progess=0 where item_code=#{itemCode} and user_id=#{userId}")
	public void deleteMsg(@Param("itemCode") String itemCode,@Param("userId") Long userId);

	/**
	 * 如若订阅事项已合并则删除该事项下的订阅记录
	 */
	@Delete("delete from user_order where item_code=#{itemCode} and user_id=#{userId}")
	public void deleteDingYue(@Param("itemCode") String itemCode,@Param("userId") Long userId);
	
	
}
