package com.cnbg.zs.ebook.api.security.details;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @author Faye.Wang
 * @version 1.0
 * @date 2021/4/4 16:21
 * @Description
 */
public class MyWebAuthenticationDetails extends WebAuthenticationDetails {
    private static final long serialVersionUID = 4429986738640447264L;
    private String key;
    private String code;
    private String username;
    private String password;

    public MyWebAuthenticationDetails(HttpServletRequest request) throws IOException {
        super(request);
        Map<String, String> map = new ObjectMapper().readValue(request.getInputStream(), Map.class);
//        key = request.getParameter("key");
//        code = request.getParameter("code");
        key = map.get("key");
        code = map.get("code");
        username = map.get("username");
        password = map.get("password");
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
