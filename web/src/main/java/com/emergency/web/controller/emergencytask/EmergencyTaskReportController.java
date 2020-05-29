/*
 * Copyright (c) 2019. 启明星辰信息技术集团股份有限公司 版权所有
 * 本源代码受法律保护，侵权必究！
 *
 */

package com.emergency.web.controller.emergencytask;

import com.emergency.module.entity.EmergencyTask;
import com.emergency.service.emergencytask.EmergencyTaskReportService;
import com.emergency.service.emergencytask.EmergencyTaskService;
import com.emergency.web.ApiResponse;
import com.emergency.web.ApiResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author gengyuanbo
 * 2019/01/04
 */

@Controller
@RequestMapping("/emergencyTask")
public class EmergencyTaskReportController {

    @Resource
    private EmergencyTaskService emergencyTaskService;

    @Resource
    private EmergencyTaskReportService emergencyTaskReportService;

    /**
     * 导出应急任务处置报告PDF
     *
     * @param taskId 任务id
     *
     */
    @GetMapping("/exportPDFReport")
    public ApiResponseData exportPDFReport(String taskId, HttpServletResponse resp) {
        if (StringUtils.isEmpty(taskId)) {
            return ApiResponse.paramIsNull("taskId");
        }

        EmergencyTask emergencyTask = emergencyTaskService.get(taskId);
        if (emergencyTask == null) {
            return ApiResponse.fail("此任务不存在，任务id：" + taskId);
        } else {
            try {
                resp.setHeader("Content-disposition", "attachment;filename=" + emergencyTask.getTaskName() + ".pdf");
                resp.setContentType("application/pdf");
                emergencyTaskReportService.exportPDFReport(emergencyTask, resp.getOutputStream());
            }catch (Exception e){
                e.printStackTrace();
                resp.setHeader("Content-disposition","");
                resp.setContentType("application/json;charset=utf-8");
                return ApiResponse.fail("导出报告失败！");
            }
        }
        return null;

    }


}
