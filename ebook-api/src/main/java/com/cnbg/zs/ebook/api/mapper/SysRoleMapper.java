package com.cnbg.zs.ebook.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.cnbg.zs.ebook.api.dto.SysRoleDTO;
import com.cnbg.zs.ebook.api.dto.SysRoleMultiDTO;
import com.cnbg.zs.ebook.api.entity.SysRole;

import java.util.List;
import java.util.Map;
/**
* @author OFG
* @version 1.0
* @date 2021/1/6 20:09
* @Description
*/
public interface SysRoleMapper extends BaseMapper<SysRole> {



    IPage<SysRoleMultiDTO> selectEntityList(Page<SysRoleMultiDTO> page,@Param(Constants.COLUMN_MAP) Map<String, Object> params);
}
