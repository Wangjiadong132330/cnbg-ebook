package com.cnbg.zs.ebook.api.vo;

import com.cnbg.zs.ebook.core.result.RequestVo;
import java.util.*;
/**
* @author Faye.Wang
* @version 1.0
* @date 2021/1/6 14:44
* @Description
*/
public class ProvincialVo extends RequestVo {
	/**
	* 字段：id ：主键id
	*/

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
