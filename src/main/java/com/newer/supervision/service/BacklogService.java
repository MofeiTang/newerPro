package com.newer.supervision.service;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newer.supervision.domain.ItemRepository;
import com.newer.supervision.mapper.IBacklogMapper;

@Service
public class BacklogService {
	
	@Autowired
	private IBacklogMapper ibmapper;

	/**
	 * 获得新增事项
	 * @return
	 */
	public List<ItemRepository> getNewItem(){
		return ibmapper.getNewItem();
	}
	
	/**
	 * 获取事项
	 * @param id
	 * @return
	 */
	public ItemRepository getItem(String id) {
		return ibmapper.getItem(id);
	}
	
	/**
	 * 设置领导批示
	 * @return
	 */
	public Integer leaderPishi(String code,String content,Date time) {
		return ibmapper.leaderPishi(code, content, time);
	}
	
	/**
	 * 添加事项与用户的关系
	 * @param itemCode
	 * @param userid
	 * @return
	 */
	@Transactional
	public Integer itemLeader(String itemCode,Long userid) {
		return ibmapper.itemLeader(itemCode, userid);
	}
	
	/**
	 * 领导修改事项状态
	 * @param itemCode
	 * @return
	 */
	public Integer updItemStatus(String itemCode,String itemStatus) {
		return ibmapper.updItemStatus(itemCode,itemStatus);
	}

	/**
	 * 设置退回时领导批示
	 * @return
	 */
	public Integer leaderPishiBack(String code,String content) {
		return ibmapper.leaderPishiBack(code, content);
	}
}
