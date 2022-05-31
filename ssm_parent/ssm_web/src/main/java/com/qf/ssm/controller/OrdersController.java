package com.qf.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.qf.ssm.domain.Orders;
import com.qf.ssm.service.IOrdersService;

@Controller
@RequestMapping("/orders")
public class OrdersController {
     @Autowired
	private IOrdersService orderService;
	   @RequestMapping("/findAll")
      public ModelAndView findAll(@RequestParam(name="page",required=true,defaultValue="1")int pageNum ,@RequestParam(name="pageSize",required=true,defaultValue="1")int pageSize) {
		   ModelAndView mav =new ModelAndView();
		   List<Orders> orderList = orderService.findAll(pageNum,pageSize);
		   PageInfo pageInfo =new PageInfo(orderList);
		   mav.addObject("pageInfo",pageInfo);
		   mav.setViewName("orders-list");
		   return mav;
	   }
	   
	   @RequestMapping("/findById")
	   public ModelAndView findById(@RequestParam(name="id",required=true)String id) {
		  Orders findById = orderService.findById(id);
		  ModelAndView mav =new ModelAndView();
		  mav.addObject("orders",findById);
		  mav.setViewName("orders-show");
		   
		   return mav;
	   }
	
}
