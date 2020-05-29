/*
 * Copyright (c) 2019. 启明星辰信息技术集团股份有限公司 版权所有
 * 本源代码受法律保护，侵权必究！
 *
 */

package com.emergency.dao.mybatis.mapper.common;


import com.emergency.dao.mybatis.common.GeneralMapper;
import com.emergency.module.entity.Dictionary;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DictionaryMapper extends GeneralMapper<Dictionary> {

    @Select("select * from dictionary where id in (#{ids})")
    List<Dictionary> listByIds(@Param("ids") String ids);
}