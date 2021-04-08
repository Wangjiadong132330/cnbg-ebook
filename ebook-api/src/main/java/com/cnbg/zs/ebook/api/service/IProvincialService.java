package com.cnbg.zs.ebook.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.entity.Provincial;

import java.util.List;

/**
* @author Faye.Wang
* @version 1.0
* @date 2021/1/6 14:49
* @Description
*/
public interface IProvincialService {
	/**
	* 保存数据
	* @param entity
	*/
	void insertEntity(Provincial entity);

	/**
	* 获取单条数据
	* @param id
	* @return
	*/
	Provincial selectByPrimaryKey(Integer id);

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
	IPage<Provincial> selectEntityList(Page<Provincial> page,Provincial entity);

	/**
	* 修改记录
	* @param entity
	*/
	void updateEntity(Provincial entity);


}
