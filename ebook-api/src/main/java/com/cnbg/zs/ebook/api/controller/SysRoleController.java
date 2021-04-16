package com.cnbg.zs.ebook.api.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.core.controller.BaseController;
import com.cnbg.zs.ebook.api.entity.SysRole;
import com.cnbg.zs.ebook.api.entity.SysRolePermission;
import com.cnbg.zs.ebook.api.entity.NodeRole;
import com.cnbg.zs.ebook.api.dto.SysRoleMultiDTO;
import com.cnbg.zs.ebook.core.result.ResultData;
import com.cnbg.zs.ebook.api.service.ISysRoleService;
import com.cnbg.zs.ebook.api.vo.SysRoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
* @author ofg
* @version 1.0
* @date 2021/1/6 14:03
* @Description
*/
@RestController
@RequestMapping("/api/role")
public class SysRoleController extends BaseController {

	@Autowired
	private ISysRoleService iSysRoleService;

	/**
	* 保存数据
	* @param record
	* @return
	*/
	@PostMapping("/insert")
	public ResultData insertSysRole(@RequestBody SysRoleVo record){
		SysRole sysRole = new SysRole();
		BeanUtils.copyProperties(record,sysRole);
		iSysRoleService.insertEntity(sysRole,record.getPermissionIds(),record.getNodeIds());
		return super.resultSuccess();
	}
	/**
	* 修改数据
	* @param record
	* @return
	*/
	@PostMapping("/update")
	public ResultData updateDemo(@RequestBody SysRoleVo record){
		SysRole sysRole = new SysRole();
		BeanUtils.copyProperties(record,sysRole);
		iSysRoleService.updateEntity(sysRole,record.getPermissionIds(),record.getNodeIds());
		return super.resultSuccess();
	}
	/**
	* 查询数据
	* @param record
	* @return
	*/
	@PostMapping("/getId")
	public ResultData getSysRoleById(@RequestBody SysRoleVo record){
		return super.resultSuccess(iSysRoleService.selectByPrimaryKey(record.getId()));
	}

	/**
	* 删除数据
	* @param record
	* @return
	*/
	@PostMapping("/delete")
	public ResultData deleteDemoById(@RequestBody SysRoleVo record){
		iSysRoleService.deletePrimaryKey(record.getId());
		return super.resultSuccess();
	}

	/**
	* 分页查询数据
	* @param record
	* @return
	*/
	@PostMapping("/getPageQuery")
	public ResultData getPageQuery(@RequestBody SysRoleVo record){
		SysRoleMultiDTO entity = new SysRoleMultiDTO();
		BeanUtils.copyProperties(record,entity);

		return super.resultSuccess(iSysRoleService.selectEntityList(new Page<>(record.getPageNo(), record.getPageSize()),entity));
	}

	/**
	 * 获取菜单
	 * @param record
	 * @return
	 */
	@PostMapping("/getMenuList")
	public ResultData getMenuList(@RequestBody SysRoleVo record){

		return super.resultSuccess(iSysRoleService.getMenuList());
	}

	/**
	 * 获取节点树
	 * @param record
	 * @return
	 */
	@PostMapping("/getNodeList")
	public ResultData getNodeList(@RequestBody SysRoleVo record){

		return super.resultSuccess(iSysRoleService.getNodeList());
	}


	/**
	 * 获取节点树
	 * @param 
	 * @return
	 */
	@PostMapping("/getRoleList")
	public ResultData getRoleList(){

		return super.resultSuccess(iSysRoleService.getRoleList());
	}


}
