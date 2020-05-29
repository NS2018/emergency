/*
 * Copyright (c) 2019. 启明星辰信息技术集团股份有限公司 版权所有
 * 本源代码受法律保护，侵权必究！
 *
 */

package com.emergency.web.controller;

import com.emergency.module.entity.SafetyKnowledge;
import com.emergency.service.safetyknowledge.SafetyKnowledgeService;
import com.emergency.web.ApiResponse;
import com.emergency.web.ApiResponseData;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 安全知识 API
 *
 * @author gengyuanbo
 * 2019/01/11
 */

@RestController
@RequestMapping("/safetyKnowledge")
public class SafetyKnowledgeController {

    @Resource
    private SafetyKnowledgeService safetyKnowledgeService;

    @GetMapping
    public ApiResponseData detail(@RequestParam String id){
        SafetyKnowledge safetyKnowledge = safetyKnowledgeService.get(id);
        if(safetyKnowledge == null){
            return ApiResponse.fail("安全知识没找到");
        }
        return ApiResponse.ok(safetyKnowledge);

    }

    @GetMapping("/page")
    public ApiResponseData getList(@RequestParam int pageNum, @RequestParam int pageSize){
        List<SafetyKnowledge> safetyKnowledges = safetyKnowledgeService.listSafetyKnowledge(pageNum, pageSize);
        PageInfo<SafetyKnowledge> safetyKnowledgePageInfo = new PageInfo<>(safetyKnowledges);
        return ApiResponse.ok(safetyKnowledgePageInfo);
    }


}
