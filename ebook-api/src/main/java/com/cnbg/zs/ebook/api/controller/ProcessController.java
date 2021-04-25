package com.cnbg.zs.ebook.api.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.utils.SessionUtils;
import com.cnbg.zs.ebook.core.controller.BaseController;
import com.cnbg.zs.ebook.api.entity.Process;
import com.cnbg.zs.ebook.core.result.ResultData;
import com.cnbg.zs.ebook.api.service.IProcessService;
import com.cnbg.zs.ebook.api.vo.ProcessVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
* @author Faye.Wang
* @version 1.0
* @date 2021/1/6 14:03
* @Description
*/
@RestController
@RequestMapping("/api/process")
public class ProcessController extends BaseController {

	@Autowired
	private IProcessService iProcessService;

	/**
	* 保存数据
	* @param record
	* @return
	*/
	@PostMapping("/insert")
	public ResultData insertProcess(@RequestBody ProcessVo record){
		Process entity = new Process();
		BeanUtils.copyProperties(record,entity);
		entity.setCreateUser(SessionUtils.getSessionUserName(record.getSessionId()));
		entity.setCreateTime(new Date());
		entity.setUpdateUser(SessionUtils.getSessionUserName(record.getSessionId()));
		entity.setUpdateTime(new Date());
		return iProcessService.insertEntity(entity);
	}
	/**
	* 修改数据
	* @param record
	* @return
	*/
	@PostMapping("/update")
	public ResultData updateDemo(@RequestBody ProcessVo record){
		Process entity = new Process();
		BeanUtils.copyProperties(record,entity);
		entity.setUpdateUser(SessionUtils.getSessionUserName(record.getSessionId()));
		entity.setUpdateTime(new Date());
		return iProcessService.updateEntity(entity);
	}
	/**
	* 查询数据
	* @param record
	* @return
	*/
	@PostMapping("/getId")
	public ResultData getProcessById(@RequestBody ProcessVo record){
		return super.resultSuccess(iProcessService.selectByPrimaryKey(record.getId()));
	}

	/**
	* 删除数据
	* @param record
	* @return
	*/
	@PostMapping("/delete")
	public ResultData deleteDemoById(@RequestBody ProcessVo record){
		iProcessService.deletePrimaryKey(record.getId());
		return super.resultSuccess();
	}

	/**
	* 分页查询数据
	* @param record
	* @return
	*/
	@PostMapping("/getPageQuery")
	public ResultData getPageQuery(@RequestBody ProcessVo record){
		Process entity = new Process();
		BeanUtils.copyProperties(record,entity);

		return super.resultSuccess(iProcessService.selectEntityList(new Page<>(record.getPageNo(), record.getPageSize()),entity));
	}

	/**
	 * 分页查询流程图数据
	 * @param record
	 * @return
	 */
	@PostMapping("/getPageQuery4Chart")
	public ResultData getPageQuery4Chart(@RequestBody ProcessVo record){
		Process entity = new Process();
		BeanUtils.copyProperties(record,entity);
		return super.resultSuccess(iProcessService.selectEntityList4Chart(new Page<>(record.getPageNo(), record.getPageSize()),entity));
	}

	/**
	 * 查询流程下拉框List
	 *
	 * @param record
	 * @return
	 */
	@PostMapping("/getListQuery")
	public ResultData selectList(@RequestBody ProcessVo record){
		Process entity = new Process();
		BeanUtils.copyProperties(record,entity);
		return super.resultSuccess(iProcessService.selectList(entity));
	}

}
