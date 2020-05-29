/*
 * Copyright (c) 2019. 启明星辰信息技术集团股份有限公司 版权所有
 * 本源代码受法律保护，侵权必究！
 *
 */

package com.emergency.web.controller;

import com.emergency.module.entity.HandleCase;
import com.emergency.service.HandleCaseService;
import com.emergency.web.ApiResponse;
import com.emergency.web.ApiResponseData;
import com.github.pagehelper.PageInfo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 应急处置案例 Api
 *
 * @author gengyuanbo
 * 2019/01/21
 */
@RestController
@RequestMapping("/handleCase")
public class HandleCaseController {

    @Resource
    private HandleCaseService handleCaseService;

    @GetMapping
    public ApiResponseData detail(String id){
        if(StringUtils.isEmpty(id))
            return ApiResponse.paramIsNull("id");

        HandleCase detail = handleCaseService.get(id);
        return ApiResponse.ok(detail);
    }

    @GetMapping("/page")
    public ApiResponseData listWithPageInfo(@RequestParam(defaultValue = "1") int pageNum,
                                            @RequestParam(defaultValue = "5") int pageSize){
        List<HandleCase> list = handleCaseService.getList(pageNum, pageSize);
        PageInfo<HandleCase> handleCasePageInfo = new PageInfo<>(list);
        return ApiResponse.ok(handleCasePageInfo);
    }

    @DeleteMapping("/delete")
    public ApiResponseData delete(String id){
        if(StringUtils.isEmpty(id))
            return ApiResponse.paramIsNull("id");
        boolean using = handleCaseService.isUsing(id);
        if(using)
            return ApiResponse.fail("该应急处置案例正在使用，无法删除！");

        handleCaseService.delete(id);
        return ApiResponse.ok();
    }


}
