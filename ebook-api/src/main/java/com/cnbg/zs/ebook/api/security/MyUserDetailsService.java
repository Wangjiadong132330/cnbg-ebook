package com.cnbg.zs.ebook.api.security;


import com.cnbg.zs.ebook.api.entity.RoleUser;
import com.cnbg.zs.ebook.api.entity.UserInfo;


import com.cnbg.zs.ebook.api.service.IUserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
* @author OFG
* @version 1.0
* @date 2021/3/24 21:59
* @Description 用户认证、授权
*/
@Component
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private IUserInfoService iUserInfoService;
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		UserInfo userInfo = iUserInfoService.loadUserInfoByName(userName);
		if(userInfo == null){
			throw  new UsernameNotFoundException("用户密码/密码错误");
		}
		MyUserDetails myUserDetails = new MyUserDetails();
		BeanUtils.copyProperties(userInfo,myUserDetails);
		return myUserDetails;
	}
}
