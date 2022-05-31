package com.qf.ssm.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qf.ssm.dao.IPermissionDao;
import com.qf.ssm.domain.Permission;

@Service
public class PermissionService implements IPermissionService {
    @Autowired
	private IPermissionDao dao;
	public List<Permission> findAll() {
	List<Permission> list =	dao.findAll();
		return list;
	}
	public void add(Permission permission) {
		
		String string = UUID.randomUUID().toString();
		permission.setId(string);
		dao.add(permission);
		
	}

}
