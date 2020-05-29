package com.emergency.web.controller.emergencytask;

import com.emergency.module.entity.EmergencyTask;
import com.emergency.service.emergencytask.EmergencyTaskService;
import com.emergency.web.ApiResponse;
import com.emergency.web.ApiResponseData;
import com.github.pagehelper.PageInfo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/emergencyTask")
public class EmergencyTaskController {


    @Resource
    private EmergencyTaskService emergencyTaskService;

    /**
     * 查询任务信息
     *
     * @param taskId 任务id
     * @return ApiResponseData
     */
    @GetMapping
    public ApiResponseData detail(String taskId) {
        if (StringUtils.isEmpty(taskId))
            return ApiResponse.paramIsNull("taskId");
        EmergencyTask emergencyTask = emergencyTaskService.get(taskId);
        return ApiResponse.ok(emergencyTask);
    }

    /**
     * 通过任务名模糊查询任务
     *
     * @param taskName 应急任务名
     * @return ApiResponseData
     */
    @GetMapping("/byName")
    public ApiResponseData showByName(String taskName) {
        List<EmergencyTask> emergencyTasks = emergencyTaskService.getEmergencyTaskByName(taskName);
        return ApiResponse.ok(emergencyTasks);
    }


    /**
     * 带分页信息的应急任务列表，默认获取第1页，每页5条数据
     *
     * @param pageSize 每页的个数，默认是5
     * @param pageNum  第几页，默认是1
     * @return ApiResponseData
     */
    @GetMapping("/page")
    public ApiResponseData page(
            @RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "1") int pageNum) {

        List<EmergencyTask> emergencyTask = emergencyTaskService.listEmergencyTask(pageSize, pageNum);
        PageInfo<EmergencyTask> emergencyTaskPageInfo = new PageInfo<>(emergencyTask);
        return ApiResponse.ok(emergencyTaskPageInfo);
    }


    /**
     * 删除应急任务
     *
     * @param taskId 任务id
     * @return ApiResponseData
     */
    @DeleteMapping
    public ApiResponseData remove(String taskId) {
        if (StringUtils.isEmpty(taskId))
            return ApiResponse.paramIsNull("taskId");
        emergencyTaskService.deleteEmergencyTask(taskId);
        return ApiResponse.ok();
    }


}
