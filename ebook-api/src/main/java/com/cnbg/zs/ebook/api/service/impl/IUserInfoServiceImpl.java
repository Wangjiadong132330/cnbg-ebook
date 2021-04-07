package com.cnbg.zs.ebook.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.dto.UserInfoDto;
import com.cnbg.zs.ebook.api.entity.UserInfo;
import com.cnbg.zs.ebook.api.mapper.UserInfoMapper;
import com.cnbg.zs.ebook.api.service.IUserInfoService;
import com.cnbg.zs.ebook.common.lang.StringToolUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	public UserInfoDto selectByPrimaryKey(Integer id) {
		UserInfo userInfo = userInfoMapper.selectById(id);
		UserInfoDto userInfoDto = new UserInfoDto();
		BeanUtils.copyProperties(userInfo,userInfoDto);
		return userInfoDto;
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void deletePrimaryKey(Integer id) {
		userInfoMapper.deleteById(id);
	}

	@Override
	public IPage<UserInfo> selectEntityList(Page<UserInfo> page,UserInfo record) {
		QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();

				wrapper.eq(!StringToolUtils.isEmptyObj(record.getCompanyId()),"company_id",record.getCompanyId());
				wrapper.eq(!StringToolUtils.isEmptyObj(record.getDepartmentId()),"department_id",record.getDepartmentId());
				wrapper.like(!StringToolUtils.isEmptyObj(record.getUserRealName()),"user_real_name",record.getUserRealName());
		return userInfoMapper.selectPage(page,wrapper);
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
