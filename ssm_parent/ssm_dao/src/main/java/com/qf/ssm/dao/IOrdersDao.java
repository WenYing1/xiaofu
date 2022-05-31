package com.qf.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import com.qf.ssm.domain.Member;
import com.qf.ssm.domain.Orders;
import com.qf.ssm.domain.Product;

@Repository
public interface IOrdersDao {

	@Select("select * from orders")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column = "orderNum",property = "orderNum"),
		@Result(column = "orderTime",property = "orderTime"),
		@Result(column = "orderStatus",property = "orderStatus"),
		@Result(column = "peopleCount",property = "peopleCount"),
		@Result(column = "payType",property = "payType"),
		@Result(column = "orderDesc",property = "orderDesc"),
		@Result(column = "productId",property="product",javaType=Product.class,one=@One(select="com.qf.ssm.dao.IProductDao.findById",fetchType = FetchType.EAGER))
	})
	
	List<Orders> findAll();
	
	
	
	@Select("select * from orders where id =#{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column = "orderNum",property = "orderNum"),
		@Result(column = "orderTime",property = "orderTime"),
		@Result(column = "orderStatus",property = "orderStatus"),
		@Result(column = "peopleCount",property = "peopleCount"),
		@Result(column = "payType",property = "payType"),
		@Result(column = "orderDesc",property = "orderDesc"),
		@Result(column = "productId",property="product",javaType=Product.class,one=@One(select="com.qf.ssm.dao.IProductDao.findById",fetchType = FetchType.EAGER)),
		@Result(column = "memberId",property="member",javaType=Member.class,one=@One(select="com.qf.ssm.dao.IMemberDao.findById",fetchType = FetchType.EAGER)),
		@Result(column = "id",property="travellers",javaType=List.class,many=@Many(select="com.qf.ssm.dao.ITravellerDao.findById",fetchType = FetchType.LAZY))
		
	})
	Orders findById(String id);
}
