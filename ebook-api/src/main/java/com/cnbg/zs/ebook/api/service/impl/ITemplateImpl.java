package com.cnbg.zs.ebook.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cnbg.zs.ebook.api.entity.Template;
import org.apache.commons.lang3.StringUtils;
import com.cnbg.zs.ebook.api.mapper.TemplateImgMapper;
import com.cnbg.zs.ebook.api.service.ITemplateService;
import com.cnbg.zs.ebook.common.file.EnviromentUtils;
import com.cnbg.zs.ebook.common.lang.StringToolUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
* @author Faye.Wang
* @version 1.0
* @date 2021/10/12 13:19
* @Description
*/
@Service
public class ITemplateImpl implements ITemplateService {

	/**
	 * 上传的文件存储路径参数配置key
	 */
	public static final String FILE_STORE_PATH = "file.store.path";
	private static final Logger log = LoggerFactory.getLogger(ITemplateImpl.class);
	private static final List<String> FILETYPELIST = new ArrayList<>();

	@Value("${file.store.path}")
	private String fileStorePath;

	@Value("${file.mapping.name}")
	private String fileMappingName;

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
	public int uploadFile(Integer uploaderId,MultipartFile file) {
		int flag = 0;
		//获取文件的类型
		String contentType = file.getContentType();
		//判断文件类型匹配
		if(FILETYPELIST.contains(contentType)) {
			//获取文件名称
			String fileName = FilenameUtils.getName(file.getOriginalFilename());
			//获取文件url
			String destFileName = getNewFileName(fileName);
			File destFile = getFile(destFileName);
			try {
				file.transferTo(destFile);
				//将图片模板文件信息存储到template_files表
				Template template = new Template();
				template.setFileName(fileName);
				template.setFileUrl(fileStorePath + "/" + destFileName);
				//设置上传用户的id
				template.setCreateUser(uploaderId.toString());
				flag = templateImgMapper.insert(template);
			} catch (IOException e) {
				log.error("文件保存异常", e);
				throw new RuntimeException("文件保存异常");
			}
		}else {
			flag = 2;
		}
		return flag;
	}

	/**
	 * 查询所有模板文件名和路径
	 */
	@Override
	public List<Template> selectAllTemplateInfo() {
		QueryWrapper<Template> queryWrapper = new QueryWrapper<>();
		List<Template> templateList = templateImgMapper.selectList(queryWrapper);
		for(Template template : templateList){
			template.setFileUrl("82.156.225.241:8080" + template.getFileUrl());
		}
		//结果倒序
		Collections.reverse(templateList);
		return templateList;
	}

	/**
	 * 获取完整的文件信息
	 *
	 * @param fileName 文件名称
	 * @return 文件信息
	 */
	private File getFile(String fileName) {
		String storeFilePath = EnviromentUtils.getValueByEnv(FILE_STORE_PATH, StringUtils.EMPTY);
		if (StringUtils.isBlank(storeFilePath)) {
			throw new RuntimeException("找不到配置{0}");
		}
		isChartPathExist(storeFilePath);
		return new File(new StringBuilder(storeFilePath).append(File.separator).append(fileName).toString());
	}

	/**
	 * 根据原始环境的文件名生成一个新的文件名
	 *
	 * @param oldFileName 原始文件名
	 * @return 新的文件名
	 */
	private String getNewFileName(String oldFileName) {
		String suffix = FilenameUtils.getExtension(oldFileName);
		if (suffix.isEmpty()) {
			return new StringBuilder(StringToolUtils.getGeneratorUUID()).toString();
		}
		return new StringBuilder(StringToolUtils.getGeneratorUUID()).append(".").append(suffix).toString();
	}

	/**
	 * 判断文件夹是否存在，如果不存在则创建
	 *
	 * @param dirPath 文件夹磁盘路径
	 * @return 文件信息
	 */
	private static void isChartPathExist(String dirPath) {
		File file = new File(dirPath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
}
