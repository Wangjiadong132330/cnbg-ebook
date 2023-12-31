package com.cnbg.zs.ebook.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.entity.Process;
import com.cnbg.zs.ebook.core.result.ResultData;

import java.util.List;

/**
* @author Faye.Wang
* @version 1.0
* @date 2021/1/6 14:49
* @Description
*/
public interface IProcessService {
	/**
	* 保存数据
	* @param entity
	*/
	ResultData insertEntity(Process entity);

	/**
	* 获取单条数据
	* @param id
	* @return
	*/
	Process selectByPrimaryKey(Integer id);

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
	IPage<Process> selectEntityList(Page<Process> page,Process entity);

	/**
	 * 流程图查询所有
	 * @param page
	 * @param entity
	 * @return
	 */
	IPage<Process> selectEntityList4Chart(Page<Process> page, Process entity);

	/**
	* 修改记录
	* @param entity
	*/
	ResultData updateEntity(Process entity);

	/**
	 * 单表List
	 * @param entity
	 * @return
	 */
	List<Process> selectList(Process entity);

	/**
	 * 删除流程图数据
	 * @param entity
	 * @return
	 */
	void update4DeleteChart(Process entity);
}
