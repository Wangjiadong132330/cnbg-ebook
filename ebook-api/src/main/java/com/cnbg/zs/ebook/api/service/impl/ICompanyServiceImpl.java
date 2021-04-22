package com.cnbg.zs.ebook.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.dto.CompanyDTO;
import com.cnbg.zs.ebook.api.entity.Company;
import com.cnbg.zs.ebook.api.entity.Department;
import com.cnbg.zs.ebook.api.mapper.CompanyMapper;
import com.cnbg.zs.ebook.api.mapper.DepartmentMapper;
import com.cnbg.zs.ebook.api.service.ICompanyService;
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
public class ICompanyServiceImpl implements ICompanyService {

	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private DepartmentMapper departmentMapper;

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public ResultData insertEntity(Company record) {

		ResultData resultData = new ResultData<>(ResultEnum.HTTP_SUCCESS.getCode(),ResultEnum.HTTP_SUCCESS.getMessage());

		// 检查公司名称是否重复
		boolean checkCompanyNameRepeat = checkCompanyShortNameRepeat(record);
		if (checkCompanyNameRepeat) {

			// 公司名称重复
			resultData = new ResultData<>(ResultEnum.MSG_CODE_ERROR_504.getCode(),ResultEnum.MSG_CODE_ERROR_504.getMessage());

		} else {

			// 检查公司简称是否重复
			boolean checkCompanyShortNameRepeatFlag = checkCompanyNameRepeat(record);
			if (checkCompanyShortNameRepeatFlag) {

				// 部门简称重复
				resultData = new ResultData<>(ResultEnum.MSG_CODE_ERROR_503.getCode(),ResultEnum.MSG_CODE_ERROR_503.getMessage());

			} else {

				// 新增公司数据
				companyMapper.insert(record);
			}

		}

		return resultData;

	}

	@Override
	public Company selectByPrimaryKey(Integer id) {
		return companyMapper.selectById(id);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void deletePrimaryKey(Integer id) {
		QueryWrapper<Department> wrapper = new QueryWrapper<>();
		wrapper.eq("company_id",id);
		departmentMapper.delete(wrapper);
		companyMapper.deleteById(id);
	}

	@Override
	public IPage<CompanyDTO> selectEntityList(Page<CompanyDTO> page, Company record) {

		Map<String,Object> cmMap = new HashMap<>();
		cmMap.put("companyName",StringToolUtils.isEmptyValue(record.getCompanyName()));
		return companyMapper.selectEntityList(page,cmMap);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public ResultData updateEntity(Company record) {

		Company updateCompany = companyMapper.selectById(record.getId());

		// 修改公司名称
		if (!updateCompany.getCompanyName().equals(record.getCompanyName())) {

			// 检查公司名称是否重复
			boolean checkCompanyNameRepeat = checkCompanyShortNameRepeat(record);
			if (checkCompanyNameRepeat) {

				// 公司名称重复
				return new ResultData<>(ResultEnum.MSG_CODE_ERROR_504.getCode(),ResultEnum.MSG_CODE_ERROR_504.getMessage());

			}
		}

		// 修改公司简称
		if (!updateCompany.getCompanyShortName().equals(record.getCompanyShortName())) {

			// 检查公司简称是否重复
			boolean checkCompanyShortNameRepeatFlag = checkCompanyNameRepeat(record);
			if (checkCompanyShortNameRepeatFlag) {

				// 部门简称重复
				return new ResultData<>(ResultEnum.MSG_CODE_ERROR_503.getCode(),ResultEnum.MSG_CODE_ERROR_503.getMessage());

			}
		}

		// 修改公司数据
		companyMapper.updateById(record);

		return new ResultData<>(ResultEnum.HTTP_SUCCESS.getCode(),ResultEnum.HTTP_SUCCESS.getMessage());
	}

	@Override
	public List<Company> getDropDownQuery() {
		QueryWrapper<Company> queryWrapper = new QueryWrapper();
		queryWrapper.select("id,company_name");
		return companyMapper.selectList(queryWrapper);
	}

	@Override
	public Company selectByName(String name) {
		QueryWrapper<Company> queryWrapper = new QueryWrapper();
		queryWrapper.eq("company_name",name);
		return companyMapper.selectOne(queryWrapper);
	}

	@Override
	public List<Company> selectEntityList(Company record) {

		QueryWrapper<Company> wrapper = new QueryWrapper<>();
		wrapper.like(!StringToolUtils.isEmptyObj(record.getCompanyName()),"company_name",record.getCompanyName());
		return companyMapper.selectList(wrapper);
	}

	/**
	 * 检查公司简称是否重复
	 *
	 * @param record
	 * @return
	 */
	private boolean checkCompanyShortNameRepeat(Company record) {

		List<Company> companyList = companyMapper.selectList(
				Wrappers.<Company>lambdaQuery()
						.eq(Company::getCompanyShortName, record.getCompanyShortName()));

		return companyList.size() > 0;
	}

	/**
	 * 检查公司名称是否重复
	 *
	 * @param record
	 * @return
	 */
	private boolean checkCompanyNameRepeat(Company record) {

		List<Company> companyList = companyMapper.selectList(
				Wrappers.<Company>lambdaQuery()
						.eq(Company::getCompanyName, record.getCompanyName()));

		return companyList.size() > 0;
	}
}
