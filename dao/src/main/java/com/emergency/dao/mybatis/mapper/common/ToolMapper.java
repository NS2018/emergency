package com.emergency.dao.mybatis.mapper.common;

import com.emergency.dao.mybatis.common.GeneralMapper;
import com.emergency.module.entity.Tool;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ToolMapper extends GeneralMapper<Tool> {


    @Select("select * from box_tool")
    List<Tool> listTools();
}