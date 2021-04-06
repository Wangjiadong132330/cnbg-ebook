package com.cnbg.zs.ebook.core.wrapper;

import com.cnbg.zs.ebook.common.lang.JsonUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

/**
* @author Faye.Wang
* @version 1.0
* @date 2020/11/3 10:54
* @Description
*/
public class HeaderHttpServletRequestWrapper extends HttpServletRequestWrapper {
    HttpServletRequest orgRequest = null;
    byte[] requestBody = new byte[0];
    private String sessionId;
    public HeaderHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        orgRequest = request;
    }
    @Override
    public String[] getParameterValues(String name) {
        if (name.equals("sessionId")) {
            return new String[] { this.getSessionId() };
        }
        return super.getParameterValues(name);
    }
    @Override
    public Enumeration<String> getParameterNames() {
        Set<String> paramNames = new LinkedHashSet<>();
            paramNames.add("sessionId");
            Enumeration<String> names =  super.getParameterNames();
                while(names.hasMoreElements()) {
                    paramNames.add(names.nextElement());
                }
            return Collections.enumeration(paramNames);
         }
        @Override
        public ServletInputStream getInputStream() throws IOException {
        try {
            requestBody = StreamUtils.copyToByteArray(orgRequest.getInputStream());
            Map<String,Object> map = new HashMap<>();
            if(requestBody.length !=0){
                map  = JsonUtils.toJsonBean(new String(requestBody),Map.class);
            }
            map.put("sessionId",this.getSessionId());
            requestBody = JsonUtils.toJsonString(map).getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }


        final ByteArrayInputStream byteArrayInputStream  = new ByteArrayInputStream(requestBody);
        ServletInputStream servletInputStream = new ServletInputStream() {
        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }

        @Override
        public int read() throws IOException {
            return byteArrayInputStream.read();
        }
     };
        return servletInputStream;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
