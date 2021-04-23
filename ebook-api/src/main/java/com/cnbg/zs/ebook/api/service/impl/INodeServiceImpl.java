package com.cnbg.zs.ebook.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.dto.NodeDTO;
import com.cnbg.zs.ebook.api.entity.Node;
import com.cnbg.zs.ebook.api.entity.NodeRole;
import com.cnbg.zs.ebook.api.entity.Process;
import com.cnbg.zs.ebook.api.entity.RoleUser;
import com.cnbg.zs.ebook.api.mapper.NodeMapper;
import com.cnbg.zs.ebook.api.mapper.NodeRoleMapper;
import com.cnbg.zs.ebook.api.mapper.ProcessMapper;
import com.cnbg.zs.ebook.api.service.INodeService;
import com.cnbg.zs.ebook.common.constant.Constants;
import com.cnbg.zs.ebook.common.lang.StringToolUtils;
import com.cnbg.zs.ebook.core.result.ResultData;
import com.cnbg.zs.ebook.core.result.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author Faye.Wang
* @version 1.0
* @date 2021/1/6 20:09
* @Description
*/
@Service
public class INodeServiceImpl implements INodeService {

	@Autowired
	private NodeMapper nodeMapper;
	@Autowired
	private NodeRoleMapper nodeRoleMapper;
	@Autowired
	private ProcessMapper processMapper;

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public ResultData insertEntity(Node record) {

		ResultData resultData = new ResultData<>(ResultEnum.HTTP_SUCCESS.getCode(),ResultEnum.HTTP_SUCCESS.getMessage());

		// 检查节点是否重复
		boolean nodeNameRepeatFlag = checkNodeNameRepeat(record);

		if (nodeNameRepeatFlag) {

			// 节点名重复
			resultData = new ResultData<>(ResultEnum.MSG_CODE_ERROR_501.getCode(),ResultEnum.MSG_CODE_ERROR_501.getMessage());

		} else {

			nodeMapper.insert(record);
		}

		return resultData;
	}

	/**
	 * 检查节点是否重复
	 *
	 * @param record
	 * @return
	 */
	private boolean checkNodeNameRepeat(Node record) {

		List<Node> nodeList = nodeMapper.selectList(
				Wrappers.<Node>lambdaQuery()
						.eq(Node::getProcessId, record.getProcessId())
						.eq(Node::getNodeName, record.getNodeName()));

		return nodeList.size() > 0;
	}

	@Override
	public Node selectByPrimaryKey(Integer id) {
		return nodeMapper.selectById(id);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public ResultData deletePrimaryKey(Integer id) {

		ResultData resultData = new ResultData<>(ResultEnum.HTTP_SUCCESS.getCode(),ResultEnum.HTTP_SUCCESS.getMessage());
		// 删除节点之前有校验
		QueryWrapper<Process> queryWrapper = new QueryWrapper<>();
		queryWrapper.like("process_json", Constants.nodeUsedKey + id);
		List<Process> processList = processMapper.selectList(queryWrapper);
		// 节点正在被使用中
		if (processList.size() > 0) {

			// 节点正在被使用中
			resultData = new ResultData<>(ResultEnum.MSG_CODE_ERROR_508.getCode(),ResultEnum.MSG_CODE_ERROR_508.getMessage());
		} else {
			nodeMapper.deleteById(id);
		}

		return resultData;
	}

//	@Override
//	public IPage<Node> selectEntityList(Page<Node> page,Node record) {
//		QueryWrapper<Node> wrapper = new QueryWrapper<>();
//
//				wrapper.like(!StringToolUtils.isEmptyObj(record.getNodeName()),"node_name",record.getNodeName());
//		return nodeMapper.selectPage(page,wrapper);
//	}

	@Override
	public IPage<NodeDTO> selectEntityList(Page<NodeDTO> page, Node record) {
		Map<String,Object> paramsMap = new HashMap<>();
		paramsMap.put("nodeName",StringToolUtils.isEmptyValue(record.getNodeName()));
		paramsMap.put("keyWord",StringToolUtils.isEmptyValue(record.getKeyWord()));
		paramsMap.put("id", record.getId());
		paramsMap.put("processId", record.getProcessId());
		return nodeMapper.selectEntityList(page,paramsMap);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public ResultData updateEntity(Node record) {

		Node updateNode = nodeMapper.selectById(record.getId());

		// 改变节点名 或 改变流程id
		if (!updateNode.getNodeName().equals(record.getNodeName())
				|| updateNode.getProcessId().intValue() != record.getProcessId().intValue()) {
			// 检查节点是否重复
			boolean nodeNameRepeatFlag = checkNodeNameRepeat(record);
			if (nodeNameRepeatFlag) {
				// 节点名重复
				return new ResultData<>(ResultEnum.MSG_CODE_ERROR_501.getCode(),ResultEnum.MSG_CODE_ERROR_501.getMessage());
			}
		}

		nodeMapper.updateById(record);

		return new ResultData<>(ResultEnum.HTTP_SUCCESS.getCode(),ResultEnum.HTTP_SUCCESS.getMessage());
	}

	@Override
	public boolean checkRoleForNode(List<RoleUser> roleUsers,Integer nodeId) {
		QueryWrapper<NodeRole> queryWrapper = new QueryWrapper<>();
		queryWrapper.in("role_id",roleUsers.stream().map(RoleUser::getRoleId).collect(Collectors.toList()));
		List<NodeRole> nodeRoles = nodeRoleMapper.selectList(queryWrapper);
		return nodeRoles.contains(nodeId);
	}

}
