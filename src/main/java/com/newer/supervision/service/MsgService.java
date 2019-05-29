package com.newer.supervision.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newer.supervision.domain.UserOrder;
import com.newer.supervision.mapper.IMsgMapper;

@Service
public class MsgService {
	@Autowired
	private IMsgMapper iMsgMapper;
    
	/**
	 * 返回当前消息
	 * @param userId
	 * @return
	 */
	public List<UserOrder> queryNewMsg(Long userId){
		return iMsgMapper.queryNewMsg(userId);
	}
	
	/**
	 * 删除消息
	 * @param itemCode
	 * @param userId
	 */
	public void deleteMsg(String itemCode,Long userId) {
		iMsgMapper.deleteMsg(itemCode, userId);
	}
	
	/**
	 * 如若事项已合并，则删除订阅消息
	 * @param itemCode
	 * @param userId
	 */
	public void deleteDingYue(String itemCode,Long userId) {
		iMsgMapper.deleteDingYue(itemCode, userId);
	}
	

}
