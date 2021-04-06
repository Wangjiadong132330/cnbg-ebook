package com.cnbg.zs.ebook.api.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
* @author Faye.Wang
* @version 1.0
* @date 2021/3/24 21:39
* @Description
*/
public class MyUserDetails implements UserDetails, Serializable {

	private static final long serialVersionUID = -3588397606431079893L;
	private Integer id;
	private String username;
	private String password;
	private String token;
	private String sessionId;

	private Set<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}
	// 账号是否过期
	@Override
	public boolean isAccountNonExpired() {
		return false;
	}
	// 账号是否锁定
	@Override
	public boolean isAccountNonLocked() {
		return false;
	}
	// 账号凭证是否未过期
	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Set<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
