package com.qf.ssm.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.qf.ssm.dao.IUserInfoDao;
import com.qf.ssm.domain.Role;
import com.qf.ssm.domain.UserInfo;

@Service("userService")
public class UserService implements UserDetailsService {
	@Autowired
	private IUserInfoDao userInfoDao;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = userInfoDao.findByName(username);
		System.out.println(userInfo);
		User user = null;
		if (userInfo != null) {
			user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==1?true:false,true,true,true, getGrantedAuthority(userInfo.getRoles()));
		} else {
			throw new RuntimeException("用户不存在");
		}
		return user;
	}

	private Collection<? extends GrantedAuthority> getGrantedAuthority(List<Role> roles) {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		for (Role role : roles) {
			list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
		}
		return list;
	}

}
