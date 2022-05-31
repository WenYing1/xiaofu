package com.qf.ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.qf.ssm.dao.IOrdersDao;
import com.qf.ssm.domain.Orders;

@Service
@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
public class OrderService implements IOrdersService {
	@Autowired
	private IOrdersDao ordersDao ;

	public List<Orders> findAll(int pageNum ,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Orders> all = ordersDao.findAll();
		return all;
	}

	public Orders findById(String id) {
		Orders findById = ordersDao.findById(id);
		return findById;
	}

}
