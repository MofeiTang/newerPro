package com.newer.supervision.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newer.supervision.domain.Result;
import com.newer.supervision.domain.User;
import com.newer.supervision.service.MergeService;

@RestController
@RequestMapping("mc")
public class MergetController {
	@Autowired
	private MergeService service;
	
	/**
	 * 查询所有合并列表
	 * @return
	 */
	@RequestMapping("queryMergeAll")
	public ResponseEntity<?> queryMergeAll(){
		List<Result> list=service.queryMergeAll();
//		System.out.println(list.get(0).getHeadDepart());
//		System.out.println("mc中的queryMergeAll调用");
		return new ResponseEntity<List<Result>>(list,HttpStatus.OK);
	}
	
	/**
	 * 获取需要合并的列表
	 */
	@RequestMapping("queryOne")
	public ResponseEntity<?> queryOne(String list){
		//切掉括号
		String str=list.substring(1,list.length()-1);
		System.out.println("需要合并的列表"+str);
		List<Result> rlist=new ArrayList<Result>();
		//切掉点
		String string[]=str.split(",");
		for(int i=0;i<string.length;i++) {
			Result r=service.queryMergeOne(string[i]);
			rlist.add(r);
		}
		return new ResponseEntity<List<Result>>(rlist,HttpStatus.OK);
	}
	
	
	/**
	 * 查询领导人
	 * @return
	 */
	@RequestMapping("queryAdmin")
	public String queryAdmin() {
//		System.out.println("mc中的queryAdmin调用");
		User user=service.queryAdmin();
		return user==null?null:user.getRealname();
	}
	
	/**
	 * 查询基本信息
	 */
	@RequestMapping("queryJB")
	public ResponseEntity<?>queryJB(String itemCode){
		System.out.println("queryJB"+itemCode);
		Result result=service.queryJB(itemCode);
		return new ResponseEntity<Result>(result,HttpStatus.OK);
	}
	
	/**
	 * 查询其他信息
	 */
	@RequestMapping("queryOther")
	public Map<String,String> queryOther(String itemCode,HttpSession session){
		System.out.println("queryOther"+itemCode);
		Map<String,String>map=new HashMap<>();
		User user=(User) session.getAttribute("users");
		String delstate=service.queryDelState(itemCode);
		String itemstate=service.queryItemState(itemCode);
		String leader=service.queryAdmin().getRealname();
		if(user!=null) {
			map.put("username", user.getRealname());
		}else {
			map.put("username", "未登录");
		}
		map.put("delstate", delstate);
		map.put("itemstate", itemstate);
		map.put("leader", leader);
		return map;
	}
	
	@RequestMapping("hebin")
	public String hebin(String parrentItemCode,String childItemCode) {
		System.out.println("parrentItemCode:"+parrentItemCode+"---childItemCode:"+childItemCode);
		//根据父事件编号查询父mid
		String fumid=service.queryMid(parrentItemCode);
		//由于子id是多个，要切割分别查询出子mid
		String[]zid=childItemCode.split(",");
		String[]zimid=new String[zid.length];
		String childstr="";
		for(int i=0;i<zid.length;i++) {
			 zimid[i]=service.queryMid(zid[i]);
			 //为所有子id设置父id
			 service.hebin_setParrent(fumid, zimid[i]);
			 System.out.println(zimid[i]);
			childstr+=zimid[i]+",";
		}
		//为父id设置所有子id的集合
		service.hebin_setchild(childstr, fumid);
//		System.out.println(childstr);
		return "设置成功";
	}
	
	@RequestMapping("checkstate")
	public String checkstate(@Param("item_code")String item_code) {
		String mid=service.queryMid(item_code);
		String zilei=service.queryIsHaveChild(mid);
//		String fulei=service.queryIsHaveParrent(mid);

		service.queryFuid(item_code);
		if(zilei==null) {
			return "error";
		}
		return zilei;
	}
	
	@RequestMapping("queryChild")
	public ResponseEntity<?>queryChild(@Param("item_code")String item_code){
		String mid=service.queryMid(item_code);
		List<Result> zilei=service.queryChildByMid(mid);
		return new ResponseEntity<List<Result>>(zilei,HttpStatus.OK);
	}
	

	@RequestMapping("queryChildJian")
	public ResponseEntity<?>queryChildJian(@Param("item_code")String item_code){
		String fuid=service.queryFuid(item_code);
		List<Result> zilei=service.queryChildByMid(fuid);
		return new ResponseEntity<List<Result>>(zilei,HttpStatus.OK);
	}
	
	@RequestMapping("qxhb")
	public String qxhb(@Param("list")String list) {
		String[] str=list.split(",");
		for(int i=0;i<str.length;i++) {
			System.out.println("qxhb:itemcode: "+str[i]);
			System.out.println("ziid "+service.queryZiid(str[i]));
			String mid=service.queryMid(str[i]);
			System.out.println("mid "+mid);
			service.qxhb(str[i]);
		}
		return "合并取消";
	}
	
	@RequestMapping("xls")
	public String xls(@Param("list")String list) throws IOException {
		//创建工作簿
		HSSFWorkbook workBook = new HSSFWorkbook();
		//创建工作表  工作表的名字叫helloWorld
		HSSFSheet sheet = workBook.createSheet("helloWorld");
		HSSFRow row0=sheet.createRow(0);
		HSSFCell cell0=row0.createCell(0, Cell.CELL_TYPE_STRING);
		cell0.setCellValue("事项编号");
		
		HSSFCell cell01=row0.createCell(1, Cell.CELL_TYPE_STRING);
		cell01.setCellValue("事项名称");
		
		HSSFCell cell02=row0.createCell(2, Cell.CELL_TYPE_STRING);
		cell02.setCellValue("公司领导");
		
		HSSFCell cell03=row0.createCell(3, Cell.CELL_TYPE_STRING);
		cell03.setCellValue("牵头部门");
		
		HSSFCell cell04=row0.createCell(4, Cell.CELL_TYPE_STRING);
		cell04.setCellValue("负责人");
		
		HSSFCell cell05=row0.createCell(5, Cell.CELL_TYPE_STRING);
		cell05.setCellValue("应办结时间");
		
		HSSFCell cell06=row0.createCell(6, Cell.CELL_TYPE_STRING);
		cell06.setCellValue("最后修改时间");
		String str[]=list.split(",");
		for(int i=0;i<str.length;i++) {
			//创建行
			HSSFRow row=sheet.createRow(i+1);
			Result result=service.queryMergeOne(str[i]);
			//为编号赋值
			String item_code=result.getItem_code();
			HSSFCell cell=row.createCell(0, Cell.CELL_TYPE_STRING);
			cell.setCellValue(item_code);
			
			//为姓名赋值
			String item_name=result.getITEM_NAME();
			HSSFCell cell1=row.createCell(1, Cell.CELL_TYPE_STRING);
			cell1.setCellValue(item_name);
			
			String leader=service.queryAdmin().getRealname();
			HSSFCell cell2=row.createCell(2, Cell.CELL_TYPE_STRING);
			cell2.setCellValue(leader);
			
			String headDepart=result.getHeadDepart();
			HSSFCell cell3=row.createCell(3, Cell.CELL_TYPE_STRING);
			cell3.setCellValue(headDepart);
			System.out.println(headDepart);
			
			String username=result.getUsername();
			HSSFCell cell4=row.createCell(4, Cell.CELL_TYPE_STRING);
			cell4.setCellValue(username);
			
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String overtime=sdf.format(result.getOver_time());
			HSSFCell cell5=row.createCell(5, Cell.CELL_TYPE_STRING);
			cell5.setCellValue(overtime);
			
			String opttime=sdf.format(result.getOpt_time());
			HSSFCell cell6=row.createCell(6, Cell.CELL_TYPE_STRING);
			cell6.setCellValue(opttime);
			
		}
		
		File f=new File("d:\\poi\\测试.xls");
		FileOutputStream fos=new FileOutputStream(f);
		workBook.write(fos);
		System.out.println("文件写入");
		fos.close();
		return null;
	}

}
