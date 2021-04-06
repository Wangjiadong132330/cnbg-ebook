package com.cnbg.zs.ebook.api.security;

/**
* @author OFG
* @version 1.0
* @date 2021/3/24 21:20
* @Description
* Security 状态信息枚举类
*/
public enum  SecurityEnum {
	SUCCESS(101,"成功"),
	FAILURE(102,"失败"),
	USER_NEED_AUTHORITIES(201,"用户未登录"),
	USER_LOGIN_FAILED(202,"用户账号或密码错误"),
	USER_LOGIN_SUCCESS(203,"用户登录成功"),
	USER_NO_ACCESS(204,"用户无权访问"),
	USER_LOGOUT_SUCCESS(205,"用户登出成功"),
	TOKEN_IS_BLACKLIST(206,"此token为黑名单"),
	LOGIN_IS_OVERDUE(207,"登录已失效");
	private Integer code;

	private String message;

	SecurityEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public static SecurityEnum parse(int code){
		SecurityEnum[] values = values();
		for (SecurityEnum value : values) {
		if(value.getCode() == code){
			return value;
		}
	}
		throw  new RuntimeException("Unknown code of ResultEnum");
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
