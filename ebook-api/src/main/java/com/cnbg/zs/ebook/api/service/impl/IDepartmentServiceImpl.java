package com.cnbg.zs.ebook.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.entity.Department;
import com.cnbg.zs.ebook.api.mapper.DepartmentMapper;
import com.cnbg.zs.ebook.api.service.IDepartmentService;
import com.cnbg.zs.ebook.common.lang.StringToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author Faye.Wang
* @version 1.0
* @date 2021/1/6 20:09
* @Description
*/
@Service
public class IDepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void insertEntity(Department record) {
		departmentMapper.insert(record);
	}

	@Override
	public Department selectByPrimaryKey(Integer id) {
		return departmentMapper.selectById(id);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void deletePrimaryKey(Integer id) {
		departmentMapper.deleteById(id);
	}

	@Override
	public IPage<Department> selectEntityList(Page<Department> page,Department record) {
		QueryWrapper<Department> wrapper = new QueryWrapper<>();

				wrapper.like(!StringToolUtils.isEmptyObj(record.getDepartmentName()),"department_short_name",record.getDepartmentName())
						.or().like(!StringToolUtils.isEmptyObj(record.getDepartmentName()),"department_name",record.getDepartmentName());

		return departmentMapper.selectPage(page,wrapper);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void updateEntity(Department record) {
		departmentMapper.updateById(record);
	}

	@Override
	public List<Department> selectEntityList(Department record) {
		QueryWrapper<Department> wrapper = new QueryWrapper<>();
		wrapper.like(!StringToolUtils.isEmptyObj(record.getDepartmentName()),"department_short_name",record.getDepartmentName())
				.or().like(!StringToolUtils.isEmptyObj(record.getDepartmentName()),"department_name",record.getDepartmentName());
		return departmentMapper.selectList(wrapper);
	}
}
