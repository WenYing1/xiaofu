package com.qf.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.qf.ssm.domain.Permission;

@Repository
public interface IPermissionDao {
  
	
	@Select("select * from permission")
	List<Permission> findAll();
    
	@Insert("insert permission(id,permissionName,url) values(#{id},#{permissionName},#{url})")
	void add(Permission permission);
}
