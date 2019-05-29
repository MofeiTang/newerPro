package com.newer.supervision.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newer.supervision.domain.Page;
import com.newer.supervision.domain.User;
import com.newer.supervision.mapper.IUserMapper;

@Service
public class UserService {
	
	@Autowired
	private IUserMapper IU;
	
	public int addUser(User user) {
		return IU.addUser(user);
	}
	public int remove(Long userid) {
		return IU.remove(userid);
		
	}
	public List<User> find(Page p){
		return IU.find(p);
	}
	public int findcount() {
		return IU.findcount();
}
	public List<User> findld(){
		return IU.findld();
	}
	
	public User findid(Long userid){
		return IU.findid(userid);
	}
		
	public int update(User user) {
		return IU.update(user);
	} 
	
}
