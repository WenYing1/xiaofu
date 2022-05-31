package com.qf.ssm.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.qf.ssm.domain.Member;

@Repository
public interface IMemberDao {

	
	@Select("select * from member where id =#{id}")
	Member findById (String id);
}
