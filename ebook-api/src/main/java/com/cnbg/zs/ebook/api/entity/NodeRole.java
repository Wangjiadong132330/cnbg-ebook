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
@TableName(value = "t_node_role")
public class NodeRole implements Serializable{

	/**
	* 字段：id ：
	*/
	@TableId(type = IdType.AUTO)
	private Integer id;

	/**
	* 字段：nodeId ：节点ID
	*/
	private Integer nodeId;

	/**
	* 字段：roleId ：角色权限ID
	*/
	private Integer roleId;

	/**
	* 字段：createTime ：
	*/
	private Date createTime;

	/**
	* 字段：createUser ：
	*/
	private String createUser;

	/**
	* 字段：updateTime ：
	*/
	private Date updateTime;

	/**
	* 字段：updateUser ：
	*/
	private String updateUser;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}
