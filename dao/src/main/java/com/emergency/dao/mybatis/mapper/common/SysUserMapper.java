/*
 * Copyright (c) 2019. 启明星辰信息技术集团股份有限公司 版权所有
 * 本源代码受法律保护，侵权必究！
 *
 */

package com.emergency.dao.mybatis.mapper.common;

import com.emergency.dao.mybatis.common.GeneralMapper;
import com.emergency.module.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SysUserMapper extends GeneralMapper<SysUser> {

    @Select("<script>select * from vwf_sys_user where LOGONNAME = #{loginName} and PASSWORD = #{password}</script>")
    SysUser getByLogonnameAndPassword(@Param("loginName") String loginName,@Param("password") String password);
}