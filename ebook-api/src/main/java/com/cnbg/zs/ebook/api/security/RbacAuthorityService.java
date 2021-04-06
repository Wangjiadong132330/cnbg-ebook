package com.cnbg.zs.ebook.api.security;

import com.cnbg.zs.ebook.api.entity.Permission;
import com.cnbg.zs.ebook.api.service.IRoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
* @author Faye.Wang
* @version 1.0
* @date 2021/3/31 16:05
* @Description
*/
@Component("rbacauthorityservice")
public class RbacAuthorityService {

	@Autowired
	private IRoleUserService roleUserService;


	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		Object userInfo = authentication.getPrincipal();
		boolean hasPermission  = false;
		if (userInfo instanceof UserDetails) {
			MyUserDetails principal = (MyUserDetails) authentication.getPrincipal();
			//获取资源
			Set<String> urls = new HashSet();

			List<Permission> permissionList = roleUserService.getPermissionForUser(principal.getId());
			permissionList.forEach(item->{
				urls.add(item.getRouterUrl());
			});
			AntPathMatcher antPathMatcher = new AntPathMatcher();
			for (String url : urls) {
				if (antPathMatcher.match(url, request.getRequestURI())) {
					hasPermission = true;
					break;
				}
			}
		}
			return hasPermission;
	}
}