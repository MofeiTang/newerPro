package com.newer.supervision.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newer.supervision.domain.Dict;
import com.newer.supervision.domain.Organication;
import com.newer.supervision.domain.Page;
import com.newer.supervision.service.OrganicattonService;

@CrossOrigin(allowedHeaders="*",allowCredentials="true", origins="*",maxAge=3600) 
@RestController
@RequestMapping(value = "/api")
public class OrganicationController {

	@Autowired
	private OrganicattonService ORG;
	
	  @RequestMapping(value = "/addorg", method = RequestMethod.POST)
	  public ResponseEntity<?> addOrganicatton(@RequestBody Organication org) {
		  System.out.println("123");
		 int i= ORG.addOrganicatton(org);
		  if (i==0) {
				System.out.println("128");
				return new ResponseEntity<>("注册失败", HttpStatus.CONFLICT);
			}else {
				System.out.println("129");
				return new ResponseEntity<Organication>(org, HttpStatus.CREATED);
			}			
		}
	  @RequestMapping(value = "/updateorg", method = RequestMethod.POST)
	  public ResponseEntity<?> updateOrganicatton(@RequestBody Organication org) {
		  System.out.println("124");
		 int i=ORG.update(org);
		  if (i==0) {
				System.out.println("128");
				return new ResponseEntity<>("更新失败", HttpStatus.CONFLICT);
			}else {
				System.out.println("129");
				return new ResponseEntity<Organication>(org, HttpStatus.OK);
			}			
		} 
	  @RequestMapping(value = "/removeorg", method = RequestMethod.POST)
	  public ResponseEntity<?> removeOrganicatton(@RequestParam("orgId")  Long orgid) {
		  System.out.println("125");
		 int i=ORG.remove(orgid);
		  if (i==0) {
			  System.out.println("删除失败");
				return new ResponseEntity<>("{\"fasle\":\"删除失败\"}", HttpStatus.CONFLICT) ;
			}else {
				System.out.println("删除成功");
				return new ResponseEntity<String>("{\"ok\":\"删除成功\"}", HttpStatus.OK);
			}				
		} 
	  @RequestMapping(value = "/findorg", method = RequestMethod.GET)
	  public ResponseEntity<Page> findOrganication() {
		  System.out.println("查询");
		  	Page p=getPage(1);
		  if (p.getData()==null || p.getData().isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<Page>(p, HttpStatus.OK);
			}
}
	  
	  @RequestMapping(value = "/nextorg", method = RequestMethod.GET)
	  public ResponseEntity<Page> nextOrganication(@RequestParam("currentPage")  int currentPage,@RequestParam("state")  int state) {
		  System.out.println("查询");
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
	  //封装Page对象
	  public Page getPage(int currentPage ) {
		  Page p=new Page();
		  p.setCurrentPage(currentPage);
		  p.setPageSize(2);
		  p.setStartLine((p.getCurrentPage()-1)*p.getPageSize());
		 List<Organication> orgs=ORG.find(p);
		 p.setData(orgs);
		 int rownum=ORG.findcount();
		 if(rownum%p.getPageSize()==0){
			 p.setPageNum(rownum/p.getPageSize());
			}else if(rownum%p.getPageSize()!=0)
			{
				p.setPageNum(rownum/p.getPageSize()+1);
			}
		 p.setRownum(rownum);
		 return p;
	  }
	  @RequestMapping(value = "/findorgid", method = RequestMethod.GET)
	  public ResponseEntity<Organication> findorgid(@RequestParam("orgId")  Long orgId) {
		  System.out.println("查询id"+orgId);
		  Organication org=ORG.findorgid(orgId);
		  if (org==null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<Organication>(org, HttpStatus.OK);
			}
}
	  
	  @RequestMapping(value = "/queryorg", method = RequestMethod.GET)
	  public ResponseEntity<List<Organication>> queryOrganication() {
		  System.out.println("查询org");
		  List<Organication> orgs=ORG.query();
		  if (orgs==null || orgs.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<List<Organication>>(orgs, HttpStatus.OK);
			}
}
	  
	  /**
		 * 获得部门路径
		 * @return
		 */
	  	@GetMapping(value="getpath")
		public ResponseEntity<?> getParentPath(){
	  		List<Organication> dlist=ORG.getParentPath();
			return new ResponseEntity(dlist,HttpStatus.OK);
		}
	  
}
