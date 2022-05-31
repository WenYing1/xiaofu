package com.qf.ssm.service;

import java.util.List;

import com.qf.ssm.domain.Role;
import com.qf.ssm.domain.UserInfo;

public interface IUserService {
    
	List<UserInfo> findAll();

	void insert(UserInfo user);

	UserInfo findById(String id);

	List<Role> findUserByIdAndAllRole(String id);

	void addRole(String id, String userId);

	

	
}
