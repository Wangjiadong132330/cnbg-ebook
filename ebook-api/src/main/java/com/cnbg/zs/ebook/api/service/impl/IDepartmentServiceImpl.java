package com.cnbg.zs.ebook.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.dto.DepartmentDTO;
import com.cnbg.zs.ebook.api.entity.Department;
import com.cnbg.zs.ebook.api.entity.Node;
import com.cnbg.zs.ebook.api.mapper.DepartmentMapper;
import com.cnbg.zs.ebook.api.service.IDepartmentService;
import com.cnbg.zs.ebook.common.lang.StringToolUtils;
import com.cnbg.zs.ebook.core.result.ResultData;
import com.cnbg.zs.ebook.core.result.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	public ResultData insertEntity(Department record) {

		ResultData resultData = new ResultData<>(ResultEnum.HTTP_SUCCESS.getCode(),ResultEnum.HTTP_SUCCESS.getMessage());

		// 检查部门名称是否重复
		boolean checkDepartmentNameRepeat = checkDepartmentShortNameRepeat(record);
		if (checkDepartmentNameRepeat) {

			// 部门名称重复
			resultData = new ResultData<>(ResultEnum.MSG_CODE_ERROR_506.getCode(),ResultEnum.MSG_CODE_ERROR_506.getMessage());

		} else {

			// 检查部门简称是否重复
			boolean departmentShortNameRepeatFlag = checkDepartmentNameRepeat(record);
			if (departmentShortNameRepeatFlag) {

				// 部门简称重复
				resultData = new ResultData<>(ResultEnum.MSG_CODE_ERROR_505.getCode(),ResultEnum.MSG_CODE_ERROR_505.getMessage());

			} else {

				// 新增部门数据
				departmentMapper.insert(record);
			}

		}

		return resultData;

	}

	/**
	 * 检查部门简称是否重复
	 *
	 * @param record
	 * @return
	 */
	private boolean checkDepartmentShortNameRepeat(Department record) {

		List<Department> departmentList = departmentMapper.selectList(
				Wrappers.<Department>lambdaQuery()
						.eq(Department::getCompanyId, record.getCompanyId())
						.eq(Department::getDepartmentShortName, record.getDepartmentShortName()));

		return departmentList.size() > 0;
	}

	/**
	 * 检查部门名称是否重复
	 *
	 * @param record
	 * @return
	 */
	private boolean checkDepartmentNameRepeat(Department record) {

		List<Department> departmentList = departmentMapper.selectList(
				Wrappers.<Department>lambdaQuery()
						.eq(Department::getCompanyId, record.getCompanyId())
						.eq(Department::getDepartmentName, record.getDepartmentName()));

		return departmentList.size() > 0;
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
	public IPage<DepartmentDTO> selectEntityList(Page<DepartmentDTO> page, Department record) {

		Map<String,Object> cmMap = new HashMap<>();
		cmMap.put("departmentName",StringToolUtils.isEmptyValue(record.getDepartmentName()));
		cmMap.put("companyId",StringToolUtils.isEmptyValue(record.getCompanyId()));
		return departmentMapper.selectEntityList(page,cmMap);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public ResultData updateEntity(Department record) {

		Department updateDepartment = departmentMapper.selectById(record.getId());

		// 修改部门名称 或者 改变公司
		if (!updateDepartment.getDepartmentName().equals(record.getDepartmentName())
				|| updateDepartment.getCompanyId().intValue() != record.getCompanyId().intValue()) {

			// 检查部门名称是否重复
			boolean checkDepartmentNameRepeat = checkDepartmentShortNameRepeat(record);
			if (checkDepartmentNameRepeat) {

				// 部门名称重复
				return new ResultData<>(ResultEnum.MSG_CODE_ERROR_506.getCode(),ResultEnum.MSG_CODE_ERROR_506.getMessage());

			}
		}

		// 修改部门简称 或者 改变公司
		if (!updateDepartment.getDepartmentShortName().equals(record.getDepartmentShortName())
				|| updateDepartment.getCompanyId().intValue() != record.getCompanyId().intValue()) {

			// 检查部门简称是否重复
			boolean departmentShortNameRepeatFlag = checkDepartmentNameRepeat(record);
			if (departmentShortNameRepeatFlag) {

				// 部门简称重复
				return new ResultData<>(ResultEnum.MSG_CODE_ERROR_505.getCode(),ResultEnum.MSG_CODE_ERROR_505.getMessage());

			}
		}

		// 新增部门数据
		departmentMapper.updateById(record);

		return new ResultData<>(ResultEnum.HTTP_SUCCESS.getCode(),ResultEnum.HTTP_SUCCESS.getMessage());
	}

	@Override
	public List<Department> getDepartmentByCompanyId(Integer id) {
		QueryWrapper<Department> wrapper = new QueryWrapper<>();
		wrapper.eq("company_id",id);
		return departmentMapper.selectList(wrapper);
	}

	@Override
	public List<Department> selectEntityList(Department record) {
		QueryWrapper<Department> wrapper = new QueryWrapper<>();
		wrapper.like(!StringToolUtils.isEmptyObj(record.getDepartmentName()),"department_short_name",record.getDepartmentName())
				.or().like(!StringToolUtils.isEmptyObj(record.getDepartmentName()),"department_name",record.getDepartmentName());
		return departmentMapper.selectList(wrapper);
	}
}
