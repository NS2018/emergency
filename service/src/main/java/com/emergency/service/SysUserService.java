package com.emergency.service;

import com.emergency.module.entity.SysUser;

import java.util.List;

public interface SysUserService {

    SysUser getUserById(String id);

    List<SysUser> getAllUser();
}
