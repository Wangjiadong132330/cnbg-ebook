package com.cnbg.zs.ebook.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.dto.NodeTreeDTO;
import com.cnbg.zs.ebook.api.entity.Permission;
import com.cnbg.zs.ebook.api.entity.SysRole;
import com.cnbg.zs.ebook.api.entity.SysRolePermission;
import com.cnbg.zs.ebook.api.entity.NodeRole;
import com.cnbg.zs.ebook.api.mapper.*;
import com.cnbg.zs.ebook.api.dto.SysRoleDTO;
import com.cnbg.zs.ebook.api.dto.SysRoleMultiDTO;
import com.cnbg.zs.ebook.api.service.ISysRoleService;
import com.cnbg.zs.ebook.api.utils.TreeMenuUtil;
import com.cnbg.zs.ebook.api.utils.TreeNodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.cnbg.zs.ebook.common.lang.StringToolUtils;
import java.util.*;
import java.util.stream.Collectors;

/**
* @author OFG
* @version 1.0
* @date 2021/1/6 20:09
* @Description
*/
@Service
public class ISysRoleServiceImpl implements ISysRoleService {
	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper;

	@Autowired
	private NodeRoleMapper nodeRoleMapper;
	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private ProcessMapper processMapper;

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void insertEntity(SysRole sysRole,Integer[] permissions,Integer[] nodes) {
		sysRole.setCreateTime(new Date());
		sysRoleMapper.insert(sysRole);
		insertPermissionsAndNodes(sysRole, permissions, nodes);

	}

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void deletePrimaryKey(Integer id) {
		sysRoleMapper.deleteById(id);
		QueryWrapper<SysRolePermission> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.eq("role_id",id);
		sysRolePermissionMapper.delete(queryWrapper1);

		QueryWrapper<NodeRole> queryWrapper2 = new QueryWrapper<>();
		queryWrapper2.eq("role_id",id);
		nodeRoleMapper.delete(queryWrapper2);

	}

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void updateEntity(SysRole sysRole,Integer[] permissions,Integer[] nodes) {
		sysRoleMapper.updateById(sysRole);
		QueryWrapper<SysRolePermission> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.eq("role_id",sysRole.getId());
		sysRolePermissionMapper.delete(queryWrapper1);

		QueryWrapper<NodeRole> queryWrapper2 = new QueryWrapper<>();
		queryWrapper2.eq("role_id",sysRole.getId());
		nodeRoleMapper.delete(queryWrapper2);

		insertPermissionsAndNodes(sysRole, permissions, nodes);
	}

	private void insertPermissionsAndNodes(SysRole sysRole, Integer[] permissions, Integer[] nodes) {
		for (Integer perm : permissions) {
			SysRolePermission sysRolePermission = new SysRolePermission();
			sysRolePermission.setRoleId(sysRole.getId());
			sysRolePermission.setPermissionId(perm);
			sysRolePermissionMapper.insert(sysRolePermission);
		}
		for (Integer perm : nodes) {
			NodeRole nodeRole = new NodeRole();
			nodeRole.setRoleId(sysRole.getId());
			nodeRole.setNodeId(perm);
			nodeRoleMapper.insert(nodeRole);
		}
	}

	@Override
	public Map<String,Object> selectByPrimaryKey(Integer id) {
		QueryWrapper<SysRolePermission> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.select("permission_id").eq("role_id",id);
		List<Object> rolePermissionsList = sysRolePermissionMapper.selectObjs(queryWrapper1);

		QueryWrapper<NodeRole> queryWrapper2 = new QueryWrapper<>();
		queryWrapper2.select("node_id").eq("role_id",id);
		List<Object> nodeRolesList = nodeRoleMapper.selectObjs(queryWrapper2);

		Map<String,Object> dataMap = new HashMap<>();
		Integer[] rolePermissions = new Integer[rolePermissionsList.size()];
		Integer[] nodeRoles = new Integer[nodeRolesList.size()];
		dataMap.put("rolePermissions",rolePermissionsList.toArray(rolePermissions));
		dataMap.put("nodeRoles",nodeRolesList.toArray(nodeRoles));
		dataMap.put("role",sysRoleMapper.selectById(id));
		return dataMap;
	}

	@Override
	public IPage<SysRoleMultiDTO> selectEntityList(Page<SysRoleMultiDTO> page,SysRoleMultiDTO record) {
		Map<String,Object> params = new HashMap<>();
		return sysRoleMapper.selectEntityList(page,params);
	}

	@Override
	public List<Object> getMenuList() {
		List<Permission> list = permissionMapper.selectList(null);
		TreeMenuUtil treeMenuUtil = new TreeMenuUtil();
		return treeMenuUtil.treeMenu(list);
	}

	@Override
	public List<Object> getNodeList() {
		List<NodeTreeDTO> list = processMapper.getNodeList();
		TreeNodeUtil treeNodeUtil = new TreeNodeUtil();
		return treeNodeUtil.treeMenu(list);
	}

	@Override
	public List<SysRole> getRoleList() {
		QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
		queryWrapper.select("id,role_name");
		return sysRoleMapper.selectList(queryWrapper);
	}

	@Override
	public boolean hasAdminFlag(List<Integer> ids) {
		QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
		queryWrapper.in("id",ids);
		List<SysRole>  list = sysRoleMapper.selectList(queryWrapper);
		List<SysRole>  roleList = list.stream().filter(item->item.getRoleType()==0).collect(Collectors.toList());
		return roleList.isEmpty();
	}


}
