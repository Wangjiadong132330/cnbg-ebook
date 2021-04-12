package com.cnbg.zs.ebook.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.entity.Company;

import java.util.List;

/**
* @author Faye.Wang
* @version 1.0
* @date 2021/1/6 14:49
* @Description
*/
public interface ICompanyService {
	/**
	* 保存数据
	* @param entity
	*/
	void insertEntity(Company entity);

	/**
	* 获取单条数据
	* @param id
	* @return
	*/
	Company selectByPrimaryKey(Integer id);

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
	IPage<Company> selectEntityList(Page<Company> page,Company entity);

	/**
	* 修改记录
	* @param entity
	*/
	void updateEntity(Company entity);

	/**
	 * 单表查询所有List
	 * @param entity
	 * @return
	 */
	List<Company> selectEntityList(Company entity);

}
