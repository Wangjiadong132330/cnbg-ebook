package com.cnbg.zs.ebook.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.entity.Node;
import com.cnbg.zs.ebook.api.entity.Process;
import com.cnbg.zs.ebook.api.mapper.ProcessMapper;
import com.cnbg.zs.ebook.api.service.IProcessService;
import com.cnbg.zs.ebook.common.lang.StringToolUtils;
import com.cnbg.zs.ebook.core.result.ResultData;
import com.cnbg.zs.ebook.core.result.ResultEnum;
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
public class IProcessServiceImpl implements IProcessService {

	@Autowired
	private ProcessMapper processMapper;

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public ResultData insertEntity(Process record) {

		ResultData resultData = new ResultData<>(ResultEnum.HTTP_SUCCESS.getCode(),ResultEnum.HTTP_SUCCESS.getMessage());

		// 检查流程名是否重复
		boolean nodeNameRepeatFlag = checkProcessNameRepeat(record);

		if (nodeNameRepeatFlag) {

			// 节点名重复
			resultData = new ResultData<>(ResultEnum.MSG_CODE_ERROR_502.getCode(),ResultEnum.MSG_CODE_ERROR_502.getMessage());

		} else {

			processMapper.insert(record);
		}

		return resultData;
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
		wrapper.orderByAsc("id");
		return processMapper.selectPage(page,wrapper);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public ResultData updateEntity(Process record) {

		ResultData resultData = new ResultData<>(ResultEnum.HTTP_SUCCESS.getCode(),ResultEnum.HTTP_SUCCESS.getMessage());

		Process updateProcess = processMapper.selectById(record.getId());

		if (!updateProcess.getProcessName().equals(record.getProcessName())) {
			// 检查节点是否重复
			boolean processNameRepeatFlag = checkProcessNameRepeat(record);
			if (processNameRepeatFlag) {
				// 流程名重复
				resultData = new ResultData<>(ResultEnum.MSG_CODE_ERROR_502.getCode(),ResultEnum.MSG_CODE_ERROR_502.getMessage());
			}
		}

		processMapper.updateById(record);

		return resultData;
	}

	/**
	 * 检查流程名是否重复
	 *
	 * @param record
	 * @return
	 */
	private boolean checkProcessNameRepeat(Process record) {

		List<Process> processList = processMapper.selectList(
				Wrappers.<Process>lambdaQuery()
						.eq(Process::getProcessName, record.getProcessName()));

		return processList.size() > 0;
	}

	@Override
	public List<Process> selectList(Process record) {
		QueryWrapper<Process> wrapper = new QueryWrapper<>();
		wrapper.select("id, process_name");
		wrapper.like(!StringToolUtils.isEmptyObj(record.getProcessName()),"process_name",record.getProcessName());
		wrapper.orderByAsc("id");
		return processMapper.selectList(wrapper);
	}
}
