package com.qf.ssm.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qf.ssm.domain.Product;
import com.qf.ssm.service.IProductService;

@RequestMapping("/product")
@Controller
public class ProductController {
	
    @Autowired
	private IProductService service ;
    
    
    @RequestMapping("/findAll")
    public ModelAndView findAllProduct() {
    	System.out.print("123");
	    List<Product> lists = service.findAll();
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("productList", lists);
	    mav.setViewName("product-list");
	    return mav;
    }
    
    @RequestMapping("/save")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String addProduct(Product product) {
    	service.add(product);
    	return "redirect:/product/findAll";
    }
    
    
    @RequestMapping("/findById")
    @PreAuthorize("authentication.principal.username=='liuxiaofu'")
    public ModelAndView findById(@RequestParam(name="id",required=true)String id ) {
    	ModelAndView mav =new ModelAndView();
    	Product product = service.findById(id);
    	mav.addObject("product",product);
    	mav.setViewName("product-update");
    	return mav;
    }
    
    @RequestMapping("/update")
    public String update(Product product) {
    	service.update(product);
    	
    	return"redirect:/product/findAll";
    }
	
}
