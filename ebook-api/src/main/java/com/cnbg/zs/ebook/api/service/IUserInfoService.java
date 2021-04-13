package com.cnbg.zs.ebook.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.dto.ExcelUserInfoDTO;
import com.cnbg.zs.ebook.api.dto.ImportResultDTO;
import com.cnbg.zs.ebook.api.dto.UserInfoDTO;
import com.cnbg.zs.ebook.api.entity.UserInfo;

import java.util.List;

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
	UserInfoDTO selectByPrimaryKey(Integer id);

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
	IPage<UserInfoDTO> selectEntityList(Page<UserInfoDTO> page,UserInfo entity);

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


	/**
	 * 导入用户信息
	 *
	 * @param userInfoDtoList
	 * @param userName
	 * @return
	 */
	ImportResultDTO importUser(List<ExcelUserInfoDTO> userInfoDtoList, String userName);

	/**
	 * 查询用户个人信息
	 * @param userId
	 * @return
	 */
	UserInfoDTO getUserInfoById(Integer userId);

	/**
	 * 根据用户ID 修改用户信息
	 * @param userInfo
	 */
	void updateUserProfile(UserInfo userInfo);



}
