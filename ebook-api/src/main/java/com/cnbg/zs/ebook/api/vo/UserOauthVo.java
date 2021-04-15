package com.cnbg.zs.ebook.api.vo;

/**
 * @author Faye.Wang
 * @version 1.0
 * @date 2020/11/20 16:27
 * @Description
 */
public class UserOauthVo {
    private String name;
    private String[] roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
