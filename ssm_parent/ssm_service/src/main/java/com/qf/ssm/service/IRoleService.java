package com.qf.ssm.service;

import java.util.List;

import com.qf.ssm.domain.Permission;
import com.qf.ssm.domain.Role;

public interface IRoleService {

	
	 List<Role> findAll();

	  void addRole(Role role);

	Role findById(String id);

	List<Permission> findUserByIdAndAllRole(String id);

	void addRoleToPermission(String id, String roleId);
}
