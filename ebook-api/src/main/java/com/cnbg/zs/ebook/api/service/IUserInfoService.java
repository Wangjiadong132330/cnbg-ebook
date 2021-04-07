package com.cnbg.zs.ebook.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.dto.UserInfoDto;
import com.cnbg.zs.ebook.api.entity.UserInfo;

/**
* @author Faye.Wang
* @version 1.0
* @date 2021/1/6 14:49
* @Description
*/
public interface IUserInfoService {
	/**
	* 保存数据
	* @param entity
	*/
	void insertEntity(UserInfo entity);

	/**
	* 获取单条数据
	* @param id
	* @return
	*/
	UserInfoDto selectByPrimaryKey(Integer id);

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
	IPage<UserInfo> selectEntityList(Page<UserInfo> page,UserInfo entity);

	/**
	* 修改记录
	* @param entity
	*/
	void updateEntity(UserInfo entity);
	/**
	 * 根据用户名称获取 用户信息
	 * @param userName
	 * @return
	 */
	UserInfo loadUserInfoByName(String userName);

}
