package com.cnbg.zs.ebook.api.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.service.IDepartmentService;
import com.cnbg.zs.ebook.api.utils.SessionUtils;
import com.cnbg.zs.ebook.core.controller.BaseController;
import com.cnbg.zs.ebook.api.entity.Company;
import com.cnbg.zs.ebook.core.result.ResultData;
import com.cnbg.zs.ebook.api.service.ICompanyService;
import com.cnbg.zs.ebook.api.vo.CompanyVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
* @author Faye.Wang
* @version 1.0
* @date 2021/1/6 14:03
* @Description
*/
@RestController
@RequestMapping("/api/company")
public class CompanyController extends BaseController {

	@Autowired
	private ICompanyService iCompanyService;
	@Autowired
	private IDepartmentService iDepartmentService;

	/**
	* 保存数据
	* @param record
	* @return
	*/
	@PostMapping("/insert")
	public ResultData insertCompany(@RequestBody CompanyVo record){
		Company entity = new Company();
		if(iCompanyService.selectByName(record.getCompanyName())!=null){
			return super.resultSuccess("企业名称已存在",null);
		}else{
			BeanUtils.copyProperties(record,entity);
			entity.setCreateTime(new Date());
			entity.setCreateUser(SessionUtils.getSessionUserName(record.getSessionId()));
			iCompanyService.insertEntity(entity);

			return super.resultSuccess();
		}


	}
	/**
	* 修改数据
	* @param record
	* @return
	*/
	@PostMapping("/update")
	public ResultData updateDemo(@RequestBody CompanyVo record){
		Company entity = new Company();
		BeanUtils.copyProperties(record,entity);
		entity.setCreateTime(new Date());
		entity.setCreateUser(SessionUtils.getSessionUserName(record.getSessionId()));
		iCompanyService.updateEntity(entity);
		return super.resultSuccess();
	}
	/**
	* 查询数据
	* @param record
	* @return
	*/
	@PostMapping("/getId")
	public ResultData getCompanyById(@RequestBody CompanyVo record){
		return super.resultSuccess(iCompanyService.selectByPrimaryKey(record.getId()));
	}

	/**
	* 删除数据
	* @param record
	* @return
	*/
	@PostMapping("/delete")
	public ResultData deleteDemoById(@RequestBody CompanyVo record){
		iCompanyService.deletePrimaryKey(record.getId());
		return super.resultSuccess();
	}

	/**
	* 分页查询数据
	* @param record
	* @return
	*/
	@PostMapping("/getPageQuery")
	public ResultData getPageQuery(@RequestBody CompanyVo record){
		Company entity = new Company();
		BeanUtils.copyProperties(record,entity);

		return super.resultSuccess(iCompanyService.selectEntityList(new Page<>(record.getPageNo(), record.getPageSize()),entity));
	}

	/**
	 * 查询公司下拉
	 * @param record
	 * @return
	 */
	@PostMapping("/getDropDownQuery")
	public ResultData getDropDownQuery(@RequestBody CompanyVo record){


		return super.resultSuccess(iCompanyService.getDropDownQuery());
	}

}
