package com.newer.supervision.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newer.supervision.domain.ItemLeader;
import com.newer.supervision.domain.ItemOrg;
import com.newer.supervision.domain.ItemProcess;
import com.newer.supervision.domain.User;
import com.newer.supervision.domain.UserOrder;
import com.newer.supervision.service.IndexService;
/**
 * 控制器类
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/sy")
public class IndexController {
	@Autowired
	private IndexService indexsService;
	/**
	 * 最近更新
	 * @param session
	 * @return
	 */
	@GetMapping("/gxsele")
	public ResponseEntity<List<ItemProcess>>findxin(){
		List<ItemProcess> xin = indexsService.findxin();
		if (xin==null||xin.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ItemProcess>>(xin,HttpStatus.OK);
	}
	
	/**
	 * 订阅模糊查询
	 * @param item_Name
	 * @return
	 */
	@GetMapping(value="likedingyue")
	public ResponseEntity<List<ItemOrg>> likedingyue(@RequestParam(required=false,name="item_Name") String item_Name,HttpSession session){
		User u=(User) session.getAttribute("users");
		if(item_Name!=null) {
			item_Name="%"+item_Name+"%"	;
		}
		List<ItemOrg> lItemOrgs = indexsService.likedingyue(item_Name,u.getUserid());
		if(lItemOrgs.size()>0) {
			return new ResponseEntity<List<ItemOrg>>(lItemOrgs, HttpStatus.OK);
		}else{
			return new ResponseEntity<List<ItemOrg>>(lItemOrgs, HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * 推进中
	 * @return
	 */
	@GetMapping("/linsele")
	public ResponseEntity<List<ItemLeader>> findtuijin() {
		List<ItemLeader> tuijin = indexsService.findtuijin();
		System.out.println("www"+tuijin.get(0).getUser_Id().getRealname());
		if (tuijin==null||tuijin.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ItemLeader>>(tuijin,HttpStatus.OK);
	}
	/**
	 * 订阅
	 * @return
	 */
	@GetMapping("/dysele")
	public ResponseEntity<List<UserOrder>> finddingyue(HttpSession session) {
		User u=(User) session.getAttribute("users");
		List<UserOrder> dingyue = indexsService.finddingyue(u.getUserid());
		System.out.println("dingyue"+dingyue);
		if (dingyue==null||dingyue.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserOrder>>(dingyue, HttpStatus.OK);
	}
	
	 @GetMapping("/show")
		public ResponseEntity<?> getCode(HttpSession session){
			String msg=(String)session.getAttribute("show");
			session.removeAttribute("show");
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		}

	
	/**
	 * 取消订阅
	 * @param code
	 * @return
	 */
	@GetMapping(value="delorder")
	public boolean delorder(@RequestParam(name="item_code") String code,HttpSession session) {
		System.out.println("111code----"+code);
		User u=(User) session.getAttribute("users"); 
		Integer iss=indexsService.delorder(code,u.getUserid());
		if(iss!=null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 统计
	 * @return
	 */
	@GetMapping("/tongji")
	public ResponseEntity<HashMap<String, Long> >tongji(HttpSession session){
		User user=(User) session.getAttribute("users");
		HashMap<String, Long> hm=null;
		Long byxz;
		if(user!=null) {
			Long userid=user.getUserid();
			hm=new HashMap<String, Long>();
			Long wc=indexsService.tongjiWC(userid);
			Long tjz=indexsService.tongjiTJZ(userid);
			if(user.getUserid()!=2&&user.getUserid()!=1) {
				byxz=indexsService.tongjiBYXZ(userid);
			}else {
				byxz=indexsService.dubanItem();
			}
			Long bywc=indexsService.tongjiBYWC(userid);
			Long bygx=indexsService.tongjiBYGX(userid);
			Long yq=indexsService.tongjiYQ(userid);
			Long cs=indexsService.tongjiCS(userid);
			hm.put("wc", wc);
			hm.put("tjz", tjz);
			hm.put("byxz", byxz);
			hm.put("bywc", bywc);
			hm.put("bygx", bygx);
			hm.put("yq", yq);
			hm.put("cs", cs);
		}
		return new ResponseEntity<HashMap<String,Long>>(hm,HttpStatus.OK);	
	}
	/**
	 * 上次登录时间
	 * @return
	 */
	@GetMapping("/logintime")
	public String findloginTime(HttpSession session){
		User u=(User) session.getAttribute("users");
		String logintime = indexsService.loginTime(u.getUserid());
		indexsService.setLoginTime(u.getUserid());
		System.out.println("ddd"+logintime);
		return logintime;
	}
	
	
}
