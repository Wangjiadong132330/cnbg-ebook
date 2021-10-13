package com.cnbg.zs.ebook.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.dto.ExcelUserInfoDTO;
import com.cnbg.zs.ebook.api.dto.TemplateFileDTO;
import com.cnbg.zs.ebook.api.entity.Template;
import com.cnbg.zs.ebook.api.entity.UserInfo;
import com.cnbg.zs.ebook.api.service.ITemplateService;
import com.cnbg.zs.ebook.api.service.IUserInfoService;
import com.cnbg.zs.ebook.api.service.impl.ITemplateImpl;
import com.cnbg.zs.ebook.api.utils.SessionUtils;
import com.cnbg.zs.ebook.api.vo.UserInfoVo;
import com.cnbg.zs.ebook.common.excel.ExcelUtils;
import com.cnbg.zs.ebook.core.controller.BaseController;
import com.cnbg.zs.ebook.core.result.RequestVo;
import com.cnbg.zs.ebook.core.result.ResultData;
import com.cnbg.zs.ebook.core.result.ResultEnum;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;


/**
* @author Faye.Wang
* @version 1.0
* @date 2021/10/11 10:26
* @Description
*/
@RestController
@RequestMapping("/api/files")
public class FilesController extends BaseController {

	@Autowired
	private ITemplateService iTemplateService;

	/**
	* 上传模板背景图
	* @param request
	* @return
	*/
	@PostMapping("/uploadImg")
	public ResultData uploadTemplateImg(@RequestBody UserInfoVo userInfoVo,HttpServletRequest request) throws IOException {
		//空文件流处理
		MultipartFile file = null;
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			MultipartHttpServletRequest multipartRequest = WebUtils.getNativeRequest(request,
					MultipartHttpServletRequest.class);
			file = multipartRequest.getFile("file");
		}
		//获得登录用户的主键id
		Integer uploaderId = SessionUtils.getSessionUserId(userInfoVo.getSessionId());
		int flag = iTemplateService.uploadFile(uploaderId,file);
		if(flag == 1){
			return super.resultSuccess();
		}else if(flag == 2){
			return super.resultFail("图片格式错误");
		}else {
			return super.resultFail("上传失败");
		}
	}

	/**
	 * 返回所有模板图信息
	 * @return
	 */
	@PostMapping("/getAllTemplateUrl")
	public ResultData getAllTemplateUrl(){
		List<Template> templateList = iTemplateService.selectAllTemplateInfo();
		return super.resultSuccess(templateList);
	}
}
