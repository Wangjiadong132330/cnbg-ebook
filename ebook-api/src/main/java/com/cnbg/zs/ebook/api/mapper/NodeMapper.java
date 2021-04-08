package com.cnbg.zs.ebook.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cnbg.zs.ebook.api.dto.NodeDTO;
import com.cnbg.zs.ebook.api.entity.Node;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
* @author OFG
* @version 1.0
* @date 2021/1/6 20:09
* @Description
*/
@Repository
@Mapper
public interface NodeMapper extends BaseMapper<Node> {

    IPage<NodeDTO> selectEntityList(Page<NodeDTO> page, @Param(Constants.COLUMN_MAP) Map<String, Object> params);
}
