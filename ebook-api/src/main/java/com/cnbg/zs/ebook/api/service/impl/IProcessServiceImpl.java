package com.cnbg.zs.ebook.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.entity.Process;
import com.cnbg.zs.ebook.api.mapper.ProcessMapper;
import com.cnbg.zs.ebook.api.service.IProcessService;
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
public class IProcessServiceImpl implements IProcessService {

	@Autowired
	private ProcessMapper processMapper;

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void insertEntity(Process record) {
		processMapper.insert(record);
	}

	@Override
	public Process selectByPrimaryKey(Integer id) {
		return processMapper.selectById(id);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void deletePrimaryKey(Integer id) {
		processMapper.deleteById(id);
	}

	@Override
	public IPage<Process> selectEntityList(Page<Process> page,Process record) {
		QueryWrapper<Process> wrapper = new QueryWrapper<>();

				wrapper.like(!StringToolUtils.isEmptyObj(record.getProcessName()),"process_name",record.getProcessName());
		return processMapper.selectPage(page,wrapper);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void updateEntity(Process record) {
		processMapper.updateById(record);
	}
}
