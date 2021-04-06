package com.cnbg.zs.ebook.api.controller;

import com.cnbg.zs.ebook.common.lang.StringToolUtils;
import com.cnbg.zs.ebook.common.redis.JRedisUtils;
import com.cnbg.zs.ebook.core.controller.BaseController;
import com.cnbg.zs.ebook.core.result.ResultData;
import com.wf.captcha.SpecCaptcha;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Faye.Wang
 * @version 1.0
 * @date 2021/4/2 15:33
 * @Description
 */
@RestController
@RequestMapping("/api")
public class CommonController extends BaseController {
    @PostMapping("/captcha")
    public ResultData captcha(HttpServletRequest request, HttpServletResponse response){
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        // 验证码
        String verCode = specCaptcha.text();
        String verCodeKey = StringToolUtils.getGeneratorUUID();
        // 存入redis并设置过期时间为30分钟
        JRedisUtils.setKeyValue(verCodeKey,verCode,1800 );
        Map<String,Object> jsonData = new HashMap<>();
        jsonData.put("image",specCaptcha.toBase64());
        jsonData.put("uk",verCodeKey);
        return super.resultSuccess(jsonData);
    }
}
