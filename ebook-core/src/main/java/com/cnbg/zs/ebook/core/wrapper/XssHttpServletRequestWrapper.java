package com.cnbg.zs.ebook.core.wrapper;



import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import java.util.Enumeration;


public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    HttpServletRequest orgRequest = null;
    private boolean isIncludeRichText = false;



    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        orgRequest = request;
        this.isIncludeRichText = isIncludeRichText;
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (StringUtils.isNotBlank(value)) {
            value =  Jsoup.clean(value, Whitelist.relaxed());
        }
        return value;
    }
    /**
    * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/>
    * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
    * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
    */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if (StringUtils.isNotBlank(value)) {
            value = Jsoup.clean(value, Whitelist.relaxed());
        }
        return value;
    }

    @Override
    public Enumeration<String> getParameterNames() {
            return super.getParameterNames();
     }

    @Override
    public String[] getParameterValues(String name) {
        String[] arr = super.getParameterValues(name);
        if(arr != null){
            for (int i=0;i<arr.length;i++) {
                arr[i] = Jsoup.clean(arr[i], Whitelist.relaxed());
            }
        }
        return arr;
    }
}
