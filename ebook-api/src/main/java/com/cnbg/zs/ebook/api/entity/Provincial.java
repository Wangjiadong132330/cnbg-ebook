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
@TableName(value = "t_provincial")
public class Provincial implements Serializable{

	/**
	* 字段：id ：主键id
	*/
	@TableId(type = IdType.AUTO)
	private Integer id;

	/**
	* 字段：provincialName ：省名称
	*/
	private String provincialName;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getProvincialName() {
		return provincialName;
	}

	public void setProvincialName(String provincialName) {
		this.provincialName = provincialName;
	}

}
