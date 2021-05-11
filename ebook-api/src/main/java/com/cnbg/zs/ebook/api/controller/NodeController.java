package com.cnbg.zs.ebook.api.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.entity.RoleUser;
import com.cnbg.zs.ebook.api.utils.SessionUtils;
import com.cnbg.zs.ebook.common.lang.JsonUtils;
import com.cnbg.zs.ebook.common.redis.JRedisUtils;
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
import java.util.List;


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
		Integer userId = SessionUtils.getSessionUserId(record.getSessionId());
		List<RoleUser> roleUserList = JsonUtils.toJsonList(JRedisUtils.getKeyValue("SESSION:ROLE:"+userId),RoleUser.class);
		if("admin".equals(JRedisUtils.getKeyValue("SESSION:ROLE:TYPE"+userId))){
			return super.resultSuccess(iNodeService.selectByPrimaryKey(record.getId()));
		}else{
			if(iNodeService.checkRoleForNode(roleUserList,record.getId())){
				return super.resultSuccess(iNodeService.selectByPrimaryKey(record.getId()));
			}else{
				return super.resultFail("节点权限不足，无法访问",null);
			}
		}
	}

	/**
	* 删除数据
	* @param record
	* @return
	*/
	@PostMapping("/delete")
	public ResultData deleteDemoById(@RequestBody NodeVo record){

		return iNodeService.deletePrimaryKey(record.getId());
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
