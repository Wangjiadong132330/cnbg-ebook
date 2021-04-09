package com.cnbg.zs.ebook.core.result;

/**
* @author Faye.Wang
* @version 1.0
* @date 2020/9/4 22:06
* @Description
*/
public enum ResultEnum {
	HTTP_SUCCESS(200,"请求成功"),
	HTTP_SMS_FAIL_201(201, "您操作过于频繁，请检查网络，30分钟后再次尝试发送"),
	HTTP_ERROR_100(100, "请求失败"),
	HTTP_ERROR_400(400, "请求的数据格式不符"),
	HTTP_ERROR_404(404, "未找到该资源"),
	HTTP_ERROR_500(500, "服务器内部错误"),

	MSG_CODE_ERROR_501(501, "节点名重复"),
	MSG_CODE_ERROR_502(502, "流程名重复"),

	HTTP_ERROR_403(403, "禁止访问 403 抱歉,页面无法访问"),
	HTTP_SMS_CODE_ERROR_202(202, "验证码错误"),
	HTTP_MSG_CODE_ERROR_203(203, "用户名已存在，请更换用户名再进行注册"),
	HTTP_MSG_CODE_ERROR_204(204, "用户名/密码错误"),
	HTTP_MSG_CODE_ERROR_205(205, "账号已被冻结，请联系管理员");

	private Integer code;
	private String message;

	ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
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
