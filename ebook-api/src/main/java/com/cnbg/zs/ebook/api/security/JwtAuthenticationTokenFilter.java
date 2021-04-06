package com.cnbg.zs.ebook.api.security;

import com.cnbg.zs.ebook.api.entity.UserInfo;
import com.cnbg.zs.ebook.common.constant.Constants;
import com.cnbg.zs.ebook.common.jwt.JWTUtils;
import com.cnbg.zs.ebook.common.lang.JsonUtils;
import com.cnbg.zs.ebook.common.redis.JRedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @author OFG
* @version 1.0
* @date 2021/3/29 13:26
* @Description JWT校验
*/
@Component
public class JwtAuthenticationTokenFilter  extends OncePerRequestFilter {
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(JWTUtils.AUTH_HEADER_KEY);

        if(StringUtils.isNotBlank(token)){
                if(JWTUtils.verify(token)==null){
                throw new AccessDeniedException("TOKEN已过期，请重新登录！");
            }
            String sessionId =  JWTUtils.getTokenObjectValue(token,JWTUtils.SESSION_ID_KEY);
            UserInfo myUserDetails =  JsonUtils.toJsonBean(JRedisUtils.getKeyValue(Constants.USER_OAUTH_SESSION_ID+sessionId),UserInfo.class);
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(myUserDetails.getUsername());
            if (userDetails != null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
            filterChain.doFilter(request, response);
    }
}
