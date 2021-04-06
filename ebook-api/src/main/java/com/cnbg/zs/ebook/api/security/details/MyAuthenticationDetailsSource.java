package com.cnbg.zs.ebook.api.security.details;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Faye.Wang
 * @version 1.0
 * @date 2021/4/4 16:27
 * @Description
 */
@Component
public class MyAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {
    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest request) {
        try {
            return new MyWebAuthenticationDetails(request);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
