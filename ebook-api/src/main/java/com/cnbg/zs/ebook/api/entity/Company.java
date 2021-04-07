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
@TableName(value = "t_company")
public class Company implements Serializable{

	/**
	* 字段：id ：
	*/
	@TableId(type = IdType.AUTO)
	private Integer id;

	/**
	* 字段：provincialId ：所属区域id
	*/
	private Integer provincialId;

	/**
	* 字段：companyName ：公司名称
	*/
	private String companyName;

	/**
	* 字段：companyShortName ：公司简称
	*/
	private String companyShortName;

	/**
	* 字段：createTime ：创建时间
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
	public Integer getProvincialId() {
		return provincialId;
	}

	public void setProvincialId(Integer provincialId) {
		this.provincialId = provincialId;
	}
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyShortName() {
		return companyShortName;
	}

	public void setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
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
