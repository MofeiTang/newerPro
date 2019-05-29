package com.newer.supervision.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newer.supervision.domain.ItemLeader;
import com.newer.supervision.domain.ItemOrg;
import com.newer.supervision.domain.ItemProcess;
import com.newer.supervision.domain.UserOrder;
import com.newer.supervision.mapper.IIndexMapper;

/**
 * 逻辑类
 * @author Administrator
 *
 */
@Service
public class IndexService implements IIndexMapper {
	@Autowired
	private IIndexMapper mapper;
	@Override
	public List<ItemProcess> findxin() {
		// TODO Auto-generated method stub
		return mapper.findxin();
	}
	@Override
	public List<ItemLeader> findtuijin() {
		// TODO Auto-generated method stub
		return mapper.findtuijin();
	}
	@Override
	public List<UserOrder> finddingyue(Long id) {
		// TODO Auto-generated method stub
		return mapper.finddingyue(id);
	}
	@Override
	public Long tongjiWC(Long userid) {
		// TODO Auto-generated method stub
		return mapper.tongjiWC(userid);
	}
	@Override
	public Long tongjiTJZ(Long userid) {
		// TODO Auto-generated method stub
		return mapper.tongjiTJZ(userid);
	}
	@Override
	public Long tongjiBYXZ(Long userid) {
		// TODO Auto-generated method stub
		return mapper.tongjiBYXZ(userid);
	}
	@Override
	public Long tongjiBYWC(Long userid) {
		// TODO Auto-generated method stub
		return mapper.tongjiBYWC(userid);
	}
	@Override
	public Long tongjiBYGX(Long userid) {
		// TODO Auto-generated method stub
		return mapper.tongjiBYGX(userid);
	}
	@Override
	public Long tongjiYQ(Long userid) {
		// TODO Auto-generated method stub
		return mapper.tongjiYQ(userid);
	}
	@Override
	public Long tongjiCS(Long userid) {
		// TODO Auto-generated method stub
		return mapper.tongjiCS(userid);
	}
	@Override
	public String loginTime(Long id) {
		// TODO Auto-generated method stub
		return mapper.loginTime(id);
	}
	@Override
	public Integer setLoginTime(Long id) {
		// TODO Auto-generated method stub
		return mapper.setLoginTime(id);
	}
	@Override
	public Integer delorder(String code, Long id) {
		// TODO Auto-generated method stub
		return mapper.delorder(code,id);
	}
	@Override
	public List<ItemOrg> likedingyue(String item_Name,Long id) {
		// TODO Auto-generated method stub
		return mapper.likedingyue(item_Name,id);
	}
	@Override
	public Long dubanItem() {
		// TODO Auto-generated method stub
		return mapper.dubanItem();
	}

}
