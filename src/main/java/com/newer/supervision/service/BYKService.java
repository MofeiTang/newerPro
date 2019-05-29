package com.newer.supervision.service;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newer.supervision.domain.Dict;
import com.newer.supervision.domain.ItemOrg;
import com.newer.supervision.domain.ItemProcess;
import com.newer.supervision.domain.ItemRepository;
import com.newer.supervision.domain.Organication;
import com.newer.supervision.mapper.IBYKMpper;
import com.newer.supervision.mapper.IBacklogMapper;

@Service
public class BYKService {
	
	@Autowired
	private IBYKMpper bykDao;
	@Autowired
	private IBacklogMapper blmDao;
	
	
	/**
	 * 增加新项
	 * @param irepository
	 * @return
	 */
	@Transactional
	public int addIrepository(ItemRepository irepository) {
		return bykDao.addIrepository(irepository);
	}
	
	/**
	 * 查看所有部门
	 * @return
	 */
//	public List<Dept> queryDept(){
//		return  bykDao.queryDept();
//	}
	
	/**
	 *  备用库首页数据初始化查询
	 * @return
	 */
	public List<ItemRepository> queryAllIrepository(){
		return bykDao.queryAllIrepository();
	}
	
	/**
	 * 查找要提交的事项内容
	 * @param item_name
	 * @return
	 */
	public ItemRepository queryIrepository(String id) {
		return bykDao.queryIrepository(id);
	}
	
	/**
	 * 添加事项与部门关系
	 * @param org
	 * @return
	 */
	@Transactional
	public Integer itemDept(ItemOrg org) {
		return bykDao.itemDept(org);
	}
	
	/**
	 * 添加事项与用户的关系
	 * @param itemCode
	 * @param userid
	 * @return
	 */
	@Transactional
	public Integer itemLeader(String itemCode,Long userid) {
		return bykDao.itemLeader(itemCode, userid);
	}
	
	/**
	 * 获得所有的部门
	 * @return
	 */
	public List<Organication> getAllDept(){
		return bykDao.getAllDept();
	}
	
	/**
	 * 把未删除的状态改为删除状态
	 * 也就是把del_status值改为1
	 * @param item_code
	 * @return
	 */
	public Integer update(String item_code) {
		return bykDao.update(item_code);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @param id
	 * @return
	 */
	public Integer deleteAll( List<?> ids) {
		return bykDao.deleteAll(ids);
	}
	
	/**
	 * 恢复删除的事项
	 * @param item_code
	 * @return
	 */
	public Integer reply(String item_code) {
		return bykDao.reply(item_code);
	}
	
	/**
	 * 查询事项编号是否重复
	 * @param code
	 * @return
	 */
	public Integer getItemCode(String code) {
		return bykDao.getItemCode(code);
	}
	
	/**
	 * 获得反馈频率
	 * @return
	 */
	public List<Dict> getFrequency(){
		return bykDao.getFrequency();
	}
	
	/**
	 * 设置反馈频率和下次反馈时间
	 * @return
	 */
	public Integer setNextTime(ItemRepository repository) {
		return bykDao.setNextTime(repository);
	}
	
	/**
	 * 督办员审批
	 * @return
	 */
	public List<ItemRepository> getCommission(){
		return bykDao.getCommission();
	}
	
	/**
	 * 修改退回的事项内容
	 * @param irepository
	 * @return
	 */
	public Integer updateItem_content(ItemRepository irepository) {
		return bykDao.updateItem_content(irepository);
	}
	
	/**
	 * 模糊查询
	 * @param source
	 * @param item_name
	 * @param serial_number
	 * @param source_time
	 * @return
	 */
	public List<ItemRepository> likeFind(String source,String item_name,String serial_number){
		List<ItemRepository> finds=bykDao.likeFind(source, item_name, serial_number);
		return finds;
	}
	
	/**
	 * 根据要修改的事项编号查询要修改的事项内容
	 * @param item_code
	 * @return
	 */
	public ItemRepository queryByCode(String item_code) {
		return bykDao.queryByCode(item_code);
	}
	
	
	
	/**
	 * 领导修改事项状态
	 * @param itemCode
	 * @return
	 */
	public Integer updItemStatus(String itemCode,String itemStatus) {
		return blmDao.updItemStatus(itemCode, itemStatus);
	}
	
	/**
	 * 获得事项进展信息
	 * @param code
	 * @return
	 */
	public ItemProcess getProgessInfo(String code) {
		return bykDao.getProgessInfo(code);
	}
	
	/**
	 *获得密级 
	 * @return
	 */
	public List<Dict> queryRank(){
		return bykDao.queryRank();
	}
	/**
	 * 查询文件类型
	 * @return
	 */
	public List<Dict> queryFile_type(){
		return bykDao.queryFile_type();
	}
}
