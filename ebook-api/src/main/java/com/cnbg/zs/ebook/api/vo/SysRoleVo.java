package com.cnbg.zs.ebook.api.vo;

import com.cnbg.zs.ebook.core.result.RequestVo;
import java.util.*;
/**
* @author Faye.Wang
* @version 1.0
* @date 2021/1/6 14:44
* @Description
*/
public class SysRoleVo extends RequestVo {
	/**
	* 字段：id ：
	*/

	private Integer id;
	/**
	* 字段：roleName ：角色名称
	*/

	private String roleName;
	/**
	* 字段：createTime ：创建时间
	*/

	private Date createTime;
	/**
	* 字段：updateTime ：修改时间
	*/

	private Date updateTime;
	/**
	* 字段：roleId ：
	*/

	private Integer roleId;
	/**
	* 字段：permissionId ：
	*/

	private Integer[] permissionIds;
	/**
	* 字段：nodeId ：节点ID
	*/

	private Integer[] nodeIds;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer[] getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(Integer[] permissionIds) {
		this.permissionIds = permissionIds;
	}

	public Integer[] getNodeIds() {
		return nodeIds;
	}

	public void setNodeIds(Integer[] nodeIds) {
		this.nodeIds = nodeIds;
	}
}
