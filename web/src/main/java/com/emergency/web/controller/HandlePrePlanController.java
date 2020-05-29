/*
 * Copyright (c) 2019. 启明星辰信息技术集团股份有限公司 版权所有
 * 本源代码受法律保护，侵权必究！
 *
 */

package com.emergency.web.controller;

import com.emergency.module.entity.Dictionary;
import com.emergency.module.entity.HandlePrePlan;
import com.emergency.service.HandlePrePlanService;
import com.emergency.web.ApiResponse;
import com.emergency.web.ApiResponseData;
import com.github.pagehelper.PageInfo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 应急处置预案 API
 *
 * @author gengyuanbo
 * 2019/01/15
 */

@RestController
@RequestMapping("/handlePrePlan")
public class HandlePrePlanController {

    @Resource
    private HandlePrePlanService handlePrePlanService;

    /**
     * 查询应急处置预案详情
     * @param id
     * @return
     */
    @GetMapping
    public ApiResponseData detail(@RequestParam String id){
        if(StringUtils.isEmpty(id))
            return ApiResponse.paramIsNull("id");

        HandlePrePlan detail = handlePrePlanService.get(id);
        if(detail == null){
            return ApiResponse.fail("该应急预案不存在！");
        }
        return ApiResponse.ok(detail);
    }

    /**
     * 分页获取应急处置预案列表(带分页信息)
     * @param pageNum 页号
     * @param pageSize 条数
     * @return ApiResponseData
     */
    @GetMapping("/page")
    public ApiResponseData getListWithPageInfo(@RequestParam(defaultValue = "1") int pageNum,
                                               @RequestParam(defaultValue = "5") int pageSize){
        List<HandlePrePlan> list = handlePrePlanService.getList(pageNum, pageSize);
        PageInfo<HandlePrePlan> handlePrePlanPageInfo = new PageInfo<>(list);
        return ApiResponse.ok(handlePrePlanPageInfo);
    }

    /**
     * 删除应急处置预案
     * @param id id
     * @return ApiResponseData
     */
    @DeleteMapping
    public ApiResponseData delete(@RequestParam String id){
        if(StringUtils.isEmpty(id))
            return ApiResponse.paramIsNull("id");
        HandlePrePlan detail = handlePrePlanService.get(id);
        if(detail != null){
            if("1".equals(detail.getIsUse())){ //如果正在使用
                return ApiResponse.fail("该应急预案正在使用，无法删除！");
            }else{
                handlePrePlanService.delete(id);
            }
        }else{
            return ApiResponse.fail("该应急预案不存在！");
        }

        return ApiResponse.ok();
    }


    /**
     * 获取所有处置预案类型
     * @return ApiResponseData
     */
    @GetMapping("/types")
    public ApiResponseData getAllPrePlanType(){
        List<Dictionary> handlePrePlanTypes = handlePrePlanService.getAllHandlePrePlanType();
        return ApiResponse.ok(handlePrePlanTypes);
    }

}
