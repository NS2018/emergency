package com.emergency.web.controller.configmanager;

import com.emergency.module.entity.SysConfig;
import com.emergency.service.configmanager.ConfigManagerService;
import com.emergency.web.ApiResponse;
import com.emergency.web.ApiResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 配置管理
 */
@RestController
@RequestMapping("/config")
public class ConfigManagerController {

    private static final Logger LOG = LoggerFactory.getLogger(ConfigManagerController.class);

    @Resource
    private ConfigManagerService configManagerService;

    /**
     * 获取配置
     * @return
     */
    @GetMapping
    public ApiResponseData view(){
        SysConfig sysConfig = configManagerService.get();
        if(sysConfig == null){
            return ApiResponse.fail("找不到记录");
        }
        return ApiResponse.ok(sysConfig);
    }


    /**
     * 更新配置
     * @param sysConfig
     * @return
     */
    @PostMapping("/edit")
    public ApiResponseData edit(@RequestBody SysConfig sysConfig){
        int result = configManagerService.update(sysConfig);
        if(result == 0){
            return ApiResponse.ok("没有做任何更新操作");
        }
        return ApiResponse.ok("更新成功");
    }



}
