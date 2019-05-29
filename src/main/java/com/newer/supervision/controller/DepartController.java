package com.newer.supervision.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newer.supervision.domain.ItemRepository;
import com.newer.supervision.domain.Result;
import com.newer.supervision.domain.User;
import com.newer.supervision.service.DepartService;

@RestController
@RequestMapping("/dc")
public class DepartController {
	@Autowired
	private DepartService service;
	
	
	/**
	 * 部门指定事项负责人时:显示事项名称、督办来源、来源时间、牵头部门、协办部门、督办事项内容（不可输入）。
	 * @return
	 */
	@GetMapping("queryDept")
	public ResponseEntity<?> queryDept(@Param("code")Integer code) {
		Result list=service.queryDept(code);
		System.out.println(code+"出现");
		return new ResponseEntity<Result>(list,HttpStatus.OK);
	}
	
	/**
	 * 查询部门的代办任务，暂不知道该方法能否通用
	 */
	@GetMapping("queryDB")
	public ResponseEntity<?> queryDeptDB(HttpSession session) {
		User u=(User) session.getAttribute("users");
		Long orgid=u.getOrgid().getOrgId();
		List<ItemRepository> list=service.queryDepartDaiBan(orgid);
		System.out.println("查询代办"+list.size());
		return new ResponseEntity<List<ItemRepository>>(list,HttpStatus.OK);
	}
	
	/**
	 * 根据item_code查询ItemRepository
	 * @param code
	 * @return
	 */
	@GetMapping("queryResposity")
	public ResponseEntity<?>queryResposity(@Param("code")Integer code){
		ItemRepository respository=service.queryItemRespository(code);
		System.out.println("queryResposity");
		return new ResponseEntity<ItemRepository>(respository,HttpStatus.OK);
	}
	/**
	 * 根据userid查询ItemRepository
	 * @param code
	 * @return
	 */
	@GetMapping("queryResposityById")
	public ResponseEntity<?>queryResposityByUserId(HttpSession session){
		User user=(User) session.getAttribute("users");
		ItemRepository respository=null;
		if(user!=null) {
			respository=service.queryItemRespositroyByUserID(user.getUserid(),"101");
			System.out.println("queryResposityById");
		}
		return new ResponseEntity<ItemRepository>(respository,HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * @param session
	 * @return
	 */
	@GetMapping("queryDeptUser")
	public ResponseEntity<?>queryDeptUser(HttpSession session){
		User user=(User) session.getAttribute("users");
		List<User>list=service.queryDeptUser(user==null?null:user.getUserid());
		System.out.println("queryDeptUser");
		return new ResponseEntity<List<User>>(list,HttpStatus.OK);	
	}
	
	@PostMapping("updateAndInser")
	public String updateAndInser(@Param("fzr")String fzr,@Param("code")String code) {
//		Date time=null;
		System.out.println(fzr+code);
		User user=service.queryUserIdByName(fzr);
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			time=sdf.parse(formaTime);
////			System.out.println(time);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		//修改
		System.out.println(code+"id："+user.getUserid());
		service.updateUserId(user==null?null:user.getUserid(),code,user.getOrgid().getOrgId());
//		int count=service.getOrgNum(code);
//		if(count==2) {
			service.updateStatus(code);
//		}
		//更新过期时间和状态
//		service.updateOverTime(time);
		return "congratiation";
	}
	
}
