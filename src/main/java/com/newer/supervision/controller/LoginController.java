package com.newer.supervision.controller;

//import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newer.supervision.domain.User;
import com.newer.supervision.service.LoginService;
import com.newer.supervision.util.MD5;

@RestController
@RequestMapping("/uc")
public class LoginController {
	@Autowired
	private LoginService userservice;
	private MD5 m=MD5.getInstance();
	private Log log=LogFactory.getLog(getClass());
	
	/**
	 * 用户登录
	 * @param UserName：用户名
	 * @param uPassWord：用户密码
	 * @param yzm：用户的图片验证码
	 * @param session
	 * @return
	 */
	@GetMapping("/login")
	public boolean login(@Param("UserName")String UserName,@Param("uPassWord")String uPassWord,@Param("yzm")String yzm,HttpSession session) {
		User user=null;
		String imgcode=(String)session.getAttribute("imgcode");
		if(yzm!=null&&imgcode!=null) {
			String code1=yzm.toLowerCase();
			String code2=imgcode.toLowerCase();
			String Mpwd=m.getMD5ofStr(uPassWord);
			user=userservice.Login(UserName,Mpwd);
			if(code1.equals(code2)&&user!=null) {
			session.setAttribute("users", user);
			System.out.println("user:"+user.getRealname());
			return true;
			}	
		}
		
		return false;	
	}
	
	/**
	 * 
	 * @param session
	 * @return获得用户的职位
	 */
	@GetMapping("/getloginuser")
	public ResponseEntity<?>get(HttpSession session){
		User user=(User) session.getAttribute("users");
		System.out.println(user.getRealname()+user.getJobtype());
		return new ResponseEntity<User>(user==null?null:user,HttpStatus.OK);
	}
	
	/**
	 * 用户退出
	 * @param session
	 * @return
	 */
	@GetMapping("/exitloginuser")
	public String exitloginuser(HttpSession session) {
		session.removeAttribute("users");
		session.invalidate();
		System.out.println("用户登出方法调用");
		return "用户登出";
	}
	
}
