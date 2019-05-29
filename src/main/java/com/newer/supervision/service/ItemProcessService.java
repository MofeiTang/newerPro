package com.newer.supervision.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newer.supervision.domain.ItemProcess;
import com.newer.supervision.domain.ItemRepository;
import com.newer.supervision.mapper.IItemProcessMapper;

@Service
public class ItemProcessService {
	@Autowired
    private IItemProcessMapper iItemProcessMapper;

	/**
	 * 获得当前登录人的所有待办事项
	 * @param userId
	 * @return
	 */
	public List<ItemRepository> queryDaiBan(Long userId){
		return iItemProcessMapper.queryDaiBan(userId);
	}
	
	/**
	 * 如果事项不存在则插入一条
	 */
	public String updateItem(ItemProcess itemProcess) {
		iItemProcessMapper.updateItem(itemProcess);
		return itemProcess.getItem_Code().getItem_Code();
	}
	
	public int insertMerge(String code) {
		return iItemProcessMapper.insertMerge(code);
	}
	
	/**
	 * 如果事项存在则修改
	 */
	public String updItem(ItemProcess itemProcess) {
		iItemProcessMapper.updItem(itemProcess);
		return itemProcess.getItem_Code().getItem_Code();
	}
	
	/**
	 *  通过用户Id 和事项编号查询该进展
	 */
	public ItemProcess queryProcess(String itemCode) {
		return iItemProcessMapper.queryProcess(itemCode);
	}
	
	/**
	 * 首先将标记1删除
	 */
	public void deleteProcess(String itemCode) {
		iItemProcessMapper.deleteProcess(itemCode);
	}
	
	/**
	 * 督办员同意通过修改进展
	 */
	public void updateProcess(String db_Desc,String itemCode) {
		iItemProcessMapper.updateProcess(db_Desc, itemCode);
	}
	
	/**
	 * 督办员不同意将意见进行赋值
	 */
	public void updateDisProcess(String db_Desc,String itemCode) {
		iItemProcessMapper.updateDisProcess(db_Desc, itemCode);
	}
	
	/**
	 * 待办任务完成
	 */
	public void updateDaiBan(String item_code) {
		iItemProcessMapper.updateDaiBan(item_code);
	}
	
	/**
	 * 待办任务完成(督办员同意)
	 */
	public void updateAgree(ItemProcess itemProcess) {
		iItemProcessMapper.updateAgree(itemProcess);
	}
	
	/**
	 * 待办任务完成(督办员不同意)
	 */
	public void updateDisAgree(String item_code) {
		iItemProcessMapper.updateDisAgree(item_code);
	}
	
	/**
	 * 修改订阅表中的process提醒订阅者更新了
	 */
	public void msgProcess(String itemCode) {
		iItemProcessMapper.updateDingYue(itemCode);
	}
}
