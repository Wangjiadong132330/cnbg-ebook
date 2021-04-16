package com.cnbg.zs.ebook.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.dto.RoleUserDTO;
import com.cnbg.zs.ebook.api.entity.Permission;
import com.cnbg.zs.ebook.api.entity.RoleUser;
import com.cnbg.zs.ebook.api.mapper.PermissionMapper;
import com.cnbg.zs.ebook.api.mapper.RoleUserMapper;
import com.cnbg.zs.ebook.api.service.IRoleUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
* @author OFG
* @version 1.0
* @date 2021/1/6 20:09
* @Description
*/
@Service
public class IRoleUserServiceImpl implements IRoleUserService {

	@Autowired
	private RoleUserMapper roleUserMapper;
	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public List<RoleUser> getRoleUserForUser(Integer userId) {
		// 获取用户角色
		QueryWrapper<RoleUser> queryWrapper2 = new QueryWrapper<>();
		queryWrapper2.eq("user_id",userId);
		List<RoleUser> roleUsers = roleUserMapper.selectList(queryWrapper2);
		return roleUsers;
	}

	@Override
	public List<Permission> getPermissionForUser(Integer userId) {
		return permissionMapper.getPermissionByUserId(userId);
	}
	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void insertEntity(RoleUser roleUser) {
		roleUserMapper.insert(roleUser);
	}
	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void deletePrimaryKey(Integer id) {
		roleUserMapper.deleteById(id);
	}
	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void updateEntity(RoleUser roleUser) {
		roleUserMapper.updateById(roleUser);
	}

	@Override
	public RoleUserDTO selectByPrimaryKey(Integer id) {
		return roleUserMapper.selectById(id);
	}

	@Override
	public IPage<RoleUserDTO> selectEntityList(Page<RoleUserDTO> objectPage) {
		return roleUserMapper.selectEntityList(objectPage);
	}

	@Override
	public String[] selectUserRole(Integer userId) {
		List<String> urls = roleUserMapper.selectUserRole(userId);
		Set<String> stringSet = new HashSet<>();
		stringSet = urls.stream().collect(Collectors.toSet());
		return stringSet.toArray(new String[stringSet.size()]);
	}


}
