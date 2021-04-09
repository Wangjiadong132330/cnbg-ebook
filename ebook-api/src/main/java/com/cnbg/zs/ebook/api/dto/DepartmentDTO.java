package com.cnbg.zs.ebook.api.dto;

import java.util.Date;

/**
 * @author Faye.Wang
 * @version 1.0
 * @date 2021/4/9 11:58
 * @Description
 */
public class DepartmentDTO {
    /**
     * 字段：id ：
     */
    private Integer id;

    /**
     * 字段：departmentName ：部门名称
     */
    private String departmentName;

    /**
     * 字段：departmentShortName ：部门简称
     */
    private String departmentShortName;


    /**
     * 字段：companyName ：公司名称
     */
    private String companyName;



    /**
     * 字段：createTime ：创建时间
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentShortName() {
        return departmentShortName;
    }

    public void setDepartmentShortName(String departmentShortName) {
        this.departmentShortName = departmentShortName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
