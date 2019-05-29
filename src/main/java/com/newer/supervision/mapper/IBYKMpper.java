package com.newer.supervision.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.supervision.domain.Dict;
import com.newer.supervision.domain.ItemOrg;
import com.newer.supervision.domain.ItemProcess;
import com.newer.supervision.domain.ItemRepository;
import com.newer.supervision.domain.Organication;

/**
 * 备用库dao层
 * @author Administrator
 *
 */
public interface IBYKMpper {
	
	/**
	 * 新增事项
	 * @param irepository
	 * @return
	 */
	
	@Insert("INSERT INTO item_repository\r\n" + 
			"(ID,SOURCE,SOURCE_TIME,SERIAL_NUM,FILE_TYPE,DRAFTER,DRAFTER_TEL,ITEM_NAME,ITEM_CODE,USERNAME,ADVERSE_COMP,SECRITY,ITEM_CONTENT,REMARK,ITEM_TIME)\r\n" + 
			" VALUES \r\n" + 
			" (NULL,#{source},NOW(),#{serial_Num},#{file_Type},#{drafter},#{drafter_Tel},#{item_Name},#{item_Code},#{userName},#{adverse_Comp},#{secrity},#{item_Content},#{remark},DATE_ADD(CURDATE(),INTERVAL 3 DAY))")
	public int addIrepository(ItemRepository irepository);
	
	/**
	 * 查询所有部门
	 * @return
	 */
//	
//	@Select(" SELECT TYPE_ID,TYPE_NAME FROM dict WHERE TYPE ='ORGID'")
//	@Results({
//		@Result(property="id",column="TYPE_ID"),
//		@Result(property="name",column="TYPE_NAME")
//	})
//	public List<Dept> queryDept(); 
	
	
	/**
	 * 备用库首页数据初始化查询
	 * @return
	 */
	@Select("SELECT * FROM item_repository")
//	@Results({
//		@Result(property="source",column="SOURCE"),
//		@Result(property="item_name",column="ITEM_NAME"),
//		@Result(property="source_time",column="SOURCE_TIME"),
//		@Result(property="adverse_company",column="ADVERSE_COMPANY"),
//		@Result(property="file_type",column="FILE_TYPE"),
//		@Result(property="item_code",column="ITEM_CODE")
//		ITEM_CODE,SOURCE,ITEM_NAME,SOURCE_TIME,ADVERSE_COMPANY,FILE_TYPE
//		ITEM_NAME,SOURCE,SOURCE_TIME,ITEM_CONTENT	
//	})
	public List<ItemRepository> queryAllIrepository();
	
	
	/**
	 * 查找要提交的事项内容
	 * @param item_name
	 * 
	 * @return
	 */
	
	@Select("SELECT * FROM item_repository WHERE ITEM_CODE=#{id}")
	public ItemRepository queryIrepository(String id);
	
	/**
	 * 添加事项与部门关系
	 * @param org
	 * @return
	 */
	@Insert("INSERT INTO item_org(ITEM_CODE,ORGID,ISPRIMARY) VALUES(#{org.item_Code.item_Code},#{org.orgid.orgId},#{org.isprimary})")
	public Integer itemDept(@Param("org") ItemOrg org);
	
	/**
	 * 添加事项与用户的关系
	 * @param itemCode
	 * @param userid
	 * @return
	 */
	@Insert("INSERT INTO item_leader VALUES(#{code},#{userid})")
	public Integer itemLeader(@Param("code") String itemCode,@Param("userid") Long userid);
	
	/**
	 * 获得所有的部门
	 * @return
	 */
	@Select("SELECT r.*,o.orgid oid,o.orgcode ocode,o.orgname oname,o.parent_orgid pid,o.idpath oidpath,o.namepath onapath,o.intro ointro,o.job ojob,o.opt_time otime,o.order oder FROM organication r,organication o WHERE r.parent_orgid=o.orgid")
	@Results({
		@Result(property="parent_Orgid.orgId",column="oid"),
		@Result(property="parent_Orgid.orgCode",column="ocode"),
		@Result(property="parent_Orgid.orgName",column="oname"),
		@Result(property="parent_Orgid.parent_Orgid.orgId",column="pid"),
		@Result(property="parent_Orgid.idPath",column="oidpath"),
		@Result(property="parent_Orgid.namePath",column="onapath"),
		@Result(property="parent_Orgid.inTro",column="ointro"),
		@Result(property="parent_Orgid.job",column="ojob"),
		@Result(property="parent_Orgid.orgOrder",column="oder"),
		@Result(property="parent_Orgid.opt_Time",column="otime")
	})
	public List<Organication> getAllDept();
	
	/**
	 * 把未删除的状态改为删除状态
	 * 也就是把del_status值改为1
	 * @param item_code
	 * @return
	 */
	@Update("UPDATE item_repository SET DEL_STATUS ='1' WHERE ITEM_CODE=#{item_code}")
	public Integer update(String item_code);
	
	/**
	 * 批量删除
	 * @param ids
	 * @param id
	 * @return
	 */
	public Integer deleteAll(@Param("list") List<?> ids);
	
	/**
	 * 恢复删除的事项
	 * @param item_code
	 * @return
	 */
	@Update("UPDATE item_repository SET DEL_STATUS ='2' WHERE ITEM_CODE=#{item_code}")
	public Integer reply(String item_code);
	
	/**
	 * 查询事项编号是否重复
	 * @param code
	 * @return
	 */
	@Select("SELECT COUNT(*) FROM item_repository ir WHERE ir.`ITEM_CODE`=#{code}")
	public Integer getItemCode(String code);
	
	/**
	 * 获得反馈频率
	 * @return
	 */
	@Select("SELECT *FROM dict WHERE dict.`TYPE`='FREQUENCY'")
	public List<Dict> getFrequency();
	
	/**
	 * 设置反馈频率和下次反馈时间
	 * @return
	 */
	@Update("UPDATE item_repository ir SET ir.`CALL_BACK`=#{call_Back},ir.`NEXT_CALLTIME`=#{next_Calltime} WHERE ir.`ITEM_CODE`=#{item_Code}")
	public Integer setNextTime(ItemRepository repository);
	
	/**
	 * 督办员审批
	 * @return
	 */
	@Select("SELECT a.ITEM_NAME,a.ITEM_STATUS,a.`OVER_TIME`,a.`ITEM_CODE`,bj FROM (SELECT ir.`ITEM_NAME`,t.type_name ITEM_STATUS,ir.`OVER_TIME`,ir.`ITEM_CODE` FROM item_repository ir,dict t WHERE ir.`ITEM_STATUS`=t.type_id AND t.type='ITEM_status' AND ir.`ITEM_STATUS` IN('4','7')) a LEFT JOIN item_process b ON a.Item_code=b.item_code AND bj=2")
	public List<ItemRepository> getCommission();
	//SELECT a.ITEM_NAME,a.ITEM_STATUS,a.`OVER_TIME`,a.`ITEM_CODE` FROM (SELECT ir.`ITEM_NAME`,t.type_name ITEM_STATUS,ir.`OVER_TIME`,ir.`ITEM_CODE` FROM item_repository ir,dict t WHERE ir.`ITEM_STATUS`=t.type_id AND t.type='ITEM_status' AND ir.`ITEM_STATUS` IN('4','7')) a JOIN item_process b ON a.Item_code=b.item_code AND bj=2
	/**
	 * 模糊查询
	 * @param source事项来源
	 * @param item_name  事项名称
	 * @param serial_number 原流水号
	 * @param source_time  来源时间
	 * @return
	 */
	public List<ItemRepository> likeFind(@Param("source_") String source,@Param("item_name_") String item_name,@Param("serial_number_") String serial_number);
	
	/**
	 * 修改退回的事项内容
	 * @param irepository  
	 * @return
	 */
	public Integer updateItem_content(ItemRepository irepository);
	
	/**
	 * 根据事项编号查询要修改的事项内容
	 * @param item_code
	 * @return
	 */
	@Select("SELECT SOURCE,SOURCE_TIME,SERIAL_NUM,FILE_TYPE,DRAFTER,DRAFTER_TEL,ITEM_NAME,ITEM_CODE,USERNAME,ADVERSE_COMP,SECRITY,ITEM_CONTENT,REMARK FROM item_repository WHERE item_code=#{item_code}")
	public ItemRepository queryByCode(String item_code);
	
	
	
	/**
	 * 获得事项进展信息
	 * @param code
	 * @return
	 */
	@Select("SELECT ip.*,d.`TYPE_NAME` FROM item_process ip,dict d WHERE ip.`ITEM_CODE`=#{code} \r\n" + 
			"AND d.`TYPE_ID`=ip.`ITEM_STATUS` AND bj=2")
	@Results({
		@Result(property="item_Status",column="TYPE_NAME")
	})
	public ItemProcess getProgessInfo(String code);
	//SELECT * FROM item_process ip WHERE ip.`ITEM_CODE`=#{code} and bj=2
	/**
	 * 获取文件类型
	 * @return
	 */
	@Select("SELECT TYPE_ID,TYPE_NAME FROM dict WHERE TYPE='FILE_TYPE' ")
	@Results({
		@Result(property="type_ID",column="TYPE_ID"),
		@Result(property="type_Name",column="TYPE_NAME")
	})
	public List<Dict> queryFile_type();
	
	/**
	 * 获得文件密级
	 * @return
	 */
	@Select("SELECT TYPE_ID,TYPE_NAME FROM dict WHERE TYPE='RANK'")
	@Results({
		@Result(property="type_ID",column="TYPE_ID"),
		@Result(property="type_Name",column="TYPE_NAME")
	})
	public List<Dict> queryRank();
	
}
