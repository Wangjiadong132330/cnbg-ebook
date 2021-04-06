package com.cnbg.zs.ebook.api.security.handler;

import com.cnbg.zs.ebook.api.security.SecurityEnum;
import com.cnbg.zs.ebook.common.lang.JsonUtils;
import com.cnbg.zs.ebook.core.result.ResultData;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Faye.Wang
 * @version 1.0
 * @date 2021/3/24 21:36
 * @Description 成功退出登录
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ResultData resultData = new ResultData();
        resultData.setCode(SecurityEnum.USER_LOGOUT_SUCCESS.getCode());
        resultData.setMessage(SecurityEnum.USER_LOGOUT_SUCCESS.getMessage());
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.getWriter().write(JsonUtils.toJsonString(resultData));
    }
}
