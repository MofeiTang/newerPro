package com.newer.supervision.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.newer.supervision.domain.Dict;
import com.newer.supervision.domain.User;
import com.newer.supervision.service.DictService;

@CrossOrigin(allowedHeaders="*",allowCredentials="true", origins="*",maxAge=3600) 
@RestController
@RequestMapping(value = "/api")
public class DictController {
	@Autowired
	private DictService DT;
	  @RequestMapping(value = "/findedu",method = RequestMethod.GET)
	  public ResponseEntity<List<Dict>> findEdu() {
		 System.out.println("查询学历");
		 List<Dict> dicts=DT.findEdu();
		 for(Dict d:dicts) {
			 System.out.println(d.getType_ID()+d.getType_Name());
		 }
		  if (dicts==null || dicts.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				 System.out.println("1");
				return new ResponseEntity<List<Dict>>(dicts,HttpStatus.OK);
			}
}

	  @RequestMapping(value = "/findjob", method = RequestMethod.GET)
	  public ResponseEntity<List<Dict>> findJob() {
		  System.out.println("查询职业");
		 List<Dict> dicts=DT.findJob();
		  if (dicts==null || dicts.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				 System.out.println("3");
				return new ResponseEntity<List<Dict>>(dicts, HttpStatus.OK);
			}
}
	  @RequestMapping(value = "/findjobtype", method = RequestMethod.GET)
	  public ResponseEntity<List<Dict>> findJobtype() {
		  System.out.println("查询职务");
		 List<Dict> dicts=DT.findJobtype();
		  if (dicts==null || dicts.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				 System.out.println("4");
				return new ResponseEntity<List<Dict>>(dicts, HttpStatus.OK);
			}
}
	  
	  
}
