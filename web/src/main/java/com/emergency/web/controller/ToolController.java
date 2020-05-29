/*
 * Copyright (c) 2019. 启明星辰信息技术集团股份有限公司 版权所有
 * 本源代码受法律保护，侵权必究！
 *
 */

package com.emergency.web.controller;

import com.emergency.framework.util.FileUtils;
import com.emergency.module.dto.ToolDTO;
import com.emergency.module.entity.BoxToolCommand;
import com.emergency.service.ToolService;
import com.emergency.web.ApiResponse;
import com.emergency.web.ApiResponseData;
import com.github.pagehelper.PageInfo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 应急工具箱工具 API
 *
 * @author gengyuanbo
 * 2019/01/23
 */

@RestController
@RequestMapping("/tool")
public class ToolController {

    @Resource
    private ToolService toolService;

    @GetMapping
    public ApiResponseData detail(@RequestParam String id){
        if(StringUtils.isEmpty(id))
            return ApiResponse.paramIsNull("id");
        ToolDTO toolDTO = toolService.get(id);
        return ApiResponse.ok(toolDTO);
    }

    @GetMapping("/page")
    public ApiResponseData listWithPageInfo(@RequestParam(defaultValue = "1") int pageNum,
                                            @RequestParam(defaultValue = "5") int pageSize){
        List<ToolDTO> list = toolService.listTool(pageNum, pageSize);
        PageInfo<ToolDTO> toolDTOPageInfo = new PageInfo<>(list);
        return ApiResponse.ok(toolDTOPageInfo);
    }

    /**
     * 远程工具下载
     * @param toolCommandId 工具命令Id
     * @param resp Response对象
     * @return ApiResponseData
     * @throws IOException 当文件丢失时抛出FileNotFoundException
     */
    @GetMapping("/remoteToolDownload")
    public ApiResponseData remoteToolDownload(String toolCommandId, HttpServletResponse resp) throws IOException {
        if(StringUtils.isEmpty(toolCommandId))
            return ApiResponse.paramIsNull("toolCommandId");
        BoxToolCommand boxToolCommand = toolService.getBoxToolCommand(toolCommandId);
        if(boxToolCommand == null){
            return ApiResponse.fail("该命令不存在！");
        }
        File file = new File(boxToolCommand.getCommandContent());
        System.out.println(file.getName());
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        resp.setContentType("application/octet-stream");
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(),"utf-8"));
        FileUtils.downFileStreamCopy(bis,resp.getOutputStream());
        return null;
    }








}
