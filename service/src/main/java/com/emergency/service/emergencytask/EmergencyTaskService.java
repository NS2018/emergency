package com.emergency.service.emergencytask;

import com.emergency.module.entity.EmergencyTask;

import java.util.List;

public interface EmergencyTaskService {

    /**
     * 根据id获取应急任务信息
     *
     * @param taskId 任务id
     * @return EmergencyTask 或 null
     */
    EmergencyTask get(String taskId);


    /**
     * 根据任务名称模糊匹配任务
     *
     * @param taskName 任务名称
     * @return List<EmergencyTask>
     */
    List<EmergencyTask> getEmergencyTaskByName(String taskName);

    /**
     * 分页获取应急任务列表
     *
     * @param pageSize 每页的个数
     * @param pageNum  第几页
     * @return List<EmergencyTask>
     */
    List<EmergencyTask> listEmergencyTask(int pageSize, int pageNum);

    /**
     * 删除应急任务（业务有点复杂，可能是表设计不合理）
     *
     * @param taskId 任务id
     */
    void deleteEmergencyTask(String taskId);


}
