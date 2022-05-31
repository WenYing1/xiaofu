package com.qf.ssm.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.qf.ssm.dao.IUserInfoDao;
import com.qf.ssm.domain.Role;
import com.qf.ssm.domain.UserInfo;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
	private IUserInfoDao userDao;
	public List<UserInfo> findAll() {
		List<UserInfo> list =userDao.findAll();
		return list;
	}
	public void insert(UserInfo user) {
		String string = UUID.randomUUID().toString();
		BCryptPasswordEncoder bpe =new BCryptPasswordEncoder();
		
		String encode = bpe.encode(user.getPassword());
		user.setPassword(encode);
		user.setId(string);
		userDao.add(user);
		
	}
	public UserInfo findById(String id) {
	UserInfo user =	userDao.findById(id);
		return user;
	}
	public List<Role> findUserByIdAndAllRole(String id) {
	List<Role> roles =	userDao.findUserByIdAndAllRole(id);
		return roles;
	}
	public void addRole(String id, String userId) {
		userDao.addRole(id,userId);
		
	}
	

}
