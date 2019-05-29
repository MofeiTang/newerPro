package com.newer.supervision.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newer.supervision.domain.User;
import com.newer.supervision.domain.UserOrder;
import com.newer.supervision.service.MsgService;

@RestController
@RequestMapping("/mc")
public class MsgController {
	
	@Autowired
	private MsgService msgService; 
	
	
	@GetMapping("/Msgs")
	public ResponseEntity<?> queryMsg(HttpSession session){
		//查询消息
		User user=(User)session.getAttribute("users");
		List<UserOrder> msgs=msgService.queryNewMsg(user.getUserid());
		//返回消息
		return new ResponseEntity<List<UserOrder>>(msgs,HttpStatus.OK);
	}
	
	@PutMapping("/delMsg")
	public ResponseEntity<?> delMsg(String itemCode,HttpSession session){
		User user=(User)session.getAttribute("users");
		session.setAttribute("show", itemCode);	
		msgService.deleteMsg(itemCode, user.getUserid());
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
	
	@PutMapping("/delDingYue")
	public ResponseEntity<?> delDingYue(String itemCode,HttpSession session){
		User user=(User)session.getAttribute("users");
		msgService.deleteDingYue(itemCode, user.getUserid());
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
	
}
