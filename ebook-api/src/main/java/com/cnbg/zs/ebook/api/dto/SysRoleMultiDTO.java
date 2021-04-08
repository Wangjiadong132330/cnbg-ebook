package com.cnbg.zs.ebook.api.dto;
import java.util.*;

/**
* @author OFG
* @version 1.0
* @date 2021/1/6 14:44
* @Description
*/
public class SysRoleMultiDTO {
	/**
	* 字段：id ：
	*/

	private Integer id;
	/**
	* 字段：roleName ：角色名称
	*/

	private String roleName;

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
}
