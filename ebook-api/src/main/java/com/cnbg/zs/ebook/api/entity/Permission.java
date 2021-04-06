package com.cnbg.zs.ebook.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author Faye.Wang
 * @version 1.0
 * @date 2021/3/31 16:25
 * @Description
 */
@TableName(value = "t_sys_permission")
public class Permission {
    /**
     * 字段：id ：
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 字段：menuName ：
     */
    private String menuName;
    /**
     * 字段：routerUrl ：
     */
    private String routerUrl;
    /**
     * 字段：parentId ：
     */
    private Integer parentId;
    /**
     * 字段：creatTime ：
     */
    private Date createTime;
    /**
     * 字段：updateTime ：
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getRouterUrl() {
        return routerUrl;
    }

    public void setRouterUrl(String routerUrl) {
        this.routerUrl = routerUrl;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
