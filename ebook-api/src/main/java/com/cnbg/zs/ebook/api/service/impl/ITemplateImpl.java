package com.cnbg.zs.ebook.api.service.impl;

import com.cnbg.zs.ebook.api.dto.TemplateFileDTO;
import com.cnbg.zs.ebook.api.mapper.TemplateImgMapper;
import com.cnbg.zs.ebook.api.service.ICompanyService;
import com.cnbg.zs.ebook.api.service.IDepartmentService;
import com.cnbg.zs.ebook.api.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


/**
* @author Faye.Wang
* @version 1.0
* @date 2021/10/12 13:19
* @Description
*/
@Service
public class ITemplateImpl implements ITemplateService {

	private static final List<String> FILETYPELIST = new ArrayList<>();

	static {
		FILETYPELIST.add("image/jpg");
		FILETYPELIST.add("image/jpeg");
		FILETYPELIST.add("image/png");
		FILETYPELIST.add("image/gif");
		FILETYPELIST.add("image/bmp");
	}

	@Autowired
	private TemplateImgMapper templateImgMapper;

	@Override
	public int uploadFile(MultipartFile file) {
		//获取文件的类型
		String contentType = file.getContentType();
		//判断文件类型匹配
		if(FILETYPELIST.contains(contentType)) {

		}
		return 0;
	}

	/**
	 * 查询所有模板文件名和路径
	 */
	@Override
	public List<TemplateFileDTO> selectAllTemplateInfo() {
		List<TemplateFileDTO> templateList = templateImgMapper.selectAllTemplateInfo();
		return templateList;
	}
}
