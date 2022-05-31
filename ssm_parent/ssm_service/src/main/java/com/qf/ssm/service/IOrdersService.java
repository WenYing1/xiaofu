package com.qf.ssm.service;

import java.util.List;

import com.qf.ssm.domain.Orders;

public interface IOrdersService {
	
	List<Orders> findAll(int page ,int pageSize);
	Orders findById(String id);

}
