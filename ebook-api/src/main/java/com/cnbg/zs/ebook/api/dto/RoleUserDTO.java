package com.cnbg.zs.ebook.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Faye.Wang
 * @version 1.0
 * @date 2021/4/9 16:57
 * @Description
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleUserDTO {
    private Integer id;
    private String userRealName;
    private String roleName;
    private String userAccount;
    private String roleId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
}
