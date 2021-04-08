package com.cnbg.zs.ebook.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.dto.NodeDTO;
import com.cnbg.zs.ebook.api.dto.UserInfoDTO;
import com.cnbg.zs.ebook.api.entity.Node;
import com.cnbg.zs.ebook.api.entity.UserInfo;
import com.cnbg.zs.ebook.api.mapper.NodeMapper;
import com.cnbg.zs.ebook.api.service.INodeService;
import com.cnbg.zs.ebook.common.lang.StringToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

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

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void insertEntity(Node record) {
		nodeMapper.insert(record);
	}

	@Override
	public Node selectByPrimaryKey(Integer id) {
		return nodeMapper.selectById(id);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void deletePrimaryKey(Integer id) {
		nodeMapper.deleteById(id);
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
	public void updateEntity(Node record) {
		nodeMapper.updateById(record);
	}
}
