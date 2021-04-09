package com.cnbg.zs.ebook.api.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cnbg.zs.ebook.api.entity.Company;

import java.util.Date;

/**
 * @author Faye.Wang
 * @version 1.0
 * @date 2021/4/9 10:06
 * @Description
 */
public class CompanyDTO {
    /**
     * 字段：id ：
     */
    private Integer id;

    /**
     * 字段：provincialId ：所属区域
     */
    private String provincialName;

    /**
     * 字段：companyName ：公司名称
     */
    private String companyName;

    /**
     * 字段：companyShortName ：公司简称
     */
    private String companyShortName;

    /**
     * 字段：createTime ：创建时间
     */
    private Date createTime;

    /**
     * 字段：createUser ：创建人
     */
    private String createUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvincialName() {
        return provincialName;
    }

    public void setProvincialName(String provincialName) {
        this.provincialName = provincialName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyShortName() {
        return companyShortName;
    }

    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
