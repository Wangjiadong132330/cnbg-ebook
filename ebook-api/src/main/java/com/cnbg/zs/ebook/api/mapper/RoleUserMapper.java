package com.cnbg.zs.ebook.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.dto.RoleUserDTO;
import com.cnbg.zs.ebook.api.entity.RoleUser;

/**
 * @author Faye.Wang
 * @version 1.0
 * @date 2021/3/30 17:55
 * @Description
 */
public interface RoleUserMapper extends BaseMapper<RoleUser> {

    IPage<RoleUserDTO> selectEntityList(Page<RoleUserDTO> page);

}
