package com.cnbg.zs.ebook.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.dto.UserInfoDTO;
import com.cnbg.zs.ebook.api.entity.UserInfo;
import com.cnbg.zs.ebook.api.mapper.UserInfoMapper;
import com.cnbg.zs.ebook.api.service.IUserInfoService;
import com.cnbg.zs.ebook.common.lang.StringToolUtils;
import org.springframework.beans.BeanUtils;
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
public class IUserInfoServiceImpl implements IUserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void insertEntity(UserInfo record) {
		userInfoMapper.insert(record);
	}

	@Override
	public UserInfoDTO selectByPrimaryKey(Integer id) {
		UserInfo userInfo = userInfoMapper.selectById(id);
		UserInfoDTO userInfoDto = new UserInfoDTO();
		BeanUtils.copyProperties(userInfo,userInfoDto);
		return userInfoDto;
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void deletePrimaryKey(Integer id) {
		userInfoMapper.deleteById(id);
	}

	@Override
	public IPage<UserInfoDTO> selectEntityList(Page<UserInfoDTO> page,UserInfo record) {
		Map<String,Object> paramsMap = new HashMap<>();
		paramsMap.put("companyId",StringToolUtils.isEmptyValue(record.getCompanyId()));
		paramsMap.put("departmentId",StringToolUtils.isEmptyValue(record.getDepartmentId()));
		paramsMap.put("userRealName",StringToolUtils.isEmptyValue(record.getUserRealName()));
		return userInfoMapper.selectEntityList(page,paramsMap);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void updateEntity(UserInfo record) {
		userInfoMapper.updateById(record);
	}

	@Override
	public UserInfo loadUserInfoByName(String userName) {
		QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_account",userName);
		UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
		return userInfo;
	}
}
