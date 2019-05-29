package com.newer.supervision.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newer.supervision.domain.ItemProcess;
import com.newer.supervision.domain.Parameter;
import com.newer.supervision.domain.User;
import com.newer.supervision.service.ItemListService;
import com.newer.supervision.service.LoginService;
import com.newer.supervision.service.UserService;



@CrossOrigin(allowedHeaders="*",allowCredentials="true", origins="*",maxAge=3600) 
@RestController
@RequestMapping(value = "/progess")
public class ItemListController {
	@Autowired
	private ItemListService itser;
	
	/**
	 * 获得事项进展
	 * @return
	 */
	@GetMapping("evolve")
	public ResponseEntity<?> getItemProgess(HttpSession session){
		User u=(User) session.getAttribute("users");
		List<ItemProcess> plist=itser.getItemProgess(u.getUserid());
		if(plist!=null) {
			return new ResponseEntity<List<ItemProcess>>(plist, HttpStatus.OK);
		}
		return new ResponseEntity<List<ItemProcess>>(plist, HttpStatus.OK);
	}
	  
	/**
	 * 订阅
	 * @param order
	 * @return
	 */
	@PostMapping(value="take")
	public String takeItem(@RequestParam("code") String code,HttpSession session) {
		User u=(User) session.getAttribute("users");
		if(u!=null) {
		Integer i=itser.takeItem(code,u.getUserid());
		return "成功";
		}
		return "";
	}
	
	/**
	 * 模糊查询
	 * @param id
	 * @return
	 */
	@PostMapping(value="likeCheck")
	public ResponseEntity<List<ItemProcess>> likeCheck(@RequestBody Parameter par,HttpSession session){
		System.out.println("parameter---"+par.getItem_code());
		User u=(User) session.getAttribute("users");
		if(u.getUserid()!=null) {
			par.setUserid(u.getUserid());
		}
		if(par.getItem_code()!=null&&par.getItem_code()!="") {
			par.setItem_code("%"+par.getItem_code()+"%");
			System.out.println("item_code");
		}
		if(par.getItem_name()!=null&&par.getItem_name()!="") {
			par.setItem_name("%"+par.getItem_name()+"%");
			System.out.println("item_name");
		}
		if(par.getOrgname()!=null&&par.getOrgname()!=""){
			par.setOrgname("%"+par.getOrgname()+"%");
			System.out.println("orgname");
		}
		List<ItemProcess> plist=itser.likeCheck(par);
//		System.out.println("plist"+plist.get(0).getUser_Id());
		return new ResponseEntity<List<ItemProcess>>(plist, HttpStatus.OK);
	}
	
	/**
	 * 统计推进中
	 * @param id
	 * @return
	 */
	@GetMapping(value="process")
	public ResponseEntity<List<ItemProcess>> getPropelling(HttpSession session){
		User u=(User) session.getAttribute("users");
		List<ItemProcess> plist=itser.getPropelling(u.getUserid());
		return new ResponseEntity<List<ItemProcess>>(plist, HttpStatus.OK);
	}
	
	/**
	 * 统计累计完成
	 * @param id
	 * @return
	 */
	@GetMapping(value="complete")
	public ResponseEntity<List<ItemProcess>> getComplete(HttpSession session){
		User u=(User) session.getAttribute("users");
		List<ItemProcess> plist=itser.getComplete(u.getUserid());
		return new ResponseEntity<List<ItemProcess>>(plist, HttpStatus.OK);
	}
	
	/**
	 * 我的事项
	 * @param id
	 * @return
	 */
	@GetMapping(value="myitem")
	public ResponseEntity<List<ItemProcess>> getMyitem(HttpSession session){
		User u=(User) session.getAttribute("users");
		List<ItemProcess> plist=itser.getMyitem(u.getUserid());
		return new ResponseEntity<List<ItemProcess>>(plist, HttpStatus.OK);
	}
	
	/**
	 * 本月完成
	 * @param id
	 * @return
	 */
	@GetMapping(value="monthcomplete")
	public ResponseEntity<List<ItemProcess>> getmonthcomplete(HttpSession session){
		User u=(User) session.getAttribute("users");
		List<ItemProcess> plist=itser.getmonthcomplete(u.getUserid());
		return new ResponseEntity<List<ItemProcess>>(plist, HttpStatus.OK);
	}
	
	/**
	 * 本月更新
	 * @param id
	 * @return
	 */
	@GetMapping(value="monthupdate")
	public ResponseEntity<List<ItemProcess>> getmonthupdate(HttpSession session){
		User u=(User) session.getAttribute("users");
		List<ItemProcess> plist=itser.getmonthupdate(u.getUserid());
		return new ResponseEntity<List<ItemProcess>>(plist, HttpStatus.OK);
	}
	
	/**
	 * 统计待办事项超时
	 * @param id
	 * @return
	 */
	@GetMapping(value="overtime")
	public ResponseEntity<List<ItemProcess>> getOvertime(HttpSession session){
		User u=(User) session.getAttribute("users");
		List<ItemProcess> plist=itser.getOvertime(u.getUserid());
		return new ResponseEntity<List<ItemProcess>>(plist, HttpStatus.OK);
	}
	
	/**
	 * 统计延期
	 * @param id
	 * @return
	 */
	@GetMapping(value="currentdelay")
	public ResponseEntity<List<ItemProcess>> getDelay(HttpSession session){
		User u=(User) session.getAttribute("users");
		List<ItemProcess> plist=itser.getDelay(u.getUserid());
		return new ResponseEntity<List<ItemProcess>>(plist, HttpStatus.OK);
	}
	
}
