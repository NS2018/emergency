/*
 * Copyright (c) 2019. 启明星辰信息技术集团股份有限公司 版权所有
 * 本源代码受法律保护，侵权必究！
 *
 */

package com.emergency.dao.mybatis.mapper.common;


import com.emergency.module.entity.SystemBasicInfo;
import org.apache.ibatis.annotations.Select;

public interface SystemBasicInfoMapper {


    @Select("select * from system_basic_info where isSelected = 1")
    SystemBasicInfo getBySelected();
}