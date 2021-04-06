package com.cnbg.zs.ebook.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cnbg.zs.ebook.api.entity.UserInfo;
import com.cnbg.zs.ebook.api.mapper.UserInfoMapper;
import com.cnbg.zs.ebook.api.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Faye.Wang
 * @version 1.0
 * @date 2021/3/31 10:26
 * @Description
 */
@Service
public class IUserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo loadUserInfoByName(String userName) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userName);
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        return userInfo;
    }

    @Override
    public void insertUser(UserInfo userInfo) {
        userInfoMapper.insert(userInfo);
    }
}
