package com.qf.ssm.dao;



import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.qf.ssm.domain.Role;
import com.qf.ssm.domain.UserInfo;

@Repository
public interface IUserInfoDao {
      
	@Select("select * from users where username =#{username}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="email",property="email"),
		@Result(column="username",property="username"),
		@Result(column="password",property="password"),
		@Result(column="phoneNum",property="phoneNum"),
		@Result(column="status",property="status"),
		@Result(column="id",property="roles",javaType=List.class,many=@Many(select="com.qf.ssm.dao.IRoleDao.findById",fetchType=FetchType.LAZY )),
		
	})
	UserInfo findByName(String username);
    
	
	
	@Select("select * from users")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="email",property="email"),
		@Result(column="username",property="username"),
		@Result(column="password",property="password"),
		@Result(column="phoneNum",property="phoneNum"),
		@Result(column="status",property="status")
	})
	List<UserInfo> findAll();

    
    @Insert("insert into users(id,username,password,email,phoneNum,status) values(#{id},#{username},#{password},#{email},#{phoneNum},#{status})")
	void add(UserInfo user);

 
    @Select("select * from users where id =#{id}")
    @Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="email",property="email"),
		@Result(column="username",property="username"),
		@Result(column="password",property="password"),
		@Result(column="phoneNum",property="phoneNum"),
		@Result(column="status",property="status"),
		@Result(column="id",property="roles",javaType=List.class,many=@Many(select="com.qf.ssm.dao.IRoleDao.findById",fetchType=FetchType.LAZY )),
		
	})
	UserInfo findById(String id);


    @Select("select * from role where id not in(select roleId from users_role where userId =#{id})")
	List<Role> findUserByIdAndAllRole(String id);


    @Insert("insert into users_role(userId,roleId) values(#{userId},#{id})")
	void addRole(@Param("id")String id,@Param("userId") String userId);


    
}
