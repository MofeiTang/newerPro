package com.newer.supervision.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.newer.supervision.domain.Dict;



public interface IDictMapper {
	@Select("select * from Dict where type='edu'")
	public  List<Dict> findEdu();
	@Select("select * from Dict where type='job'")
	public  List<Dict> findJob();
	@Select("select * from Dict where type='jobtype'")
	public  List<Dict> findJobtype();
}
