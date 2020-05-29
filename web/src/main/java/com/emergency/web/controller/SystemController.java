/*
 * Copyright (c) 2019. 启明星辰信息技术集团股份有限公司 版权所有
 * 本源代码受法律保护，侵权必究！
 *
 */

package com.emergency.web.controller;

import com.emergency.module.entity.SystemBasicInfo;
import com.emergency.service.SystemService;
import com.emergency.web.ApiResponse;
import com.emergency.web.ApiResponseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 应急工具箱系统相关 API
 *
 * @author gengyuanbo
 * 2019/01/25
 */
@RestController
@RequestMapping("/system")
public class SystemController {

    @Resource
    private SystemService systemService;


    /**
     * 获取系统基本信息
     * @return ApiResponseData
     */
    @GetMapping("/systemInfo")
    public ApiResponseData detail(){
        SystemBasicInfo systemBasicInfo = systemService.getSystemBasicInfo();
        return ApiResponse.ok(systemBasicInfo);
    }


}
