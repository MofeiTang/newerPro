package com.newer.supervision.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock.Catch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.mysql.fabric.xmlrpc.base.Data;
import com.newer.supervision.domain.ItemProcess;
import com.newer.supervision.domain.ItemRepository;
import com.newer.supervision.domain.User;
import com.newer.supervision.service.BYKService;
import com.newer.supervision.service.ItemProcessService;

@RestController
@RequestMapping("/ip")
public class ItemProcessController {
	
	@Autowired
	private ItemProcessService itemProcessService;
	@Autowired
	private  BYKService bykSvr;
	
	/**
	 * 查询负责人待办
	 * @param session
	 * @return
	 */
	@GetMapping("/daiban")
	public ResponseEntity<?> queryDaiBan(HttpSession session){
		//查询到当前负责人的待办事项，若存在则返回队列，若不存在则返回空
		User user=(User)session.getAttribute("users");
	
		List<ItemRepository> itemRepositories=itemProcessService.queryDaiBan(user.getUserid());
		System.out.println("size--"+itemRepositories.size());
		if(itemRepositories.size()!=0) { 
			return new ResponseEntity<List<ItemRepository>>(itemRepositories,HttpStatus.OK);
		}else {
			return null;
		}
	}
	
	/**
	 * 更新事项进展
	 * @param itemProcess
	 * @param session
	 * @return
	 */
	@PostMapping("/updateItem")
	public boolean updateItem(ItemProcess itemProcess,HttpSession session){
		
		//为事项更新进展进行赋值
		User user=(User)session.getAttribute("users");
		itemProcess.setUser_Id(user);
		Date date=new Date();
		itemProcess.setOpt_Time(date);
		itemProcess.setBj("2");
		//查询进展是否存在
		ItemProcess itemProcess2=itemProcessService.queryProcess(itemProcess.getItem_Code().getItem_Code());
		try {
			if(itemProcess2!=null) {
				//对已存在的事项进行修改
				itemProcessService.updItem(itemProcess);
			}else {
				//进行事项进展更新
				itemProcessService.updateItem(itemProcess);
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		//修改待办任务
		itemProcessService.updateDaiBan(itemProcess.getItem_Code().getItem_Code());
		return true;
	}
	
	/**
	 * 督办员同意事项进展
	 */
	@PutMapping("/agree/{status}")
	public ResponseEntity<?> updateProcess(@PathVariable("status") String status,ItemProcess itemProcess){
		if(status.equals("已完成")) {
			System.out.println("已完成");
			bykSvr.updItemStatus(itemProcess.getItem_Code().getItem_Code(), "10");
		}else if(status.equals("已中止")) {
			System.out.println("已中止");
			bykSvr.updItemStatus(itemProcess.getItem_Code().getItem_Code(), "11");
		}else {
		itemProcessService.updateAgree(itemProcess);
		}
		itemProcessService.insertMerge(itemProcess.getItem_Code().getItem_Code());
		//给订阅者发消息代表信息修改
		itemProcessService.msgProcess(itemProcess.getItem_Code().getItem_Code());
		//先删除再修改 并且顺序不能改变
		itemProcessService.deleteProcess(itemProcess.getItem_Code().getItem_Code());
		itemProcessService.updateProcess(itemProcess.getDb_Desc(), itemProcess.getItem_Code().getItem_Code());
		return new ResponseEntity<String>("审批成功",HttpStatus.OK);
	}
	
	/**
	 * 督办员不同意事项
	 */
	@PutMapping("/disagree")
	public ResponseEntity<?> updateDisProcess(ItemProcess itemProcess){
		//不同意就仅仅只增加审批意见
		itemProcessService.updateDisProcess(itemProcess.getDb_Desc(), itemProcess.getItem_Code().getItem_Code());
		itemProcessService.updateDisAgree(itemProcess.getItem_Code().getItem_Code());
		return new ResponseEntity<String>("事项进展已退回",HttpStatus.OK);
	}
		
	/**
	 * 事项进展被退回则返回退回信息
	 * @param itemId
	 * @param session
	 * @return
	 */
	@GetMapping("/backInit")
	public ResponseEntity<?> InitBack(String itemId,HttpSession session){
		//通过userId和事项编号来查询进展数据
		ItemProcess itemProcess=itemProcessService.queryProcess(itemId);
		//返回进展数据
		return new ResponseEntity<ItemProcess>(itemProcess,HttpStatus.OK);
	}
	
	
	
	
}
