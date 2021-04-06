package com.cnbg.zs.ebook.core.config;

import com.cnbg.zs.ebook.common.redis.JRedisUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
* @author Faye.Wang
* @version 1.0
* @date 2020/9/11 23:24
* @Description
*/
@Configuration
public class RedisConfig implements InitializingBean {
    @Value("${redis.address}")
    private String redisAddress;

    @Override
    public void afterPropertiesSet() throws Exception {
        JRedisUtils.init(redisAddress);
    }
}
