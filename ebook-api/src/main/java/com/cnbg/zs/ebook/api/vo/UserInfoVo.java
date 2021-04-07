package com.cnbg.zs.ebook.api.vo;

import com.cnbg.zs.ebook.core.result.RequestVo;
import java.util.*;
/**
* @author Faye.Wang
* @version 1.0
* @date 2021/1/6 14:44
* @Description
*/
public class UserInfoVo extends RequestVo {
	/**
	* 字段：id ：主键id
	*/

	private Integer id;
	/**
	* 字段：userAccount ：用户账号
	*/

	private String userAccount;
	/**
	* 字段：userRealName ：用户姓名
	*/

	private String userRealName;
	/**
	* 字段：userPass ：密码
	*/

	private String userPass;
	/**
	* 字段：companyId ：公司id
	*/

	private Integer companyId;
	/**
	* 字段：departmentId ：部门id
	*/

	private Integer departmentId;
	/**
	* 字段：gender ：性别
	*/

	private String gender;
	/**
	* 字段：phone ：电话
	*/

	private String phone;
	/**
	* 字段：createTime ：创建时间
	*/

	private Date createTime;
	/**
	* 字段：createUser ：创建人
	*/

	private String createUser;
	/**
	* 字段：status ：状态(1-正常,2-冻结)
	*/

	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
