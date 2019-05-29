package com.newer.supervision.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newer.supervision.domain.User;
import com.newer.supervision.mapper.ILoginMapper;


@Service
public class LoginService implements ILoginMapper {
	@Autowired
	private ILoginMapper mapper;
	@Override
	public User Login(String UserName, String uPassWord) {
		// TODO Auto-generated method stub
		return mapper.Login(UserName, uPassWord);
	}

}
