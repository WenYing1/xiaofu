package com.qf.ssm.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qf.ssm.domain.Role;
import com.qf.ssm.domain.UserInfo;
import com.qf.ssm.service.IRoleService;
import com.qf.ssm.service.IUserService;

@RequestMapping("/user")
@Controller
public class UserController {

	@Autowired
	private IUserService   userService;
	@Autowired
	private	IRoleService roleService;
	
	@RequestMapping("/findAll")
	@RolesAllowed("USER")
	public  ModelAndView findAll() {
      List<UserInfo> list = userService.findAll();
      ModelAndView mav =new ModelAndView();
      mav.addObject("userList",list);
      mav.setViewName("user-list");
      return mav ;
	}
	
	@RequestMapping("/save")
	public String addUser(UserInfo user) {
		userService.insert(user);
		
		return "redirect:/user/findAll";
	}
	
	@RequestMapping("/findById")
	public ModelAndView findUserById(String id) {
		ModelAndView mav = new ModelAndView();
	UserInfo user =	userService.findById(id);
	mav.addObject("user",user);
	mav.setViewName("user-show");
		return mav;
	}
	
	@RequestMapping("/findUserByIdAndAllRole")
	public ModelAndView findAllRole(@RequestParam(name="id",required=true,defaultValue="1")String id) {
		UserInfo userInfo = userService.findById(id);
		 ModelAndView mav = new ModelAndView();
	List<Role> roleList=userService.findUserByIdAndAllRole( id);
	mav.addObject("user", userInfo);
	mav.addObject("roleList",roleList);
	mav.setViewName("user-role-add");
	return mav;	
	}
	@RequestMapping("/addRoleToUser")
	public String addRoleToUser(@RequestParam(name="ids")String[] ids ,@RequestParam(name="userId")String userId) {
		System.out.println(userId);
		 for (String id : ids) {
			userService.addRole(id,userId);
		}
	
		 
		return "redirect:/user/findAll";
	}
}
