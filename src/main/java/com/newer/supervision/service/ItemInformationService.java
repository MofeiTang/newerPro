package com.newer.supervision.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newer.supervision.domain.ItemInformation;
import com.newer.supervision.mapper.IItemInformationMapper;

@Service
public class ItemInformationService {

	@Autowired
	private IItemInformationMapper iItemInformationMapper;
	
	/**
	 * 查找当前事项信息
	 * @param itemCode
	 * @return
	 */
	public ItemInformation queryInformation(String itemCode) {
		return iItemInformationMapper.queryInformation(itemCode); 
	}
}
