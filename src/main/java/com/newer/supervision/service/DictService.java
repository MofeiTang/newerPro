package com.newer.supervision.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newer.supervision.domain.Dict;
import com.newer.supervision.mapper.IDictMapper;

@Service
public class DictService {
	@Autowired
	private IDictMapper ID;
	
	public List<Dict> findEdu() {
		return ID.findEdu();
	}
	public List<Dict> findJob() {
		return ID.findJob();
	}
	public List<Dict> findJobtype() {
		return ID.findJobtype();
	}
}
