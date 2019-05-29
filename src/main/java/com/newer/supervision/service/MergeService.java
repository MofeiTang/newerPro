package com.newer.supervision.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newer.supervision.domain.Result;
import com.newer.supervision.domain.User;
import com.newer.supervision.mapper.IMergeMapper;


@Service
public class MergeService implements IMergeMapper{
	@Autowired
	private IMergeMapper mapper;

	@Override
	public List<Result> queryMergeAll() {
		// TODO Auto-generated method stub
		return mapper.queryMergeAll();
	}

	@Override
	public User queryAdmin() {
		// TODO Auto-generated method stub
		return mapper.queryAdmin();
	}

	@Override
	public Result queryMergeOne(String item_code) {
		// TODO Auto-generated method stub
		return mapper.queryMergeOne(item_code);
	}

	@Override
	public Result queryJB(String item_code) {
		// TODO Auto-generated method stub
		return mapper.queryJB(item_code);
	}

	@Override
	public String queryDelState(String item_code) {
		// TODO Auto-generated method stub
		return mapper.queryDelState(item_code);
	}

	@Override
	public String queryItemState(String item_code) {
		// TODO Auto-generated method stub
		return mapper.queryItemState(item_code);
	}

	@Override
	public String queryMid(String item_code) {
		// TODO Auto-generated method stub
		return mapper.queryMid(item_code);
	}

	@Override
	public int hebin_setParrent(String fuid, String mid) {
		// TODO Auto-generated method stub
		return mapper.hebin_setParrent(fuid, mid);
	}

	@Override
	public int hebin_setchild(String ziid, String mid) {
		// TODO Auto-generated method stub
		return mapper.hebin_setchild(ziid, mid);
	}

	@Override
	public List<Result> queryChildByMid(String fuid) {
		// TODO Auto-generated method stub
		return mapper.queryChildByMid(fuid);
	}

	@Override
	public String queryIsHaveChild(String mid) {
		// TODO Auto-generated method stub
		return mapper.queryIsHaveChild(mid);
	}

	@Override
	public String queryFuid(String item_code) {
		// TODO Auto-generated method stub
		return mapper.queryFuid(item_code);
	}

	@Override
	public int qxhb(String item_code) {
		// TODO Auto-generated method stub
		return mapper.qxhb(item_code);
	}

	@Override
	public String queryIsHaveParrent(String mid) {
		// TODO Auto-generated method stub
		return mapper.queryIsHaveParrent(mid);
	}

	@Override
	public String queryZiid(String item_code) {
		// TODO Auto-generated method stub
		return mapper.queryZiid(item_code);
	}

}
