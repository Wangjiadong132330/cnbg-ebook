package com.cnbg.zs.ebook.api.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.entity.Company;
import com.cnbg.zs.ebook.api.entity.RoleUser;
import com.cnbg.zs.ebook.api.service.IRoleUserService;
import com.cnbg.zs.ebook.api.utils.SessionUtils;
import com.cnbg.zs.ebook.api.vo.CompanyVo;
import com.cnbg.zs.ebook.api.vo.RoleUserVo;
import com.cnbg.zs.ebook.api.vo.UserInfoVo;
import com.cnbg.zs.ebook.core.controller.BaseController;
import com.cnbg.zs.ebook.core.result.ResultData;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Faye.Wang
 * @version 1.0
 * @date 2021/4/9 13:56
 * @Description
 */
@RestController
@RequestMapping("/api/permission")
public class RoleUserController extends BaseController {
    @Autowired
    private IRoleUserService iRoleUserService;

    /**
     * 保存数据
     * @param record
     * @return
     */
    @PostMapping("/insert")
    public ResultData insertRoleUser(@RequestBody RoleUserVo record){


            RoleUser roleUser = new RoleUser();
            BeanUtils.copyProperties(record,roleUser);
            roleUser.setCreateTime(new Date());
            roleUser.setUserId(SessionUtils.getSessionUserId(record.getSessionId()));
            iRoleUserService.insertEntity(roleUser);
            return super.resultSuccess();
    }
    /**
     * 修改
     * @param record
     * @return
     */
    @PostMapping("/update")
    public ResultData updateRoleUser(@RequestBody RoleUserVo record){
        RoleUser roleUser = new RoleUser();
        BeanUtils.copyProperties(record,roleUser);
        roleUser.setCreateTime(new Date());
        roleUser.setUserId(SessionUtils.getSessionUserId(record.getSessionId()));
        iRoleUserService.updateEntity(roleUser);
        return super.resultSuccess();
    }
    /**
     * 修改
     * @param record
     * @return
     */
    @PostMapping("/delete")
    public ResultData deleteRoleUser(@RequestBody RoleUserVo record){
        iRoleUserService.deletePrimaryKey(record.getId());
        return super.resultSuccess();
    }

    /**
     * 修改
     * @param record
     * @return
     */
    @PostMapping("/getId")
    public ResultData getRoleUser(@RequestBody RoleUserVo record){

        return super.resultSuccess(iRoleUserService.selectByPrimaryKey(record.getId()));
    }

    /**
     * 分页查询数据
     * @param record
     * @return
     */
    @PostMapping("/getPageQuery")
    public ResultData getPageQuery(@RequestBody RoleUserVo record){


        return super.resultSuccess(iRoleUserService.selectEntityList(new Page<>(record.getPageNo(), record.getPageSize())));
    }


}
