package com.newer.supervision.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.newer.supervision.domain.Attachment;
import com.newer.supervision.domain.ItemRepository;
import com.newer.supervision.service.AttachmentService;
import com.newer.supervision.util.ZipMultiFile;

@RestController
@RequestMapping("/file")
public class UploadAndDownloadController {
	private String path="D://Test/";
	@Autowired
	private AttachmentService service;
    /**
     * 单文件上传
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/upload")
    public String upload(MultipartFile file, HttpServletRequest request) {
    	//获取事项编号
    	String item_code=request.getParameter("item_code");
        if (!file.isEmpty()) {
        	//获取上传的文件名字
            String saveFileName = file.getOriginalFilename();
            // a. 生成一个唯一标记
			// b. 与文件名拼接
			saveFileName =  item_code+"#"+ saveFileName;
            System.out.println("saveFileName:"+saveFileName);
            //设置存储路径，后面该值为数据库中用户的路径
            File saveFile = new File(path+saveFileName);
            //文件夹不存在就创建一个
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            	//写数据
                BufferedOutputStream out;
				try {
				out = new BufferedOutputStream(new FileOutputStream(saveFile));
				out.write(file.getBytes());
                out.flush();
                out.close();
                
                //插入数据库
                System.out.println("saveFileName"+saveFileName+" path"+path+" item"+item_code);
                service.InsertAttachment(saveFileName, path, item_code);
                return saveFileName + "上传成功";
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "上传失败，文件路径错误.";
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        } else {
            return "上传失败，因为文件为空.";
        }
		return "上传失拜，文件不存在";
    } 
    
    /**
     *根据item_code查询附件表
     * @param item_code
     * @return
     */
    @RequestMapping("/queryAtt")
    public ResponseEntity<?>query(@Param("item_code")String item_code){
    	System.out.println("item_code:"+item_code);
    	List<Attachment> att=service.queryAtt(item_code);
    	return new ResponseEntity<List<Attachment>>(att,HttpStatus.OK);
    }
    
    /**
	 * 批量下载
	 * @return 
	 */
	@RequestMapping(value="/downAll")
	public String downloadAll(String item_code,HttpServletResponse response) {
		//设置数组序号
		int i=0;
		System.out.println("item_code的值为： "+item_code);
			Map map=getMessageMap(path, item_code);
			//根据map长度设置数组长度
			File[] files=new File[map.size()];
			//设置压缩后的文件名
			File zipfile=new File(path+item_code+"压缩.zip");
			//遍历map的所有文件，并装入压缩数组
		 Iterator<Entry<String,String>> iter = map.entrySet().iterator();
		  while(iter.hasNext()){
		   Entry<String,String> entry = iter.next();
		   String key = entry.getKey();
		   String value = entry.getValue();
		   //给file数组赋值
		   File file=new File(path+key);
		   System.out.println("文件路径:  "+path+key);
		   files[i]=file;
		   i++;
		  }
		  //压缩
		  ZipMultiFile.zipFiles(files,zipfile);
		   try {
			   //将压缩后的数据写入流中
				down(path,item_code+"压缩.zip", response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "批量下载出现错误";
			}
		  return "ojbk";
	}

    
   
    /**
     * 文件下载
     */
    @RequestMapping("download")
    public String downLoad(HttpServletResponse response, String filename){
    	
    	System.out.println("fileName:"+filename);
    	//由于#为分隔符会被拦截。html使用@作为分隔符，在这里手动切割拼接
    	String[] test=filename.split("@");
    	String spFile=test[1]+"#"+test[0];
        String filePath = "D:\\Test" ;
        System.out.println("spfile:"+spFile);
       try {
			down(filePath, spFile, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return "正在下载...";
    }

    
	/**
	 *  下载的方法
	 */
	private void down(String bathPath,String fileName,HttpServletResponse response)throws ServletException, IOException {
			File file=new File(bathPath,fileName);
			System.out.println("完全路径"+file.getAbsolutePath()+"   file是否存在："+file.exists());
		 if(file.exists()){ //判断文件父目录是否存在
						// 获取一个文件流
						InputStream in = new FileInputStream(file);	
						// 如果文件名是中文，需要进行url编码
						fileName = URLEncoder.encode(fileName, "UTF-8");
						// 设置下载的响应头
						response.setHeader("content-disposition", "attachment;fileName=" + fileName);
						// 获取response字节流
						OutputStream out = response.getOutputStream();
						byte[] b = new byte[1024];
						int len = -1;
						while ((len = in.read(b)) != -1){
							out.write(b, 0, len);
						}
						// 关闭
						out.close();
						in.close();	
		 }		
	}
    
    
	/**
	 * 根据目录和事项id获得该目录下的所有文件
	 */
	private Map<String,String> getMessageMap(String bathPath,String item_code) {
		//1. 初始化map集合Map<包含唯一标记的文件名, 简短文件名>  ;
		Map<String,String> fileNames = new HashMap<String,String>();
		// 目录
		File file = new File(bathPath);
		// 目录下，所有文件名
		String list[] = file.list();
		// 遍历，封装
		if (list != null && list.length > 0){
			for (int i=0; i<list.length; i++){
				// 全名
				String fileName = list[i];
				//切割出文件的id
				if(fileName.contains("#")) {
					String checkName=fileName.substring(0, fileName.lastIndexOf("#"));
					if(checkName.equals(item_code)) {
						String shortName = fileName.substring(fileName.lastIndexOf("#")+1);
						System.out.println("短名"+shortName+" 长名"+fileName+" checkName"+checkName);
						// 封装
						fileNames.put(fileName, shortName);
					}
				}
				// 短名
			}
		}
		return fileNames;
	}
	
	/**
	 * 根据目录和短姓名获得来删除文件
	 */
	@RequestMapping("/delete")
	private String deleteFile(@Param("fileName")String fileName) {
				System.out.println("fileName:"+fileName);
					String delPath=path+fileName;
					System.out.println("即将删除的文件名称: "+delPath);
					File f=new File(delPath);
					f.delete();
					service.DeleteAtt(fileName);
					return "删除成功";
	}
	
	/*
	 * 获取文件下载列表,暂确定为D:\\test
	 */
	@GetMapping("getNum")
	public ResponseEntity<?> getNum(@Param("item_code")String item_code) {
		System.out.println("getNum"+item_code);
		Map map=getMessageMap(path,item_code); 
		return new ResponseEntity<Map>(map,HttpStatus.OK);
	}
	
	
	 /**
     * 多文件上传
     * @param files
     * @return
     */
    
    /*
     * 上传多个文件
     */
   @RequestMapping(value = "/plsc", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    boolean uploadFiles(@RequestParam("fileName") MultipartFile [] files) {
        boolean result = false;
        String realPath;
        System.out.println("files:"+files.length);
        for(int i=0;i<files.length;i++){
            if (!files[i].isEmpty()) {  

                   String uniqueName=files[i].getOriginalFilename();//得到文件名

                    realPath="E:"+File.separator+uniqueName;//文件上传的路径这里为E盘
                  
                    try {
						files[i].transferTo(new File(realPath));
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}   // 转存文件
                    result = true;           
                }
            }
        return result;
        }
   
   /**

    *多文件具体上传时间，主要是使用了MultipartHttpServletRequest和MultipartFile

    *@param request

    *@return

    */

   @RequestMapping(value="/batch/upload", method=RequestMethod.POST) 
   @ResponseBody 
public String handleFileUpload(HttpServletRequest request){ 

    List<MultipartFile> files =((MultipartHttpServletRequest)request).getFiles("file"); 
    System.out.println("文件大小"+files.size());
//    MultipartFile file = null;
    
//    BufferedOutputStream stream = null;

//    for (int i =0;i< files.size(); ++i) { 
//
//        file = files.get(i); 
//
//        if (!file.isEmpty()) { 
//
//            try { 
//
//                byte[]bytes =file.getBytes(); 
//
//                stream =  new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename()))); 
//
//                stream.write(bytes); 
//
//                stream.close(); 
//
//            } catch (Exception e) { 
//
//                   stream =  null;
//
//                return"You failed to upload " +i + " =>" +e.getMessage(); 
//
//            } 
//
//        } else { 
//
//            return"You failed to upload " +i + " becausethe file was empty."; 
//
//        } 
//
//    } 

    return"upload successful"; 

} 
}