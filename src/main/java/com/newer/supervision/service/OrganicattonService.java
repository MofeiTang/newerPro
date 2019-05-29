package com.newer.supervision.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newer.supervision.domain.Dict;
import com.newer.supervision.domain.Organication;
import com.newer.supervision.domain.Page;
import com.newer.supervision.mapper.IOrganicattonMapper;



@Service
public class OrganicattonService {
	@Autowired
	private IOrganicattonMapper IO;
	
	public int addOrganicatton(Organication org) {
		return IO.addOrganicatton(org);
	}
	public int remove(Long orgid) {
		return IO.remove(orgid);
		
	}
	public List<Organication> find(Page p){
		return IO.find(p);
	}
	public Organication findorgid(Long orgid){
		return IO.findorgid(orgid);
	}
	public int findcount() {
			return IO.findcount();
	}
	public int update(Organication org) {
		return IO.update(org);
	} 
	public List<Organication> query(){
		return IO.query();
	}
	/**
	 * 获得部门路径
	 * @return
	 */
	public List<Organication> getParentPath(){
		return IO.getParentPath();
	}
}
