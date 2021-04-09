package com.cnbg.zs.ebook.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;

import java.io.Serializable;
import java.util.*;


/**
* @author Faye.Wang
* @version 1.0
* @date 2021/1/6 14:54
* @Description
*/
@TableName(value = "t_process", autoResultMap = true)
public class Process implements Serializable{

	/**
	* 字段：id ：主键
	*/
	@TableId(type = IdType.AUTO)
	private Integer id;

	/**
	* 字段：processName ：流程名
	*/
	private String processName;

	/**
	* 字段：processChart ：流程图名
	*/
	private String processChart;

	/**
	* 字段：processJson ：流程图json数据
	*/
	private String processJson;

	/**
	* 字段：createTime ：创建时间
	*/
	private Date createTime;

	/**
	* 字段：createUser ：创建人
	*/
	private String createUser;

	/**
	* 字段：updateTime ：更新时间
	*/
	private Date updateTime;

	/**
	* 字段：updateUser ：更新者
	*/
	private String updateUser;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getProcessChart() {
		return processChart;
	}

	public void setProcessChart(String processChart) {
		this.processChart = processChart;
	}
	public String getProcessJson() {
		return processJson;
	}

	public void setProcessJson(String processJson) {
		this.processJson = processJson;
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
