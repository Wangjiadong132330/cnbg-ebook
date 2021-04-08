package com.cnbg.zs.ebook.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.entity.SysRole;
import com.cnbg.zs.ebook.api.entity.SysRolePermission;
import com.cnbg.zs.ebook.api.entity.NodeRole;
import com.cnbg.zs.ebook.api.dto.SysRoleDTO;
import com.cnbg.zs.ebook.api.dto.SysRoleMultiDTO;
import java.util.List;
import java.util.Map;

/**
* @author Faye.Wang
* @version 1.0
* @date 2021/1/6 14:49
* @Description
*/
public interface ISysRoleService {
	/**
	* 保存数据
	* @param
	*/
	void insertEntity(SysRole sysRole,Integer[] permissions,Integer[] nodes);

	/**
	* 根据主键删除数据
	* @param id
	* @return
	*/
	void deletePrimaryKey(Integer id);

	/**
	* 修改记录
	* @param
	*/
	void updateEntity(SysRole sysRole,Integer[] permissions,Integer[] nodes);

	/**
	* 获取单条数据
	* @param id
	* @return
	*/
	Map<String,Object> selectByPrimaryKey(Integer id);

	/**
	* 单表查询所有
	* @param page
	* @param entity
	* @return
	*/
	IPage<SysRoleMultiDTO> selectEntityList(Page<SysRoleMultiDTO> page,SysRoleMultiDTO entity);


	List<Object> getMenuList();

	List<Object> getNodeList();
}
