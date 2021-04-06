package com.cnbg.zs.ebook.api.security.config;

import com.cnbg.zs.ebook.api.security.*;
import com.cnbg.zs.ebook.api.security.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
* @author Faye.Wang
* @version 1.0
* @date 2021/3/25 20:41
* @Description
*/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MyAuthenticationEntryPoint myAuthenticationEntryPoint;
	@Autowired
	MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
	@Autowired
	MyAuthenticationFailureHandler myAuthenticationFailureHandler;
	@Autowired
	MyLogoutSuccessHandler myLogoutSuccessHandler;
	@Autowired
	MyAccessDeniedHandler myAccessDeniedHandler;
	@Autowired
	MyUserDetailsService myUserDetailsService;
	@Autowired
	LoginAuthenticationProvider loginAuthenticationProvider;
	@Autowired
	JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
	@Autowired
	private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;
	private static String ignoredUrl;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(loginAuthenticationProvider);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		web.ignoring().antMatchers(ignoredUrl.split(","));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.httpBasic().authenticationEntryPoint(myAuthenticationEntryPoint)
				.and()
				.authorizeRequests()
				.antMatchers(ignoredUrl.split(","))
				.authenticated()
				.anyRequest().access("@rbacauthorityservice.hasPermission(request,authentication)")
				.and()
				.formLogin().loginProcessingUrl("/api/login")
				.successHandler(myAuthenticationSuccessHandler)
				.failureHandler(myAuthenticationFailureHandler)
				.authenticationDetailsSource(authenticationDetailsSource)
				.and()
				.logout().logoutUrl("/api/logOut")
				.logoutSuccessHandler(myLogoutSuccessHandler).permitAll();

		http.rememberMe().rememberMeParameter("remember-me").userDetailsService(myUserDetailsService).tokenValiditySeconds(1000);
		http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);
		http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}

	public static String getIgnoredUrl() {
		return ignoredUrl;
	}

	@Value("${ignored.urls}")
	public  void setIgnoredUrl(String ignoredUrl) {
		this.ignoredUrl = ignoredUrl;
	}
	/**
	* BCrypt加密
	* @return
	*/
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
