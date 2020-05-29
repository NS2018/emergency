package com.emergency.service.impl;

import com.emergency.dao.mybatis.mapper.common.SystemBasicInfoMapper;
import com.emergency.module.entity.SystemBasicInfo;
import com.emergency.service.SystemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SystemServiceImpl implements SystemService {

    @Resource
    private SystemBasicInfoMapper systemBasicInfoMapper;

    @Override
    public SystemBasicInfo getSystemBasicInfo() {
        SystemBasicInfo systemBasicInfo = systemBasicInfoMapper.getBySelected();
        return systemBasicInfo;
    }
}
