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
@TableName(value = "t_sys_role")
public class SysRole implements Serializable{

	/**
	* 字段：id ：
	*/
	@TableId(type = IdType.AUTO)
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

}
