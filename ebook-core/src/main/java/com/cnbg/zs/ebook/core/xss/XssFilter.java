package com.cnbg.zs.ebook.core.xss;

import com.cnbg.zs.ebook.core.wrapper.XssHttpServletRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class XssFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(XssFilter.class);
    private static boolean IS_INCLUDE_RICH_TEXT = false;//是否过滤富文本内容

    public List<String> excludes = new ArrayList<String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if(logger.isDebugEnabled()){
            logger.debug("xss config is open");
        }

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if(handleExcludeURL(req, resp)){
            filterChain.doFilter(request, response);
            return;
        }

        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
        filterChain.doFilter(xssRequest, response);

    }
    private boolean handleExcludeURL(HttpServletRequest request, HttpServletResponse response) {

        if (excludes == null || excludes.isEmpty()) {
            return false;
        }

        String url = request.getServletPath();
        for (String pattern : excludes) {
            Pattern p = Pattern.compile("^" + pattern);
            Matcher m = p.matcher(url);
            if (m.find()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void destroy() {

    }
}