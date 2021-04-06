package com.cnbg.zs.ebook.api.security.handler;

import com.cnbg.zs.ebook.api.security.SecurityEnum;
import com.cnbg.zs.ebook.common.lang.JsonUtils;
import com.cnbg.zs.ebook.core.result.ResultData;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author OFG
 * @version 1.0
 * @date 2021/3/24 21:34
 * @Description 用户登录失败时返回给前端的数据
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResultData resultData = new ResultData();
        resultData.setCode(SecurityEnum.USER_LOGIN_FAILED.getCode());
        resultData.setMessage(e.getMessage());
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.getWriter().write(JsonUtils.toJsonString(resultData));
    }
}
