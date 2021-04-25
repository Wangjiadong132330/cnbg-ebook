package com.cnbg.zs.ebook.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cnbg.zs.ebook.api.dto.NodeTreeDTO;
import com.cnbg.zs.ebook.api.entity.Process;

import java.util.List;

/**
* @author OFG
* @version 1.0
* @date 2021/1/6 20:09
* @Description
*/
public interface ProcessMapper extends BaseMapper<Process> {
    List<NodeTreeDTO> getNodeList();

    int update4DeleteChart(Process process);
}
