package com.cnbg.zs.ebook.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.entity.Company;
import com.cnbg.zs.ebook.api.entity.Department;

import java.util.List;

/**
* @author Faye.Wang
* @version 1.0
* @date 2021/1/6 14:49
* @Description
*/
public interface IDepartmentService {
	/**
	* 保存数据
	* @param entity
	*/
	void insertEntity(Department entity);

	/**
	* 获取单条数据
	* @param id
	* @return
	*/
	Department selectByPrimaryKey(Integer id);

	/**
	* 根据主键删除数据
	* @param id
	* @return
	*/
	void deletePrimaryKey(Integer id);

	/**
	* 单表查询所有
	* @param entity
	* @return
	*/
	IPage<Department> selectEntityList(Page<Department> page,Department entity);

	/**
	* 修改记录
	* @param entity
	*/
	void updateEntity(Department entity);

	/**
	 * 单表查询所有List
	 * @param entity
	 * @return
	 */
	List<Department> selectEntityList(Department entity);

}
