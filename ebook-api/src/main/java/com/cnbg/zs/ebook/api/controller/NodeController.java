package com.cnbg.zs.ebook.api.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.utils.SessionUtils;
import com.cnbg.zs.ebook.core.controller.BaseController;
import com.cnbg.zs.ebook.api.entity.Node;
import com.cnbg.zs.ebook.core.result.ResultData;
import com.cnbg.zs.ebook.api.service.INodeService;
import com.cnbg.zs.ebook.api.vo.NodeVo;
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
@RequestMapping("/api/node")
public class NodeController extends BaseController {

	@Autowired
	private INodeService iNodeService;

	/**
	* 保存数据
	* @param record
	* @return
	*/
	@PostMapping("/insert")
	public ResultData insertNode(@RequestBody NodeVo record){
		Node entity = new Node();
		BeanUtils.copyProperties(record,entity);
		entity.setCreateUser(SessionUtils.getSessionUserName(record.getSessionId()));
		entity.setCreateTime(new Date());
		entity.setUpdateUser(SessionUtils.getSessionUserName(record.getSessionId()));
		entity.setUpdateTime(new Date());
		return iNodeService.insertEntity(entity);
	}
	/**
	* 修改数据
	* @param record
	* @return
	*/
	@PostMapping("/update")
	public ResultData updateDemo(@RequestBody NodeVo record){
		Node entity = new Node();
		BeanUtils.copyProperties(record,entity);
		entity.setUpdateUser(SessionUtils.getSessionUserName(record.getSessionId()));
		entity.setUpdateTime(new Date());
		return iNodeService.updateEntity(entity);
	}
	/**
	* 查询数据
	* @param record
	* @return
	*/
	@PostMapping("/getId")
	public ResultData getNodeById(@RequestBody NodeVo record){
		return super.resultSuccess(iNodeService.selectByPrimaryKey(record.getId()));
	}

	/**
	* 删除数据
	* @param record
	* @return
	*/
	@PostMapping("/delete")
	public ResultData deleteDemoById(@RequestBody NodeVo record){
		iNodeService.deletePrimaryKey(record.getId());
		return super.resultSuccess();
	}

	/**
	* 分页查询数据
	* @param record
	* @return
	*/
	@PostMapping("/getPageQuery")
	public ResultData getPageQuery(@RequestBody NodeVo record){
		Node entity = new Node();
		BeanUtils.copyProperties(record,entity);

		return super.resultSuccess(iNodeService.selectEntityList(new Page<>(record.getPageNo(), record.getPageSize()),entity));
	}

}
