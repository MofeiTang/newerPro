package com.newer.supervision.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newer.supervision.domain.ItemRepository;
import com.newer.supervision.domain.Result;
import com.newer.supervision.domain.User;
import com.newer.supervision.mapper.IDepartMapper;


@Service
public class DepartService implements IDepartMapper {
	@Autowired
	private IDepartMapper mapper;
	@Override
	public Result queryDept(Integer code) {
		// TODO Auto-generated method stub
		return mapper.queryDept(code);
	}
	@Override
	public List<ItemRepository> queryDepartDaiBan(Long orgid) {
		// TODO Auto-generated method stub
		return mapper.queryDepartDaiBan(orgid);
	}
	@Override
	public Result queryDeptAll() {
		// TODO Auto-generated method stub
		return mapper.queryDeptAll();
	}
	@Override
	public ItemRepository queryItemRespository(Integer code) {
		// TODO Auto-generated method stub
		return mapper.queryItemRespository(code);
	}
	@Override
	public ItemRepository queryItemRespositroyByUserID(Long uid,String item_code) {
		// TODO Auto-generated method stub
		return mapper.queryItemRespositroyByUserID(uid,item_code);
	}
	@Override
	public List<User> queryDeptUser(Long userid) {
		// TODO Auto-generated method stub
		return mapper.queryDeptUser(userid);
	}
	@Override
	public int updateUserId(Long USERID,String item_code,Long id) {
		// TODO Auto-generated method stub
		return mapper.updateUserId(USERID, item_code,id);
	}
	@Override
	public int updateOverTime(Date time) {
		// TODO Auto-generated method stub
		return mapper.updateOverTime(time);
	}
	@Override
	public User queryUserIdByName(String realname) {
		// TODO Auto-generated method stub
		return mapper.queryUserIdByName(realname);
	}
	@Override
	public void updateStatus(String code) {
		// TODO Auto-generated method stub
		 mapper.updateStatus(code);
	}
	@Override
	public int getOrgNum(String code) {
		// TODO Auto-generated method stub
		return mapper.getOrgNum(code);
	}
	
}
