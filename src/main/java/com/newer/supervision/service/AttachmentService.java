package com.newer.supervision.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newer.supervision.domain.Attachment;
import com.newer.supervision.domain.ItemRepository;
import com.newer.supervision.mapper.IAttachmentMapper;

@Service
public class AttachmentService implements IAttachmentMapper{
	@Autowired
	private IAttachmentMapper mapper;

	@Override
	public int InsertAttachment(String name,String path, String item_Code) {
		// TODO Auto-generated method stub
		System.out.println(name+path+item_Code);
		return mapper.InsertAttachment(name,path,item_Code);
	}

	@Override
	public int UpdateAttachment(Attachment att) {
		// TODO Auto-generated method stub
		return mapper.UpdateAttachment(att);
	}

	@Override
	public List<Attachment> queryAtt(String item_code) {
		// TODO Auto-generated method stub
		return mapper.queryAtt(item_code);
	}

	@Override
	public int DeleteAtt(String name) {
		// TODO Auto-generated method stub
		return mapper.DeleteAtt(name);
	}
	
	

}
