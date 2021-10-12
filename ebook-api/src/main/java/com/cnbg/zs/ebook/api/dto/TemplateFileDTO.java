package com.cnbg.zs.ebook.api.dto;

/**
* @author OFG
* @version 1.0
* @date 2021/10/12 13:15
* @Description
*/
public class TemplateFileDTO {
	/**
	* 字段：id ：
	*/
	private String id;

	/**
	* 字段：fileName ：文件名称
	*/
	private String fileName;

	/**
	 * 字段：fileName ：文件名称
	 */
	private String fileUrl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
}
