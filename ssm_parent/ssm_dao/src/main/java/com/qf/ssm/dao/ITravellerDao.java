package com.qf.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.qf.ssm.domain.Traveller;

@Repository
public interface ITravellerDao {

	
	@Select("select * from traveller where id in(select travellerId from order_traveller where orderId =#{id})")
	List<Traveller> findById(String id);
}
