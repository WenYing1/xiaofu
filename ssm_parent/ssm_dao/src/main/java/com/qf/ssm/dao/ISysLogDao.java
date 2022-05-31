package com.qf.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.qf.ssm.domain.SysLog;

@Repository
public interface ISysLogDao {
    
	
	@Insert("insert into syslog(id,visitTime,username,ip,url,executionTime,method) values(#{id},#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
	void insert(SysLog sysLog);
    
	@Select("select * from syslog")
	List<SysLog> findAll();

}
