package com.newer.supervision.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.supervision.domain.ItemRepository;

public interface IBacklogMapper {
	
	/**
	 * 获得新增事项
	 * @return
	 */
	@Select("SELECT * FROM item_repository ir WHERE ir.item_status='1'")
	public List<ItemRepository> getNewItem();
	
	/**
	 * 获取事项
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM item_repository ir WHERE ir.`ITEM_CODE`=#{id}")
	public ItemRepository getItem(@Param("id") String id);
	
	/**
	 * 设置领导批示
	 * @return
	 */
	@Update("UPDATE item_repository ir SET ir.`LEADER_IDEA`=#{content},ir.`OVER_TIME`=#{time} WHERE ir.`ITEM_CODE`=#{itemCode}")
	public Integer leaderPishi(@Param("itemCode") String code,@Param("content") String content,@Param("time") Date time);
	
	/**
	 * 添加事项与用户的关系
	 * @param itemCode
	 * @param userid
	 * @return
	 */
	@Insert("INSERT INTO item_leader VALUES(#{code},#{userid})")
	public Integer itemLeader(@Param("code") String itemCode,@Param("userid") Long userid);
	
	
	/**
	 * 设置退回时领导批示
	 * @return
	 */
	@Update("UPDATE item_repository ir SET ir.`LEADER_IDEA`=#{content} WHERE ir.`ITEM_CODE`=#{itemCode}")
	public Integer leaderPishiBack(@Param("itemCode") String code,@Param("content") String content);
	
	/**
	 * 领导修改事项状态
	 * @param itemCode
	 * @return
	 */
	@Update("UPDATE item_repository ir SET ir.`ITEM_STATUS`=#{Status} WHERE ir.`ITEM_CODE`=#{itemCode}")
	public Integer updItemStatus(@Param("itemCode") String itemCode,@Param("Status") String itemStatus);
}
