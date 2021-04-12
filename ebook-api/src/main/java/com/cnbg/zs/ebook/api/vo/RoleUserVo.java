package com.cnbg.zs.ebook.api.vo;

import com.cnbg.zs.ebook.core.result.RequestVo;

/**
 * @author Faye.Wang
 * @version 1.0
 * @date 2021/4/9 14:32
 * @Description
 */
public class RoleUserVo extends RequestVo {
    private Integer id;
    private Integer userId;
    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
