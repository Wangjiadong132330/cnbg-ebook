package com.cnbg.zs.ebook.api.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.dto.ExcelUserInfoDTO;
import com.cnbg.zs.ebook.api.utils.SessionUtils;
import com.cnbg.zs.ebook.common.excel.ExcelUtils;
import com.cnbg.zs.ebook.core.controller.BaseController;
import com.cnbg.zs.ebook.api.entity.UserInfo;
import com.cnbg.zs.ebook.core.result.RequestVo;
import com.cnbg.zs.ebook.core.result.ResultData;
import com.cnbg.zs.ebook.api.service.IUserInfoService;
import com.cnbg.zs.ebook.api.vo.UserInfoVo;
import org.apache.poi.sl.usermodel.Sheet;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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

	@Value("${sys.init.pass}")
	private String initPass;

	/**
	* 保存数据
	* @param record
	* @return
	*/
	@PostMapping("/insert")
	public ResultData insertUserInfo(@RequestBody UserInfoVo record){
		if(iUserInfoService.loadUserInfoByName(record.getUserAccount())!=null){
			return super.resultSuccess("用户名以存在，请更换",null);
		}else{
			UserInfo entity = new UserInfo();
			BeanUtils.copyProperties(record,entity);
			entity.setUsername(record.getUserAccount());
			entity.setPassword(new BCryptPasswordEncoder().encode(initPass));
			entity.setStatus(1);
			entity.setCreateTime(new Date());
			entity.setCreateUser(SessionUtils.getSessionUserName(record.getSessionId()));
			iUserInfoService.insertEntity(entity);
			return super.resultSuccess();
		}
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
	 * 导入用户
	 * @param file
	 * @param requestVo
	 * @return
	 */
	@PostMapping("/importUser")
	public ResultData importUser(@RequestParam(value="file") MultipartFile file, @RequestBody RequestVo requestVo) {

		List<ExcelUserInfoDTO> userInfoList = ExcelUtils.readExcel(file, ExcelUserInfoDTO.class, 0, 1);

		return super.resultSuccess(iUserInfoService.importUser(userInfoList, SessionUtils.getSessionUserName(requestVo.getSessionId())));
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

	@GetMapping(value = "/exportUser")
	public void exportUser(HttpServletResponse response) {

		List<ExcelUserInfoDTO> userInfoDtoList = new ArrayList<>();
		ExcelUserInfoDTO e;

		for (int i = 0; i < 10; i++) {
			e = new ExcelUserInfoDTO();
			// 会员号
			e.setUserAccount("userAccount" + i);
			// 姓名
			e.setUserRealName("userRealName" + i);
			// 公司Id
			e.setCompanyName("companyName" + i);
			// 公司Id
			e.setDepartmentName("departmentName" + i);
			// 手机号
			e.setPhone("Phone" + i);
			// 性别
			e.setGender("gender" + i);

			userInfoDtoList.add(e);
		}

		ExcelUtils.writeExcel(response, userInfoDtoList, "exportUser", "用户列表", ExcelUserInfoDTO.class);
	}



}
