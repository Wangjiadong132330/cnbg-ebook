package com.cnbg.zs.ebook.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.*;


/**
* @author Faye.Wang
* @version 1.0
* @date 2021/1/6 14:54
* @Description
*/
@TableName(value = "t_sys_role_permission")
public class SysRolePermission implements Serializable{

	/**
	* 字段：id ：
	*/
	@TableId(type = IdType.AUTO)
	private Integer id;

	/**
	* 字段：roleId ：
	*/
	private Integer roleId;

	/**
	* 字段：permissionId ：
	*/
	private Integer permissionId;

	/**
	* 字段：createTime ：
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
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
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
