package com.cnbg.zs.ebook.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.dto.CompanyDTO;

import com.cnbg.zs.ebook.api.entity.Company;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author OFG
* @version 1.0
* @date 2021/1/6 20:09
* @Description
*/
public interface CompanyMapper extends BaseMapper<Company> {
    IPage<CompanyDTO> selectEntityList(Page<CompanyDTO> page, @Param(Constants.COLUMN_MAP) Map<String, Object> params);
}
