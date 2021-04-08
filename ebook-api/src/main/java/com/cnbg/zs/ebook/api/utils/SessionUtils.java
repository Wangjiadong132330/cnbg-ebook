package com.cnbg.zs.ebook.api.utils;

import com.cnbg.zs.ebook.api.entity.UserInfo;
import com.cnbg.zs.ebook.common.constant.Constants;
import com.cnbg.zs.ebook.common.lang.JsonUtils;
import com.cnbg.zs.ebook.common.redis.JRedisUtils;

/**
 * @author Faye.Wang
 * @version 1.0
 * @date 2021/4/8 10:15
 * @Description
 */
public class SessionUtils {
    /**
     * 获取用户登录名称
     * @param sessionId
     * @return
     */
    public static String getSessionUserName(String sessionId){
        UserInfo myUserDetails =  JsonUtils.toJsonBean(JRedisUtils.getKeyValue(Constants.USER_OAUTH_SESSION_ID+sessionId),UserInfo.class);
        return myUserDetails.getUsername();
    }

    /**
     * 获取用户登录ID
     * @param sessionId
     * @return
     */
    public static Integer getSessionUserId(String sessionId){
        UserInfo myUserDetails =  JsonUtils.toJsonBean(JRedisUtils.getKeyValue(Constants.USER_OAUTH_SESSION_ID+sessionId),UserInfo.class);
        return myUserDetails.getId();
    }
}
