package com.cnbg.zs.ebook.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.dto.RoleUserDTO;
import com.cnbg.zs.ebook.api.entity.Permission;
import com.cnbg.zs.ebook.api.entity.RoleUser;

import java.util.List;

/**
* @author Faye.Wang
* @version 1.0
* @date 2021/1/6 14:49
* @Description
*/
public interface IRoleUserService {
	/**
	 * 查询用户当前的角色列表
	 * @param userId
	 * @return
	 */
	List<RoleUser> getRoleUserForUser(Integer userId);

	/**
	 * 查询用户访问权限URL
	 * @param userId
	 * @return
	 */
	List<Permission> getPermissionForUser(Integer userId);


	/**
	 * 保存数据
	 * @param
	 */
	void insertEntity(RoleUser roleUser);

	/**
	 * 根据主键删除数据
	 * @param id
	 * @return
	 */
	void deletePrimaryKey(Integer id);

	/**
	 * 修改记录
	 * @param
	 */
	void updateEntity(RoleUser roleUser);


	RoleUserDTO selectByPrimaryKey(Integer id);


	IPage<RoleUserDTO> selectEntityList(Page<RoleUserDTO> objectPage);


	/**
	 * 获取登陆者菜单权限ID
	 * @param userId
	 * @return
	 */
	String[] selectUserRole(Integer userId);

}
