package com.emergency.service.configmanager;

import com.emergency.module.entity.SysConfig;

public interface ConfigManagerService {

    SysConfig get();

    int update(SysConfig sysConfig);
}
