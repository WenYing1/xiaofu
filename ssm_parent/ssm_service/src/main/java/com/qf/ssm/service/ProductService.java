package com.qf.ssm.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qf.ssm.dao.IProductDao;
import com.qf.ssm.domain.Product;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class ProductService implements IProductService {

	@Autowired
	private IProductDao productDao;

	public List<Product> findAll() {
		List<Product> list = productDao.findAll();
		return list;
	}

	public void add(Product product) {
		String string = UUID.randomUUID().toString();
		product.setId(string);
		productDao.addProduct(product);

	}
	
	public void update(Product product) {
		productDao.updateProduct(product);
		
	}

	public Product findById(String id) {
		Product findById = productDao.findById(id);
		return findById;
	}

}
