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
@TableName(value = "t_node")
public class Node implements Serializable{

	/**
	* 字段：id ：主键id
	*/
	@TableId(type = IdType.AUTO)
	private Integer id;

	/**
	* 字段：processId ：流程id
	*/
	private Integer processId;

	/**
	* 字段：nodeName ：节点名称
	*/
	private String nodeName;

	/**
	* 字段：nodeDetail ：节点明细
	*/
	private String nodeDetail;

	/**
	* 字段：keyWord ：关键词
	*/
	private String keyWord;

	/**
	 * 字段：keyWord ：使用的模板id
	 */
	private String templateUrl;

	/**
	* 字段：createTime ：创建时间
	*/
	private Date createTime;

	/**
	* 字段：createUser ：创建者
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
	public Integer getProcessId() {
		return processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
	}
	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getNodeDetail() {
		return nodeDetail;
	}

	public void setNodeDetail(String nodeDetail) {
		this.nodeDetail = nodeDetail;
	}
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
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

	public String getTemplateUrl() {
		return templateUrl;
	}

	public void setTemplateUrl(String templateUrl) {
		this.templateUrl = templateUrl;
	}
}
