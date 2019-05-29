package com.newer.supervision.service;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newer.supervision.domain.ItemProcess;
import com.newer.supervision.domain.Parameter;
import com.newer.supervision.domain.User;
import com.newer.supervision.mapper.IUserMapper;
import com.newer.supervision.mapper.ItemListMapper;

@Service
public class ItemListService {
	
	@Autowired
	private ItemListMapper It;
	
	/**
	 * 获得事项进展
	 * @return
	 */
	public List<ItemProcess> getItemProgess(Long id){
		return It.getItemProgess(id);
	}
	
	/**
	 * 订阅
	 * @param order
	 * @return
	 */
	public Integer takeItem(@Param("code") String code,@Param("id") Long id) {
		return It.takeItem(code, id);
	}
	
	/**
	 * 模糊查询
	 * @param id
	 * @return
	 */
	public List<ItemProcess> likeCheck(Parameter par){
		return It.likeCheck(par);
	}
	
	/**
	 * 设置事项状态
	 * @param code
	 * @param id
	 * @return
	 */
	public Integer setTake(String code,Long id) {
		return It.setTake(code, id);
	}
	
	/**
	 * 统计推进中
	 * @param id
	 * @return
	 */
	public List<ItemProcess> getPropelling(Long id){
		return It.getPropelling(id);
	}
	
	/**
	 * 统计累计完成
	 * @param id
	 * @return
	 */
	public List<ItemProcess> getComplete(Long id){
		return It.getComplete(id);
	}
	
	/**
	 * 我的事项
	 * @param id
	 * @return
	 */
	public List<ItemProcess> getMyitem(Long id){
		return It.getMyitem(id);
	}
	
	/**
	 * 本月完成
	 * @param id
	 * @return
	 */
	public List<ItemProcess> getmonthcomplete(Long id){
		return It.getmonthcomplete(id);
	}
	
	/**
	 * 本月更新
	 * @param id
	 * @return
	 */
	public List<ItemProcess> getmonthupdate(Long id){
		return It.getmonthupdate(id);
	}
	
	/**
	 * 统计待办事项超时
	 * @param id
	 * @return
	 */
	public List<ItemProcess> getOvertime(Long id){
		return It.getOvertime(id);
	}
	
	/**
	 * 统计延期
	 * @param id
	 * @return
	 */
	public List<ItemProcess> getDelay(Long id){
		return It.getDelay(id);
	}
}
