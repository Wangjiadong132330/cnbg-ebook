package com.cnbg.zs.ebook.common.constant;

/**
* @author Faye.Wang
* @version 1.0
* @date 2020/9/9 22:33
* @Description
*/
public class Constants {
    public static final String HTTP_UTF8="utf-8";
    // 短信验证码过期时间为5分钟
    public static final int MSG_CODE_TIME= 60 * 5;
    // 短信验证码此处累加过期时间：5次后，30分钟后在进行发送
    public static final int MSG_COUNT_CODE_TIME= 60 * 30;
    // 短信REDIS KEY 前缀
    public static final String USER_SEND_REDIS_KEY= "USER:PHONE:";
    // 短信超出数量REDIS KEY 前缀
    public static final String USER_SEND_REDIS_EXPIRE= "USER:PHONE:EXPIRE:";
    // 拒绝发短信次数
    public static final int NOT_ALLOW_SEND_MSG= 5;
    // 用户SESSION信息
    public static final String USER_OAUTH_SESSION_ID= "USER:SESSION:";


    // PC方式登录 0，手机验证码登录方式为1
    public static final int USER_LOGIN_TYPE = 0;
    // 状态标识字典
    public static final Integer ZERO = 0;
    public static final Integer ONE = 1;
    public static final Integer TWO = 2;

    public static final String STRING_ZERO = "0";
    public static final String STRING_ONE = "1";
    public static final String STRING_TWO = "2";

    // 导入用户默认密码
    public static final String DEFAULT_PASSWORD = "123456";

    public static final String nodeUsedKey = "\"firstLevelMenu\":";
}
