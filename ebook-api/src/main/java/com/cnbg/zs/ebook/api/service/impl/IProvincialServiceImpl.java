package com.cnbg.zs.ebook.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.entity.Provincial;
import com.cnbg.zs.ebook.api.mapper.ProvincialMapper;
import com.cnbg.zs.ebook.api.service.IProvincialService;
import com.cnbg.zs.ebook.common.lang.StringToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
* @author Faye.Wang
* @version 1.0
* @date 2021/1/6 20:09
* @Description
*/
@Service
public class IProvincialServiceImpl implements IProvincialService {

	@Autowired
	private ProvincialMapper provincialMapper;

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void insertEntity(Provincial record) {
		provincialMapper.insert(record);
	}

	@Override
	public Provincial selectByPrimaryKey(Integer id) {
		return provincialMapper.selectById(id);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void deletePrimaryKey(Integer id) {
		provincialMapper.deleteById(id);
	}

	@Override
	public IPage<Provincial> selectEntityList(Page<Provincial> page,Provincial record) {
		QueryWrapper<Provincial> wrapper = new QueryWrapper<>();

				wrapper.like(!StringToolUtils.isEmptyObj(record.getProvincialName()),"provincial_name",record.getProvincialName());
		return provincialMapper.selectPage(page,wrapper);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void updateEntity(Provincial record) {
		provincialMapper.updateById(record);
	}
}
