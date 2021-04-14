package com.cnbg.zs.ebook.api.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.utils.SessionUtils;
import com.cnbg.zs.ebook.core.controller.BaseController;
import com.cnbg.zs.ebook.api.entity.Department;
import com.cnbg.zs.ebook.core.result.ResultData;
import com.cnbg.zs.ebook.api.service.IDepartmentService;
import com.cnbg.zs.ebook.api.vo.DepartmentVo;
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
@RequestMapping("/api/department")
public class DepartmentController extends BaseController {

	@Autowired
	private IDepartmentService iDepartmentService;

	/**
	* 保存数据
	* @param record
	* @return
	*/
	@PostMapping("/insert")
	public ResultData insertDepartment(@RequestBody DepartmentVo record){
		Department entity = new Department();
		BeanUtils.copyProperties(record,entity);
		entity.setCreateUser(SessionUtils.getSessionUserName(record.getSessionId()));
		entity.setCreateTime(new Date());
		return iDepartmentService.insertEntity(entity);
	}
	/**
	* 修改数据
	* @param record
	* @return
	*/
	@PostMapping("/update")
	public ResultData updateDemo(@RequestBody DepartmentVo record){
		Department entity = new Department();
		BeanUtils.copyProperties(record,entity);
		iDepartmentService.updateEntity(entity);
		return super.resultSuccess();
	}
	/**
	* 查询数据
	* @param record
	* @return
	*/
	@PostMapping("/getId")
	public ResultData getDepartmentById(@RequestBody DepartmentVo record){
		return super.resultSuccess(iDepartmentService.selectByPrimaryKey(record.getId()));
	}

	/**
	* 删除数据
	* @param record
	* @return
	*/
	@PostMapping("/delete")
	public ResultData deleteDemoById(@RequestBody DepartmentVo record){
		iDepartmentService.deletePrimaryKey(record.getId());
		return super.resultSuccess();
	}

	/**
	* 分页查询数据
	* @param record
	* @return
	*/
	@PostMapping("/getPageQuery")
	public ResultData getPageQuery(@RequestBody DepartmentVo record){
		Department entity = new Department();
		BeanUtils.copyProperties(record,entity);

		return super.resultSuccess(iDepartmentService.selectEntityList(new Page<>(record.getPageNo(), record.getPageSize()),entity));
	}

	/**
	 * 跟姐企业ID查询部门信息
	 * @param record
	 * @return
	 */
	@PostMapping("/getQueryDepartment")
	public ResultData getQueryDepartment(@RequestBody DepartmentVo record){
		return super.resultSuccess(iDepartmentService.getDepartmentByCompanyId(record.getCompanyId()));
	}


}
