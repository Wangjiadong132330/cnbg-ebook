package com.cnbg.zs.ebook.core.log;


import com.cnbg.zs.ebook.common.lang.JsonUtils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author Faye.Wang
* @version 1.0
* @date 2020/9/6 10:35
* @Description 统一操作日志，记录所有请求请求参数
*/
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);


    @Pointcut("execution(public * com.cnbg.zs.ebook.api.controller..*.*(..))")
    public void pointLog(){

    }
    @Before("pointLog()")
    public void doMethodBefore(JoinPoint joinPoint){
        logger.info("执行日志切面：{}","doMethodBefore--方法执行前调用");
        Object[] obj = joinPoint.getArgs();


        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;

        Method method = methodSignature.getMethod();

        String methodName = method.getName();
        String className = signature.getDeclaringTypeName();

        String[] params2 = methodSignature.getParameterNames();
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // ajax json对象
        String params = null;
        String reqURI = request.getRequestURI();

        String[] igUrl ={"api/login","captcha","logOut","importUser","exportUser"};

        List urlList =  Arrays.asList(igUrl).stream().filter(item->reqURI.contains(item)).collect(Collectors.toList());
        if(urlList.isEmpty()){
            if(request.getParameterMap().isEmpty()){
                params = JsonUtils.toJsonString(obj);
            }else{
                params = JsonUtils.toJsonString(request.getParameterMap());
            }
        }

        logger.info("-===============审计日志：路径:-={},参数-=:{},类-=:{},方法-=:{}",
        reqURI,params,className,methodName);

    }
}
