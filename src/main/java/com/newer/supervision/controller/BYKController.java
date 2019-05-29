package com.newer.supervision.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newer.supervision.domain.Dict;
import com.newer.supervision.domain.ItemOrg;
import com.newer.supervision.domain.ItemProcess;
import com.newer.supervision.domain.ItemRepository;
import com.newer.supervision.domain.Organication;
import com.newer.supervision.service.BYKService;

@CrossOrigin(allowedHeaders="*",allowCredentials="true", origins="*",maxAge=3600)
@RestController
@RequestMapping(value = "/list")
public class BYKController {
		
	@Autowired
	private  BYKService bykSvr;

	/**
	 * 增加新项
	 * @param irepository
	 * @return
	 */
	@RequestMapping(value="/addIrepository",method=RequestMethod.POST)
	public String addIrepository(@RequestBody ItemRepository irepository){
		System.out.println("ItemRepository"+irepository.getRemark());
		int i=bykSvr.addIrepository(irepository);
		if(i>0) {
			System.out.println("添加成功----------");
			return "添加成功";
		}else {
			return "";
		}
		
	}
	
	/**
	 * 备用库首页数据初始化查询
	 * @return
	 */
	@RequestMapping(value="/queryAllIrepository",method=RequestMethod.GET)
	public ResponseEntity<?> queryAllIrepository(){
		List<ItemRepository> lists=bykSvr.queryAllIrepository();
		if(lists.size()>0) {
			return new ResponseEntity<List<ItemRepository>>(lists,HttpStatus.OK);
		}else {
			return new ResponseEntity<List<ItemRepository>>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * 查询所有部门
	 * @return
	 */
//	@RequestMapping(value="/queryDept",method=RequestMethod.GET)
//	public  ResponseEntity<?> queryDept(){
//		List<Dept> depts=bykSvr.queryDept();
//		if(depts.size()>0) {
//			return new ResponseEntity<List<Dept>>(depts,HttpStatus.OK);
//		}else {
//			return new ResponseEntity<List<Dept>>(HttpStatus.NOT_FOUND);
//		}
//	}
	
	/**
	 * 修改退回的事项内容
	 * @param irepository
	 * @return
	 */
	@RequestMapping(value="updateItem_content",method=RequestMethod.POST)
	public String updateItem_content(@RequestBody ItemRepository irepository){
		Integer irepository_=bykSvr.updateItem_content(irepository);
		bykSvr.updItemStatus(irepository.getItem_Code(),"1");
		System.out.println("----------修改成功");
		if(irepository!=null) {
			return "修改成功！！！";
		}else {
			return "修改失败！！！";
		}
	}
	
	/**
	 * 查询事项要提交的内容
	 * @param item_name
	 * @return
	 */
	@RequestMapping(value="/queryIrepository",method=RequestMethod.GET)
	public ResponseEntity<?>  queryIrepository(@RequestParam("id") String id ){
		ItemRepository irepository=bykSvr.queryIrepository(id);
		if(irepository!=null) {
			return new ResponseEntity<ItemRepository>(irepository,HttpStatus.OK);
		}
			return new ResponseEntity<ItemRepository>(HttpStatus.NOT_FOUND);
	}
	
	/**
	 * 添加事项与部门关系
	 * @param org
	 * @return
	 */
	@PostMapping(value="itemDept/{id}")
	public String itemDept(@PathVariable("id") Long did,@RequestBody ItemOrg org) {
		System.out.println("orgid:"+org.getOrgid().getOrgId());
		System.out.println("did:"+did);
		if(org!=null) {
			Integer iss=bykSvr.itemDept(org);
			org.getOrgid().setOrgId(did);
			org.setIsprimary("2");
			Integer iss1=bykSvr.itemDept(org);
			System.out.println("code:"+org.getItem_Code().getItem_Code());
			System.out.println("userid:"+org.getUserid().getUserid());
			Integer iss3=bykSvr.setNextTime(org.getItem_Code());
			bykSvr.updItemStatus(org.getItem_Code().getItem_Code(), "5");
			if(iss!=null&&iss1!=null&&iss3!=null) {
				return "操作成功";
			}
		}
		return "";
	}
	
	
	/**
	 * 获得所有的部门
	 * @return
	 */
	@GetMapping(value="getdept")
	public ResponseEntity<?> getAllDept(){
		List<Organication> olist=bykSvr.getAllDept();
		return new ResponseEntity<List<Organication>>(olist, HttpStatus.OK);
	}
	
	/**
	 * 批量删除
	 * @param strs
	 * @return
	 */
	@RequestMapping(value="deleteall",method=RequestMethod.GET)
	public String deleteall(@RequestParam("strs") String strs) {
		System.out.println("------------"+strs);
		List<String> lists=Arrays.asList(strs.split(","));
		bykSvr.deleteAll(lists);
		return null;
	}
	
	/**
	 * 删除事项
	 * @param item_code
	 * @return
	 */
	@RequestMapping(value="udpateDel_status",method=RequestMethod.GET)
	public ResponseEntity<?> udpateDel_status(@RequestParam("item_code") String item_code){
		System.out.println("-------------"+item_code);
		Integer i=bykSvr.update(item_code);
		if(i!=null) {
			return new ResponseEntity<String>("{\"status\":\"删除成功\"}",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("{\"status\":\"删除失败\"}",HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * 恢复删除的事项
	 * @param item_code
	 * @return
	 */
	@RequestMapping(value="reply",method=RequestMethod.GET)
	public  ResponseEntity<?> reply(@RequestParam("item_code") String item_code){
		Integer i=bykSvr.reply(item_code);
		if(i!=null) {
			return new ResponseEntity<String>("{\"status\":\"恢复成功\"}",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("{\"status\":\"恢复失败\"}",HttpStatus.NOT_FOUND);
		}
		
	}
	
	/**
	 * 查询事项编号是否重复
	 * @param code
	 * @return
	 */
	@GetMapping(value="getItemCode")
	public Integer getItemCode(@RequestParam("item_Code") String code) {
		if(code!=null) {
			Integer count=bykSvr.getItemCode(code);
			System.out.println("count:"+count);
			if(count>0) {
				return count;
			}
		}
		return null;
	}
	
	/**
	 * 获得反馈频率
	 * @return
	 */
	@GetMapping(value="getfrequency")
	public ResponseEntity<?> getFrequency(){
		List<Dict> dlist=bykSvr.getFrequency();
		return new ResponseEntity<List<Dict>>(dlist, HttpStatus.OK);
	}
	
	/**
	 * 督办员审批
	 * @return
	 */
	@GetMapping(value="commission")
	public ResponseEntity<List<ItemRepository>> getCommission(){
		List<ItemRepository> dlist=bykSvr.getCommission();
			return new ResponseEntity<List<ItemRepository>>(dlist, HttpStatus.OK);
	}
	
	/**
	 * 模糊查询
	 * @param source
	 * @param item_name
	 * @param serial_number
	 * @param source_time
	 * @return
	 */
	@RequestMapping(value="likeFind",method=RequestMethod.GET)
	public ResponseEntity<List<ItemRepository>> likeFind(@RequestParam(required=false,name="source") String source,@RequestParam(required=false,name="item_name") String item_name,@RequestParam(required=false,name="serial_number") String serial_number){
		System.out.println("--------------------"+source+item_name+serial_number);
		if(source!=null) {
			source="%"+source+"%";
			if(item_name!=null) {
				item_name="%"+item_name+"%";
				if(serial_number!=null) {
					serial_number="%"+serial_number+"";
				}
			}
		}
		List<ItemRepository> irepositorys=bykSvr.likeFind(source, item_name, serial_number);
		if(irepositorys.size()>0) {
			return new ResponseEntity<List<ItemRepository>>(irepositorys,HttpStatus.OK);
		}else {
			return new ResponseEntity<List<ItemRepository>>(irepositorys,HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * 根据编号查询需要修改的事项内容
	 * @param item_code
	 * @return
	 */
	@RequestMapping(value="queryByCode",method=RequestMethod.GET)
	public ResponseEntity<?> queryByCode(@RequestParam("item_code") String item_code){
		System.out.println("-------------"+item_code);
		ItemRepository irepository=bykSvr.queryByCode(item_code);			
		if(irepository!=null) {
			return new ResponseEntity<ItemRepository>(irepository,HttpStatus.OK);
		}else {
			return new ResponseEntity<ItemRepository>(irepository,HttpStatus.NOT_FOUND);
		}
	}
	
	
	/**
	 * 获得事项进展信息
	 * @param code
	 * @return
	 */
	@GetMapping(value="progessinfo")
	public ResponseEntity<ItemProcess> getProgessInfo(@RequestParam("code") String code) {
		ItemProcess ipro=bykSvr.getProgessInfo(code);
		return new ResponseEntity<ItemProcess>(ipro, HttpStatus.OK);
	}
	
		/**
		 * 获得文件类型
		 * @return
		 */
		@RequestMapping(value="queryFile_type",method=RequestMethod.GET)
		public ResponseEntity<?> queryFile_type(){
			List<Dict> dicts=bykSvr.queryFile_type();
			if(dicts!=null) {
				return new ResponseEntity<List<Dict>>(dicts,HttpStatus.OK);
			}else {
				return new ResponseEntity<List<Dict>>(dicts,HttpStatus.NOT_FOUND);
			}
		}
		/**
		 * 获得密级
		 * @return
		 */
		@RequestMapping(value="queryRank",method=RequestMethod.GET)
		public ResponseEntity<?> queryRank(){
			List<Dict> dicts=bykSvr.queryRank();
			if(dicts!=null) {
				return new ResponseEntity<List<Dict>>(dicts,HttpStatus.OK);
			}else {
				return new ResponseEntity<List<Dict>>(dicts,HttpStatus.NOT_FOUND);
			}
		}
}
