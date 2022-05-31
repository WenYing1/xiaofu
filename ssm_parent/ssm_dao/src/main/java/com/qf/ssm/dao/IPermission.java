package com.qf.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.qf.ssm.domain.Permission;

@Repository
public interface IPermission {
	
	@Select("select * from permission where id in(select permissionId from role_permission where roleId =#{id})")
	List<Permission> findById(String id);

}
