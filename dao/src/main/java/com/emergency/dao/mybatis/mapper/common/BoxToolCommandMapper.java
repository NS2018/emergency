package com.emergency.dao.mybatis.mapper.common;


import com.emergency.dao.mybatis.common.GeneralMapper;
import com.emergency.module.entity.BoxToolCommand;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BoxToolCommandMapper extends GeneralMapper<BoxToolCommand> {

    @Select("select * from box_tool_command where toolId = #{toolId}")
    List<BoxToolCommand> listByToolId(String toolId);
}