package com.cnbg.zs.ebook.api.dto;

/**
 * @author Faye.Wang
 * @version 1.0
 * @date 2021/4/9 16:57
 * @Description
 */
public class RoleUserDTO {
    private Integer id;
    private String userRealName;
    private String roleName;

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
}
