package com.cnbg.zs.ebook.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cnbg.zs.ebook.api.entity.Permission;

import java.util.List;

/**
 * @author Faye.Wang
 * @version 1.0
 * @date 2021/3/31 16:50
 * @Description
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 查询用户访问权限URL
     * @param userId
     * @return
     */
    List<Permission> getPermissionByUserId(Integer userId);
}
