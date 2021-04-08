package com.cnbg.zs.ebook.api.dto;
import java.util.*;

/**
* @author OFG
* @version 1.0
* @date 2021/1/6 14:44
* @Description
*/
public class SysRoleDTO {
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

	private Integer permissionId;
	/**
	* 字段：nodeId ：节点ID
	*/

	private Integer nodeId;

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
	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}
}
