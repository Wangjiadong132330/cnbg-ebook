package com.cnbg.zs.ebook.core.result;

/**
* @author Faye.Wang
* @version 1.0
* @date 2020/10/28 9:58
* @Description
*/
public class RequestVo {
	private Integer pageNo;
	private Integer pageSize;
	private String sessionId;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
