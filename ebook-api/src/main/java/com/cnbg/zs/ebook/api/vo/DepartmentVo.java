package com.cnbg.zs.ebook.api.vo;

import com.cnbg.zs.ebook.core.result.RequestVo;
import java.util.*;
/**
* @author Faye.Wang
* @version 1.0
* @date 2021/1/6 14:44
* @Description
*/
public class DepartmentVo extends RequestVo {
	/**
	* 字段：id ：
	*/

	private Integer id;
	/**
	* 字段：companyId ：公司id
	*/

	private Integer companyId;
	/**
	* 字段：departmentName ：部门名称
	*/

	private String departmentName;
	/**
	* 字段：departmentShortName ：部门简称
	*/

	private String departmentShortName;
	/**
	* 字段：createTime ：
	*/

	private Date createTime;
	/**
	* 字段：createUser ：创建人
	*/

	private String createUser;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentShortName() {
		return departmentShortName;
	}

	public void setDepartmentShortName(String departmentShortName) {
		this.departmentShortName = departmentShortName;
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
}
