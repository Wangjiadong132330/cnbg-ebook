package com.cnbg.zs.ebook.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.dto.UserInfoDTO;
import com.cnbg.zs.ebook.api.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author Faye.Wang
 * @version 1.0
 * @date 2021/3/24 22:50
 * @Description
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    IPage<UserInfoDTO> selectEntityList(Page<UserInfoDTO> page, @Param(Constants.COLUMN_MAP) Map<String, Object> params);
}
