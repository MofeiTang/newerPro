package com.newer.supervision.controller;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newer.supervision.domain.ItemRepository;
import com.newer.supervision.domain.User;
import com.newer.supervision.service.BacklogService;

@RestController
@RequestMapping("/backlog")
public class BacklogController {
	
	@Autowired
	private BacklogService blser;
	
	
	/**
	 * 获得登入用户
	 * @return
	 */
	@GetMapping(value="getloginuser")
	public ResponseEntity<User> getLoginUser(HttpSession session) {
		User us=(User) session.getAttribute("users");
		if(us!=null) {
			return new ResponseEntity<User>(us,HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}	
	
	/**
	 * 获得新增事项
	 * @return
	 */
	@GetMapping(value="items")
	public ResponseEntity<?> getNewItem(){
		List<ItemRepository> ilist=blser.getNewItem();
		return new ResponseEntity<List<ItemRepository>>(ilist,HttpStatus.OK); 
	}
	
	/**
	 * 获取事项
	 * @param id
	 * @return
	 */
	@GetMapping(value="item")
	public ResponseEntity<ItemRepository> getItem(String id) {
		ItemRepository repository=null;
		if(id!=null) {
			repository=blser.getItem(id);
		}
		return new ResponseEntity<ItemRepository>(repository,HttpStatus.OK);
	}
	
	/**
	 * 设置领导批示
	 * @return
	 */
	@PutMapping(value="pishi")
	public String leaderPishi(@RequestParam("code") String code,@RequestParam("content") String content,@RequestParam("time") String time,HttpSession session) {
		User u=(User) session.getAttribute("users");
		if(code!=null&&content!=null&&time!=null){
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			Date date=null;
			try {
				date = sf.parse(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Integer iss=blser.leaderPishi(code, content, new java.sql.Date(date.getTime()));
			Integer iss1=blser.updItemStatus(code,"4");
			Integer iss2=blser.itemLeader(code,u.getUserid());
			if(iss!=null&&iss1!=null&&iss2!=null) {
				return "添加成功";
			}
		}
		System.out.println("失败");
		return "添加失败";
	}
	
	/**
	 * 领导退回事项
	 * @param itemCode
	 * @return
	 */
	@PutMapping(value="reject")
	public String updItemStatus(@RequestParam("itemCode") String itemCode,@RequestParam("content") String content) {
		if(itemCode!=null) {
			blser.leaderPishiBack(itemCode, content);
			Integer iss=blser.updItemStatus(itemCode,"3");
			if(iss!=null) {
				return "事项已退回";
			}
		}
		return "事项退回失败";
	}
}
