package com.qf.ssm.service;

import java.util.List;

import com.qf.ssm.domain.Product;

public interface IProductService {

	List<Product> findAll();
	void add(Product product);
	void update(Product product);
	Product findById(String id);
	
}
