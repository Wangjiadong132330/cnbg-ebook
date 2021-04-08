package com.cnbg.zs.ebook.api.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.core.controller.BaseController;
import com.cnbg.zs.ebook.api.entity.Provincial;
import com.cnbg.zs.ebook.core.result.ResultData;
import com.cnbg.zs.ebook.api.service.IProvincialService;
import com.cnbg.zs.ebook.api.vo.ProvincialVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
* @author Faye.Wang
* @version 1.0
* @date 2021/1/6 14:03
* @Description
*/
@RestController
@RequestMapping("/api/provincial")
public class ProvincialController extends BaseController {

	@Autowired
	private IProvincialService iProvincialService;

	/**
	* 保存数据
	* @param record
	* @return
	*/
	@PostMapping("/insert")
	public ResultData insertProvincial(@RequestBody ProvincialVo record){
		Provincial entity = new Provincial();
		BeanUtils.copyProperties(record,entity);
		iProvincialService.insertEntity(entity);
		return super.resultSuccess();
	}
	/**
	* 修改数据
	* @param record
	* @return
	*/
	@PostMapping("/update")
	public ResultData updateDemo(@RequestBody ProvincialVo record){
		Provincial entity = new Provincial();
		BeanUtils.copyProperties(record,entity);
		iProvincialService.updateEntity(entity);
		return super.resultSuccess();
	}
	/**
	* 查询数据
	* @param record
	* @return
	*/
	@PostMapping("/getId")
	public ResultData getProvincialById(@RequestBody ProvincialVo record){
		return super.resultSuccess(iProvincialService.selectByPrimaryKey(record.getId()));
	}

	/**
	* 删除数据
	* @param record
	* @return
	*/
	@PostMapping("/delete")
	public ResultData deleteDemoById(@RequestBody ProvincialVo record){
		iProvincialService.deletePrimaryKey(record.getId());
		return super.resultSuccess();
	}

	/**
	* 分页查询数据
	* @param record
	* @return
	*/
	@PostMapping("/getPageQuery")
	public ResultData getPageQuery(@RequestBody ProvincialVo record){
		Provincial entity = new Provincial();
		BeanUtils.copyProperties(record,entity);

		return super.resultSuccess(iProvincialService.selectEntityList(new Page<>(record.getPageNo(), record.getPageSize()),entity));
	}

}
