package com.cnbg.zs.ebook.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cnbg.zs.ebook.api.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author OFG
* @version 1.0
* @date 2021/1/6 20:09
* @Description
*/
@Repository
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

}
