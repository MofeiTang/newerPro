package com.newer.supervision.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.supervision.domain.Attachment;

public interface IAttachmentMapper {
	@Insert("INSERT INTO attachment VALUES(NULL,#{name},#{path},#{item_Code},now())")
	public int InsertAttachment(@Param("name")String name ,@Param("path")String path,@Param("item_Code")String item_Code);
	
	@Update("update attachment set name=#{name},OPT_TIME=now() where item_code=#{item_Code.item_Code}")
	public int UpdateAttachment(Attachment att);
	
	@Select("select * from attachment where item_code=#{item_code}")
	public List<Attachment> queryAtt(String item_code);
	
	@Delete("delete from attachment where name=#{name}")
	public int DeleteAtt(String name);

}
