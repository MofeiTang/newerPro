package com.newer.supervision.controller;


import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newer.supervision.domain.Organication;
import com.newer.supervision.domain.Page;
import com.newer.supervision.domain.User;
import com.newer.supervision.service.UserService;



@CrossOrigin(allowedHeaders="*",allowCredentials="true", origins="*",maxAge=3600) 
@RestController
@RequestMapping(value = "/api")
public class UserController {
	@Autowired
	private UserService US;
	
	  @RequestMapping(value = "/adduser", method = RequestMethod.POST)
	  public ResponseEntity<?> addUser(@RequestBody User user) {
		 int i= US.addUser(user);
		  if (i==0) {
				return new ResponseEntity<>("注册失败", HttpStatus.CONFLICT);
			}else {
				return new ResponseEntity<User>(user, HttpStatus.CREATED);
			}			
		}
	  @RequestMapping(value = "/update", method = RequestMethod.POST)
	  public ResponseEntity<?> updateUser(@RequestBody User user) {
		 int i=US.update(user);
		  if (i==0) {
				return new ResponseEntity<>("更新失败", HttpStatus.CONFLICT);
			}else {
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}			
		} 
	  @RequestMapping(value = "/remove", method = RequestMethod.POST)
	  public ResponseEntity<?> removeUser(@RequestParam("userid")  Long userid) {
		  System.out.println("125");
		 int i=US.remove(userid);
		  if (i==0) {
			  System.out.println("删除失败");
				return new ResponseEntity<>("{\"fasle\":\"删除失败\"}", HttpStatus.CONFLICT) ;
			}else {
				System.out.println("删除成功");
				return new ResponseEntity<String>("{\"ok\":\"删除成功\"}", HttpStatus.OK);
			}			
		} 
	  @RequestMapping(value = "/find", method = RequestMethod.GET)
	  public ResponseEntity<Page> find() {
		  System.out.println("查询");
		  Page p=getPage(1);

		  if (p.getData()==null || p.getData().isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<Page>(p, HttpStatus.OK);
			}
}
	  
	  @RequestMapping(value = "/nextuser", method=RequestMethod.GET)
	  public ResponseEntity<Page> nextUser(@RequestParam("currentPage")  int currentPage,@RequestParam("state")  int state) {
		  System.out.println("查询"+currentPage+" "+state);
		  Page p=new Page();
		  if(state==0) {
			  p=getPage(currentPage-1);
		  }else if(state==1) {
			  p=getPage(currentPage+1);
		  }else if(state==2) {
			  p=getPage(currentPage);
		  }
		  if (p.getData()==null || p.getData().isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<Page>(p, HttpStatus.OK);
			}
}
	  @RequestMapping(value = "/findld", method = RequestMethod.GET)
	  public ResponseEntity<Page> findld() {
		  System.out.println("查询");
		 List<User> users=US.findld();
		 Page p=getPage(1);
		 p.setData(users);
		  if (p.getData()==null || p.getData().isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<Page>(p, HttpStatus.OK);
			}
}
	  @RequestMapping(value = "/findid", method = RequestMethod.GET)
	  public ResponseEntity<User> findid(@RequestParam("userid")  Long userid) {
		  System.out.println("查询id"+userid);
		 User users=US.findid(userid);
		  if (users==null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<User>(users, HttpStatus.OK);
			}
}  
	  //封装Page对象
	  public Page getPage(int currentPage ) {
		  Page p=new Page();
		  p.setCurrentPage(currentPage);
		  p.setPageSize(4);
		  p.setStartLine((p.getCurrentPage()-1)*p.getPageSize());
		 List<User> users=US.find(p);
		 p.setData(users);
		 int rownum=US.findcount();
		 if(rownum%p.getPageSize()==0){
			 p.setPageNum(rownum/p.getPageSize());
			}else if(rownum%p.getPageSize()!=0)
			{
				p.setPageNum(rownum/p.getPageSize()+1);
			}
		 p.setRownum(rownum);
		 return p;
	  }
}
