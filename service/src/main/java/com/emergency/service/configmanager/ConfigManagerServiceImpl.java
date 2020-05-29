package com.emergency.service.configmanager;

import com.emergency.dao.mybatis.mapper.common.SysConfigMapper;
import com.emergency.module.entity.SysConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ConfigManagerServiceImpl implements ConfigManagerService {

    @Resource
    private SysConfigMapper sysConfigMapper;

    @Override
    public SysConfig get() {
        List<SysConfig> sysConfigs = sysConfigMapper.get(new SysConfig());
        return sysConfigs.get(0);
    }

    @Override
    public int update(SysConfig sysConfig) {
        int update = sysConfigMapper.update(sysConfig);
        return update;
    }
}
