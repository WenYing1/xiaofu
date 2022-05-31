package com.qf.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qf.ssm.domain.Permission;
import com.qf.ssm.service.IPermissionService;

@RequestMapping("/permission")
@Controller
public class PermissionController {
     @Autowired
	private IPermissionService permissionService;
	@RequestMapping("/findAll")
	public ModelAndView findAll() {
	List<Permission> permissions=permissionService.findAll();
	ModelAndView mav = new ModelAndView();
	mav.addObject("permissionList",permissions);
	mav.setViewName("permission-list");
	return mav;
	}
	@RequestMapping("/save")
	public String savePermission(Permission permission) {
		 permissionService.add(permission);
		return"redirect:/permission/findAll";
	}
}
