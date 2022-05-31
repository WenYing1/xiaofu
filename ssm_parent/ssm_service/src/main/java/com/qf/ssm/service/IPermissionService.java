package com.qf.ssm.service;

import java.util.List;

import com.qf.ssm.domain.Permission;

public interface IPermissionService {
    
	List<Permission> findAll();

	void add(Permission permission);
}
