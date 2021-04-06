package com.cnbg.zs.ebook.api.service;

import com.cnbg.zs.ebook.api.entity.UserInfo;

/**
 * @author Faye.Wang
 * @version 1.0
 * @date 2021/3/31 10:15
 * @Description
 */
public interface IUserInfoService {
    /**
     * 根据用户名称获取 用户信息
     * @param userName
     * @return
     */
    UserInfo loadUserInfoByName(String userName);

    void insertUser(UserInfo userInfo);
}
