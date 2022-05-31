package com.qf.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.qf.ssm.domain.Product;

@Repository
public interface IProductDao {

	@Select("select * from product")
	List<Product> findAll();
	
	@Insert("insert into product(id,productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{id},#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
	 void addProduct(Product product);
	
	@Select("select * from product where id=#{productId}")
	Product findById(String productId);
	
	@Update("update product set productName=#{productName},cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus} where id =#{id}")
	void updateProduct(Product product);
}
