package com.qf.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qf.ssm.domain.Permission;
import com.qf.ssm.domain.Role;
import com.qf.ssm.service.IRoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private	IRoleService roleService;
	
	
	
	@RequestMapping("/findAll")
	@Secured("ROLE_USER")
	public ModelAndView findAll() {
	List<Role> roles =	roleService.findAll();
	ModelAndView mav = new ModelAndView();
	 mav.addObject("roleList",roles);
	 mav.setViewName("role-list");
	 return mav;
	}
	@RequestMapping("/save")
	public String addRole(Role role) {
		roleService.addRole(role);
		return "redirect:/role/findAll";
	}
	
	@RequestMapping("/findUserByIdAndAllRole")
	public ModelAndView findUserByIdAndAllRole(String id) {
		ModelAndView mav =new ModelAndView();
		Role role = roleService.findById(id);
	   List<Permission> permissions=roleService.findUserByIdAndAllRole(id);
	   mav.addObject("role",role);
	   mav.addObject("permissionList", permissions);
	   mav.setViewName("role-permission-add");
	   return mav;
	}
	@RequestMapping("/addRoleToPermission")
	public String addRoleToPermission(@RequestParam(name="ids")String[] ids, @RequestParam(name="roleId")String roleId) {
		for (String id : ids) {
			roleService.addRoleToPermission(id,roleId);
		}
		
		
		return"redirect:/role/findAll";
	}
}
