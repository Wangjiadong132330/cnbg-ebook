package com.cnbg.zs.ebook.api.service;

import com.cnbg.zs.ebook.api.entity.Permission;
import com.cnbg.zs.ebook.api.entity.RoleUser;

import java.util.List;

/**
 * @author Faye.Wang
 * @version 1.0
 * @date 2021/3/31 10:52
 * @Description
 */
public interface IRoleUserService {
    /**
     * 查询用户当前的角色列表
     * @param userId
     * @return
     */
    List<RoleUser> getRoleUserForUser(Integer userId);

    /**
     * 查询用户访问权限URL
     * @param userId
     * @return
     */
    List<Permission> getPermissionForUser(Integer userId);
}
