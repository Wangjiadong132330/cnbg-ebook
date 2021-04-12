package com.cnbg.zs.ebook.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.entity.Company;
import com.cnbg.zs.ebook.api.mapper.CompanyMapper;
import com.cnbg.zs.ebook.api.service.ICompanyService;
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
public class ICompanyServiceImpl implements ICompanyService {

	@Autowired
	private CompanyMapper companyMapper;

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void insertEntity(Company record) {
		companyMapper.insert(record);
	}

	@Override
	public Company selectByPrimaryKey(Integer id) {
		return companyMapper.selectById(id);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void deletePrimaryKey(Integer id) {
		companyMapper.deleteById(id);
	}

	@Override
	public IPage<Company> selectEntityList(Page<Company> page,Company record) {
		QueryWrapper<Company> wrapper = new QueryWrapper<>();

				wrapper.like(!StringToolUtils.isEmptyObj(record.getCompanyName()),"company_name",record.getCompanyName());
		return companyMapper.selectPage(page,wrapper);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void updateEntity(Company record) {
		companyMapper.updateById(record);
	}

	@Override
	public List<Company> selectEntityList(Company record) {

		QueryWrapper<Company> wrapper = new QueryWrapper<>();
		wrapper.like(!StringToolUtils.isEmptyObj(record.getCompanyName()),"company_name",record.getCompanyName());
		return companyMapper.selectList(wrapper);
	}
}
