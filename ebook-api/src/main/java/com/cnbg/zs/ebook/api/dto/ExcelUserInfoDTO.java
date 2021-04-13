package com.cnbg.zs.ebook.api.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * @author Faye.Wang
 * @version 1.0
 * @date 2021/4/7 13:34
 * @Description
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExcelUserInfoDTO {

    @ExcelProperty(value = "会员号", index = 0)
    private String userAccount;
    @ExcelProperty(value = "姓名", index = 1)
    private String userRealName;
    @ExcelProperty(value = "性别", index = 2)
    private String gender;
    @ExcelProperty(value = "手机号", index = 5)
    private String phone;
    @ExcelProperty(value = "所属公司", index = 3)
    private String companyName;
    @ExcelProperty(value = "所属部门", index = 4)
    private String departmentName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
