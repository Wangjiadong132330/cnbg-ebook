package com.cnbg.zs.ebook.api.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.core.controller.BaseController;
import com.cnbg.zs.ebook.api.entity.UserInfo;
import com.cnbg.zs.ebook.core.result.ResultData;
import com.cnbg.zs.ebook.api.service.IUserInfoService;
import com.cnbg.zs.ebook.api.vo.UserInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
* @author Faye.Wang
* @version 1.0
* @date 2021/1/6 14:03
* @Description
*/
@RestController
@RequestMapping("/api/user")
public class UserInfoController extends BaseController {

	@Autowired
	private IUserInfoService iUserInfoService;

	/**
	* 保存数据
	* @param record
	* @return
	*/
	@PostMapping("/insert")
	public ResultData insertUserInfo(@RequestBody UserInfoVo record){
		UserInfo entity = new UserInfo();
		BeanUtils.copyProperties(record,entity);
		entity.setUsername(record.getUserAccount());
		entity.setPassword(new BCryptPasswordEncoder().encode(record.getUserPass()));
		iUserInfoService.insertEntity(entity);
		return super.resultSuccess();
	}
	/**
	* 修改数据
	* @param record
	* @return
	*/
	@PostMapping("/update")
	public ResultData updateDemo(@RequestBody UserInfoVo record){
		UserInfo entity = new UserInfo();
		BeanUtils.copyProperties(record,entity);
		iUserInfoService.updateEntity(entity);
		return super.resultSuccess();
	}
	/**
	* 查询数据
	* @param record
	* @return
	*/
	@PostMapping("/getId")
	public ResultData getUserInfoById(@RequestBody UserInfoVo record){
		return super.resultSuccess(iUserInfoService.selectByPrimaryKey(record.getId()));
	}

	/**
	* 删除数据
	* @param record
	* @return
	*/
	@PostMapping("/delete")
	public ResultData deleteDemoById(@RequestBody UserInfoVo record){
		iUserInfoService.deletePrimaryKey(record.getId());
		return super.resultSuccess();
	}

	/**
	* 分页查询数据
	* @param record
	* @return
	*/
	@PostMapping("/getPageQuery")
	public ResultData getPageQuery(@RequestBody UserInfoVo record){
		UserInfo entity = new UserInfo();
		BeanUtils.copyProperties(record,entity);

		return super.resultSuccess(iUserInfoService.selectEntityList(new Page<>(record.getPageNo(), record.getPageSize()),entity));
	}

}
