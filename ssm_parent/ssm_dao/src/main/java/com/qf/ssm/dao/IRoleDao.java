package com.qf.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import com.qf.ssm.domain.Permission;
import com.qf.ssm.domain.Role;

@Repository
public interface IRoleDao {

	@Select("select * from role where id in(select roleId from users_role where userId =#{id})")
	@Results({ @Result(id = true, column = "id", property = "id"), 
		    @Result(column = "roleName", property = "roleName"),
			@Result(column = "roleDesc", property = "roleDesc"),
			@Result(column = "id", property = "permissions", javaType = List.class, many = @Many(select = "com.qf.ssm.dao.IPermission.findById", fetchType = FetchType.LAZY))

	})
	List<Role> findById(String id);

	@Select("select * from role")
	List<Role> findAll();
    
	@Insert("insert into role(id,roleName,roleDesc) values(#{id},#{roleName},#{roleDesc})")
	void addRole(Role role);
    
	@Select("select * from role where id =#{id}")
	Role findByRoleId(String id);
    
	
	@Select("select * from permission where id not in(select permissionId from role_permission where roleId =#{id})")
	List<Permission> findUserByIdAndAllRole(String id);
    @Insert("insert into role_permission(permissionId,roleId) values(#{id},#{roleId})") 
	void addRoleToPermission(@Param("id")String id,@Param("roleId") String roleId);
}
