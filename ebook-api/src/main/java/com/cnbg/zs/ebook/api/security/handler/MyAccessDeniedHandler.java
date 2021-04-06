package com.cnbg.zs.ebook.api.security.handler;

import com.cnbg.zs.ebook.api.security.SecurityEnum;
import com.cnbg.zs.ebook.common.lang.JsonUtils;
import com.cnbg.zs.ebook.core.result.ResultData;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author OFG
 * @version 1.0
 * @date 2021/3/24 21:33
 * @Description 无权访问
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResultData resultData = new ResultData();
        resultData.setCode(SecurityEnum.USER_NO_ACCESS.getCode());
        resultData.setMessage(SecurityEnum.USER_NO_ACCESS.getMessage());
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.getWriter().write(JsonUtils.toJsonString(resultData));
    }
}
