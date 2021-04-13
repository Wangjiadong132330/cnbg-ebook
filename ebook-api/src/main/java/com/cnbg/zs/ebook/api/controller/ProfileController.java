package com.cnbg.zs.ebook.api.controller;

import com.cnbg.zs.ebook.api.dto.UserInfoDTO;
import com.cnbg.zs.ebook.api.entity.UserInfo;
import com.cnbg.zs.ebook.api.service.IUserInfoService;
import com.cnbg.zs.ebook.api.utils.SessionUtils;
import com.cnbg.zs.ebook.api.vo.UserInfoVo;
import com.cnbg.zs.ebook.core.controller.BaseController;
import com.cnbg.zs.ebook.core.result.ResultData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Faye.Wang
 * @version 1.0
 * @date 2021/4/13 9:58
 * @Description 个人信息
 */
@RestController
@RequestMapping("/api/profile")
public class ProfileController extends BaseController {
    @Autowired
    private IUserInfoService iUserInfoService;

    /**
     * 查询用户信息
     * @param userInfoVo 空
     * @return
     */
    @RequestMapping("/getUserProfile")
    public ResultData getUserProfile(@RequestBody UserInfoVo userInfoVo){
        UserInfoDTO userInfoDTO = iUserInfoService.getUserInfoById(SessionUtils.getSessionUserId(userInfoVo.getSessionId()));
        return super.resultSuccess(userInfoDTO);
    }

    /**
     * 用户修改资料
     * @param userInfoVo 用户信息
     * @return
     */
    @RequestMapping("/updateUserProfile")
    public ResultData updateUserProfile(@RequestBody UserInfoVo userInfoVo){
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoVo,userInfo);
        userInfo.setId(SessionUtils.getSessionUserId(userInfoVo.getSessionId()));
        iUserInfoService.updateUserProfile(userInfo);
        return super.resultSuccess();
    }

    /**
     * 用户修改资料
     * @param userInfoVo 用户信息
     * @return
     */
    @RequestMapping("/updateUserPass")
    public ResultData updateUserPass(@RequestBody UserInfoVo userInfoVo){
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword(new BCryptPasswordEncoder().encode(userInfoVo.getUserPass()));
        userInfo.setId(SessionUtils.getSessionUserId(userInfoVo.getSessionId()));
        iUserInfoService.updateUserProfile(userInfo);
        return super.resultSuccess();
    }

}
