package com.cnbg.zs.ebook.api.security;


import com.cnbg.zs.ebook.api.entity.RoleUser;
import com.cnbg.zs.ebook.api.entity.UserInfo;
import com.cnbg.zs.ebook.api.security.details.MyWebAuthenticationDetails;
import com.cnbg.zs.ebook.api.service.IRoleUserService;
import com.cnbg.zs.ebook.api.service.IUserInfoService;
import com.cnbg.zs.ebook.common.lang.JsonUtils;
import com.cnbg.zs.ebook.common.redis.JRedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
* @author Faye.Wang
* @version 1.0
* @date 2021/4/1 9:57
* @Description
*/
@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private IUserInfoService iUserInfoService;
	@Autowired
	private IRoleUserService iRoleUserService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String userName = authentication.getName();
//        String rawPassword = (String) authentication.getCredentials();

		MyWebAuthenticationDetails details = (MyWebAuthenticationDetails) authentication.getDetails();
		String userName = details.getUsername();
		String rawPassword = details.getPassword();
//		String serverCode =  JRedisUtils.getKeyValue(details.getKey());
		// TODO 临时程序开发
		String serverCode =  "1234";
		if(!StringUtils.equals(serverCode,details.getCode())){
		throw new DisabledException("验证码输入错误");
		}

		UserInfo userInfo = iUserInfoService.loadUserInfoByName(userName);
		if(userInfo == null){
			throw  new UsernameNotFoundException("用户密码/密码错误");
		}
		MyUserDetails myUserDetails = new MyUserDetails();
		BeanUtils.copyProperties(userInfo,myUserDetails);

		if (myUserDetails.isEnabled()) {
			throw new DisabledException("该账户已被禁用，请联系管理员");

		} else if (myUserDetails.isAccountNonLocked()) {
			throw new LockedException("该账号已被锁定");

		} else if (myUserDetails.isAccountNonExpired()) {
			throw new AccountExpiredException("该账号已过期，请联系管理员");

		} else if (myUserDetails.isCredentialsNonExpired()) {
			throw new CredentialsExpiredException("该账户的登录凭证已过期，请重新登录");
		}
		//验证密码
		if (!passwordEncoder.matches(rawPassword, myUserDetails.getPassword())) {
			throw new BadCredentialsException("用户密码/密码错误!");
		}
	// 获取该用户拥有的权限
		List<RoleUser> roleUsers = iRoleUserService.getRoleUserForUser(userInfo.getId());
		JRedisUtils.setKeyValue("SESSION:ROLE:"+String.valueOf(userInfo.getId()), JsonUtils.toJsonString(roleUsers));
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
			for(RoleUser roleUser : roleUsers){
			authorities.add(new SimpleGrantedAuthority("ROLE_"+roleUser.getRoleId()));
		}
		myUserDetails.setAuthorities(authorities);
		return new UsernamePasswordAuthenticationToken(myUserDetails, rawPassword, myUserDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
