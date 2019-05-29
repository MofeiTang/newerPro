package com.newer.supervision.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newer.supervision.domain.ItemInformation;
import com.newer.supervision.service.ItemInformationService;

@RestController
@RequestMapping("/iic")
public class ItemInformationController {
     
	@Autowired
	private ItemInformationService itemInformationService;
	
	/**
	 * 获得事项的基本信息
	 * @param itemCode
	 * @return
	 */
	@GetMapping("/information")
	public ResponseEntity<?> queryInformation(String itemCode){
		ItemInformation itemInformation=itemInformationService.queryInformation(itemCode);
		return new ResponseEntity<ItemInformation>(itemInformation,HttpStatus.OK);
	}
	
	
}
