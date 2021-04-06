package com.cnbg.zs.ebook.api.security.handler;

import com.cnbg.zs.ebook.api.entity.UserInfo;
import com.cnbg.zs.ebook.api.security.MyUserDetails;
import com.cnbg.zs.ebook.api.security.SecurityEnum;
import com.cnbg.zs.ebook.common.constant.Constants;
import com.cnbg.zs.ebook.common.jwt.JWTUtils;
import com.cnbg.zs.ebook.common.lang.JsonUtils;
import com.cnbg.zs.ebook.common.lang.StringToolUtils;
import com.cnbg.zs.ebook.common.redis.JRedisUtils;
import com.cnbg.zs.ebook.core.result.ResultData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author OFG
 * @version 1.0
 * @date 2021/3/24 21:35
 * @Description 用户登录成功时返回给前端的数据
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static long expireTime;

    private static int loginExpireTime;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        MyUserDetails  myUserDetails = (MyUserDetails) authentication.getPrincipal();
        Map<String,String> tokenMap = new HashMap<>();
        String sessionId = StringToolUtils.getGeneratorUUID();
        tokenMap.put("sessionId",sessionId);
        String jwtToken = JWTUtils.createSingleJwtToken(tokenMap,expireTime);
        myUserDetails.setToken(jwtToken);
        myUserDetails.setSessionId(sessionId);

        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(myUserDetails,userInfo);

        JRedisUtils.setKeyValue(Constants.USER_OAUTH_SESSION_ID+sessionId, JsonUtils.toJsonString(userInfo),loginExpireTime);

        ResultData resultData = new ResultData();
        resultData.setCode(SecurityEnum.USER_LOGIN_SUCCESS.getCode());
        resultData.setMessage(SecurityEnum.USER_LOGIN_SUCCESS.getMessage());
        resultData.setData(jwtToken);
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.getWriter().write(JsonUtils.toJsonString(resultData));
    }

    public  long getExpireTime() {
        return expireTime;
    }
    @Value("${jwt.expire.time}")
    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public  int getLoginExpireTime() {
        return loginExpireTime;
    }
    @Value("${user.login.redis.time}")
    public  void setLoginExpireTime(int loginExpireTime) {
        this.loginExpireTime = loginExpireTime;
    }
}
