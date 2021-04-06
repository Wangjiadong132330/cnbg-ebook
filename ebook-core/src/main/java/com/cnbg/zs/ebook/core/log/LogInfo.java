package com.cnbg.zs.ebook.core.log;

/**
* @author Faye.Wang
* @version 1.0
* @date 2020/9/6 13:32
* @Description
*/
public class LogInfo {
    // 业务名称
    private String serviceName;
    // 业务类型
    private String serviceType;
    // 业务描述
    private String serviceDesc;
    // 请求参数
    private String reqParams;
    // 请求方法
    private String reqMethod;
    // 请求时间
    private String reqDate;
    // 请求URL
    private String reqURI;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public String getReqParams() {
        return reqParams;
    }

    public void setReqParams(String reqParams) {
        this.reqParams = reqParams;
    }

    public String getReqMethod() {
        return reqMethod;
    }

    public void setReqMethod(String reqMethod) {
        this.reqMethod = reqMethod;
    }

    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    public String getReqURI() {
        return reqURI;
    }

    public void setReqURI(String reqURI) {
        this.reqURI = reqURI;
    }
}
