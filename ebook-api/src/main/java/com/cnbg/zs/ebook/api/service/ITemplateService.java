package com.cnbg.zs.ebook.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.dto.ExcelUserInfoDTO;
import com.cnbg.zs.ebook.api.dto.ImportResultDTO;
import com.cnbg.zs.ebook.api.dto.TemplateFileDTO;
import com.cnbg.zs.ebook.api.dto.UserInfoDTO;
import com.cnbg.zs.ebook.api.entity.UserInfo;
import com.cnbg.zs.ebook.api.vo.UserInfoVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
* @author Faye.Wang
* @version 1.0
* @date 2021/10/12 12:09
* @Description
*/
public interface ITemplateService {
	/**
	 * 上传图片
	 * @return
	 */
	public int uploadFile(MultipartFile file);

	/**
	* 查询所有模板信息
	* @return
	*/
	public List<TemplateFileDTO> selectAllTemplateInfo();
}
