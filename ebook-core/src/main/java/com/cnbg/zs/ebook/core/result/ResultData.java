package com.cnbg.zs.ebook.core.result;

import java.io.Serializable;

/**
* @author Faye.Wang
* @version 1.0
* @date 2020/9/4 21:53
* @Description
*/
public class ResultData <T> implements Serializable {
	private int code;
	private String message;
	private T data;
	public ResultData(){

	}
	public ResultData(int code, String message, T data){
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public ResultData(int code, String message){
		this(code,message,null);
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
