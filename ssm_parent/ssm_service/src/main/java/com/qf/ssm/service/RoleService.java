package com.qf.ssm.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qf.ssm.dao.IRoleDao;
import com.qf.ssm.domain.Permission;
import com.qf.ssm.domain.Role;
@Service
public class RoleService implements IRoleService {
	 @Autowired
     private IRoleDao roleDao;
	public List<Role> findAll() {
	List<Role> roles=roleDao.findAll();
		return roles;
	}
	public void addRole(Role role) {
		String string = UUID.randomUUID().toString();
		role.setId(string);
		roleDao.addRole(role);
		
	}
	public Role findById(String id) {
		Role role = roleDao.findByRoleId(id);
		return role;
	}
	public List<Permission> findUserByIdAndAllRole(String id) {
	List<Permission> permissions=roleDao.findUserByIdAndAllRole(id);
		return permissions;
	}
	public void addRoleToPermission(String id, String roleId) {
		roleDao.addRoleToPermission(id,roleId);
		
	}

}
