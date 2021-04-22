package com.cnbg.zs.ebook.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.dto.DepartmentDTO;
import com.cnbg.zs.ebook.api.entity.Company;
import com.cnbg.zs.ebook.api.entity.Department;
import com.cnbg.zs.ebook.core.result.ResultData;

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
	ResultData insertEntity(Department entity);

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
	IPage<DepartmentDTO> selectEntityList(Page<DepartmentDTO> page, Department entity);

	/**
	* 修改记录
	* @param entity
	*/
	ResultData updateEntity(Department entity);

	/**
	 * 单表查询所有List
	 * @param entity
	 * @return
	 */
	List<Department> selectEntityList(Department entity);
	/**
	 * 根据企业查询当前企业下的部门
	 * @param id
	 * @return
	 */
	List<Department> getDepartmentByCompanyId(Integer id);


}
