/*
 * Copyright (c) 2019. 启明星辰信息技术集团股份有限公司 版权所有
 * 本源代码受法律保护，侵权必究！
 *
 */

package com.emergency.web.controller.baselinecheck;

import com.emergency.framework.HttpSession;
import com.emergency.framework.UserSessionInfo;
import com.emergency.module.entity.CvsCheckJob;
import com.emergency.module.vo.CVSCheckJobVO;
import com.emergency.service.baselinecheck.CvsCheckJobService;
import com.emergency.web.ApiResponse;
import com.emergency.web.ApiResponseData;
import com.github.pagehelper.PageInfo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 基线核查接口
 *
 * @author gengyuanbo
 * 2019/01/04
 */

@RestController
@RequestMapping("/baselineCheck")
public class CvsCheckJobController {

    @Resource
    private CvsCheckJobService cvsCheckJobService;

    /**
     * 添加基线核查作业
     * @param req request对象，用于获取用户会话信息
     * @param cvsCheckJobVO
     * @return ApiResponseData
     */
    @PostMapping
    public ApiResponseData addJob(HttpServletRequest req, @RequestBody CVSCheckJobVO cvsCheckJobVO){
        String name = cvsCheckJobVO.getName();
        boolean existJobName = cvsCheckJobService.isExistJobName(name);
        if(existJobName)
            return ApiResponse.fail("该作业名称被占用！");
        String sessionId = HttpSession.getSessionId(req);
        if(StringUtils.isEmpty(sessionId))
            return ApiResponse.noLogin();

        UserSessionInfo userSession = HttpSession.getUserSession(sessionId);
        long id = cvsCheckJobService.addJob(userSession, cvsCheckJobVO);
        HashMap<String, Long> resMap = new HashMap<>(1);
        resMap.put("jobId",id); //将生成的job id返回前端
        return ApiResponse.ok(resMap);
    }

    /**
     * 根据id获取基线核查作业
     * @param jobId 任务id
     * @return ApiResponseData
     */
    @GetMapping("/getJob")
    public ApiResponseData getJob(@RequestParam Long jobId){
        CvsCheckJob job = cvsCheckJobService.getJob(jobId);
        return ApiResponse.ok(job);
    }

    /**
     * 带分页信息的基线核查作业列表
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return ApiResponseData
     */
    @GetMapping("/jobList")
    public ApiResponseData jobListWithPageInfo(@RequestParam(defaultValue = "1") int pageNum,
                                               @RequestParam(defaultValue = "5") int pageSize){
        List<CvsCheckJob> list = cvsCheckJobService.getList(pageNum, pageSize);
        PageInfo<CvsCheckJob> cvsCheckJobPageInfo = new PageInfo<>(list);

        return ApiResponse.ok(cvsCheckJobPageInfo);
    }

    /**
     * 删除基线核查job
     * @param jobId id
     * @return ApiResponseData
     */
    @DeleteMapping("/deleteJob")
    public ApiResponseData deleteJob(@RequestParam Long jobId){
        cvsCheckJobService.deleteJob(jobId);
        return ApiResponse.ok();
    }


}
