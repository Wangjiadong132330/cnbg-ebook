package com.cnbg.zs.ebook.core.annotation;

import java.lang.annotation.*;

/**
* @author Faye.Wang
* @version 1.0
* @date 2020/9/5 21:29
* @Description 忽略JWT 验证请求,Controller方法增加此注解后，当前请求
* 则不需要token 认证
*/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JwtIgnore {
}
