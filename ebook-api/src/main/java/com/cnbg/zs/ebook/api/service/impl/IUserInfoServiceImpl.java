package com.cnbg.zs.ebook.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cnbg.zs.ebook.api.dto.ExcelUserInfoDTO;
import com.cnbg.zs.ebook.api.dto.ImportResultDTO;
import com.cnbg.zs.ebook.api.dto.UserInfoDTO;
import com.cnbg.zs.ebook.api.entity.Company;
import com.cnbg.zs.ebook.api.entity.Department;
import com.cnbg.zs.ebook.api.entity.UserInfo;
import com.cnbg.zs.ebook.api.mapper.UserInfoMapper;
import com.cnbg.zs.ebook.api.service.ICompanyService;
import com.cnbg.zs.ebook.api.service.IDepartmentService;
import com.cnbg.zs.ebook.api.service.IUserInfoService;
import com.cnbg.zs.ebook.api.vo.UserInfoVo;
import com.cnbg.zs.ebook.common.constant.Constants;
import com.cnbg.zs.ebook.common.excel.ExcelUtils;
import com.cnbg.zs.ebook.common.lang.StringToolUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
* @author Faye.Wang
* @version 1.0
* @date 2021/1/6 20:09
* @Description
*/
@Service
public class IUserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>  implements IUserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Autowired
	private IDepartmentService iDepartmentService;

	@Autowired
	private ICompanyService iCompanyService;

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

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public ImportResultDTO importUser(List<ExcelUserInfoDTO> userInfoDtoList, String userName) {

		ImportResultDTO importResult = new ImportResultDTO();

		// 导入总数
		int importCount = 0;

		// 存在总数
		int existCount = 0;

		if (CollectionUtils.isEmpty(userInfoDtoList)) {
			return importResult;
		}

		// 获得所有公司List
		Company company = new Company();
		List<Company> companyList = iCompanyService.selectEntityList(company);
		Map<String, Integer> companyMap =
				companyList.stream().collect(Collectors.toMap(Company::getCompanyShortName, Company::getId));

		// 获得所有部门List
		Department department = new Department();
		List<Department> departmentList = iDepartmentService.selectEntityList(department);
		Map<String, Integer> departmentMap =
				departmentList.stream().collect(Collectors.toMap(Department::getDepartmentShortName, Department::getId));

		// 获得所有用户List
		QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
		List<UserInfo> allUserList = userInfoMapper.selectList(wrapper);
		Map<String, UserInfo> allUserMap = allUserList.stream().collect(Collectors.toMap(UserInfo::getUsername, item -> item));

		List<UserInfo> userInfoList = new ArrayList<>();
		for (ExcelUserInfoDTO userInfo : userInfoDtoList) {

			// 会员号已经存在时
			if (allUserMap.get(userInfo.getUserAccount()) != null) {

				existCount++;
				continue;

			} else {

				importCount++;

				UserInfo info = new UserInfo();
				// 会员号
				info.setUsername(userInfo.getUserAccount());
				// 姓名
				info.setUserRealName(userInfo.getUserRealName());
				// 公司Id
				info.setCompanyId(companyMap.get(userInfo.getCompanyName()));
				// 公司Id
				info.setDepartmentId(departmentMap.get(userInfo.getDepartmentName()));
				// 手机号
				info.setPhone(userInfo.getPhone());
				// 性别
				info.setGender(userInfo.getGender());
				// 设置默认密码
				info.setPassword(new BCryptPasswordEncoder().encode(Constants.DEFAULT_PASSWORD));
				// 状态 1-正常
				info.setStatus(Constants.ONE);
				// 创建时间
				info.setCreateTime(new Date());
				// 创建者
				info.setCreateUser(userName);

				// 最加新用户
				allUserMap.put(userInfo.getUserAccount(), info);

				userInfoList.add(info);
			}
		}

		// 保存数据
		this.saveBatch(userInfoList);

		// 设定存在总数
		importResult.setExistCount(existCount);
		// 设定导入总数
		importResult.setImportCount(importCount);

		return importResult;
	}

	@Override
	public UserInfoDTO getUserInfoById(Integer userId) {
		return userInfoMapper.getUserInfoById(userId);
	}
	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
	@Override
	public void updateUserProfile(UserInfo userInfo) {
		userInfoMapper.updateById(userInfo);
	}

	@Override
	public void exportUser(HttpServletResponse response, UserInfoVo record) {

		Map<String,Object> paramsMap = new HashMap<>();
		paramsMap.put("companyId",StringToolUtils.isEmptyValue(record.getCompanyId()));
		paramsMap.put("departmentId",StringToolUtils.isEmptyValue(record.getDepartmentId()));
		paramsMap.put("userRealName",StringToolUtils.isEmptyValue(record.getUserRealName()));

		List<UserInfoDTO> userInfoDtoList = userInfoMapper.selectEntityList(paramsMap);

		List<ExcelUserInfoDTO> exportUserInfoDtoList = new ArrayList<>();
		for (UserInfoDTO dto : userInfoDtoList) {

			ExcelUserInfoDTO excelUserInfo = new ExcelUserInfoDTO();
			excelUserInfo.setUserAccount(dto.getUsername());
			excelUserInfo.setUserRealName(dto.getUserRealName());
			excelUserInfo.setCompanyName(dto.getCompanyName());
			excelUserInfo.setDepartmentName(dto.getDepartmentName());
			excelUserInfo.setGender(dto.getGender());
			excelUserInfo.setPhone(dto.getPhone());

			exportUserInfoDtoList.add(excelUserInfo);
		}

		ExcelUtils.writeExcel(response, exportUserInfoDtoList, "exportUser", "sheet1", ExcelUserInfoDTO.class);
	}

	@Override
	public List<UserInfo> getSysUserSelect() {
		QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
		queryWrapper.select("id,user_real_name");
		return userInfoMapper.selectList(queryWrapper);
	}

}
