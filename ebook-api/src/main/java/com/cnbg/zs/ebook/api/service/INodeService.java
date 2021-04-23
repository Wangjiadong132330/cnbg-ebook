package com.cnbg.zs.ebook.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.dto.NodeDTO;
import com.cnbg.zs.ebook.api.entity.Node;
import com.cnbg.zs.ebook.api.entity.RoleUser;
import com.cnbg.zs.ebook.core.result.ResultData;

import java.util.List;

/**
* @author Faye.Wang
* @version 1.0
* @date 2021/1/6 14:49
* @Description
*/
public interface INodeService {
	/**
	* 保存数据
	* @param entity
	*/
	ResultData insertEntity(Node entity);

	/**
	* 获取单条数据
	* @param id
	* @return
	*/
	Node selectByPrimaryKey(Integer id);

	/**
	* 根据主键删除数据
	* @param id
	* @return
	*/
	void deletePrimaryKey(Integer id);

	/**
	* 单表查询所有
	* @param entity
	* @return
	*/
	IPage<NodeDTO> selectEntityList(Page<NodeDTO> page, Node entity);

	/**
	* 修改记录
	* @param entity
	*/
	ResultData updateEntity(Node entity);

	/**
	 * 检查当前用户是否有节点权限
	 * @param roleUsers
	 * @return
	 */
	boolean checkRoleForNode(List<RoleUser> roleUsers,Integer nodeId);
}
