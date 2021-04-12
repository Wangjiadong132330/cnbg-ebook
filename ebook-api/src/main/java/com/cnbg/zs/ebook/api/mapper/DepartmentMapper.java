package com.cnbg.zs.ebook.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.dto.CompanyDTO;
import com.cnbg.zs.ebook.api.dto.DepartmentDTO;
import com.cnbg.zs.ebook.api.entity.Department;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author OFG
* @version 1.0
* @date 2021/1/6 20:09
* @Description
*/
public interface DepartmentMapper extends BaseMapper<Department> {

    IPage<DepartmentDTO> selectEntityList(Page<DepartmentDTO> page, @Param(Constants.COLUMN_MAP) Map<String, Object> params);

}
