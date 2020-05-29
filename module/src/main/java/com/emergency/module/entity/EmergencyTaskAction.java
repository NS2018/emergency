package com.emergency.module.entity;

import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import com.emergency.module.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
@TableName(value = "box_emergency_task_action")
public class EmergencyTaskAction {

    @JdbcId(strategy = IdGenerationType.APP_MANUAL)
    private String id;

    private String emergTaskActionName;

    private String emergTaskActionRecode;

    private String taskId;

    private Integer emergTaskActionOrder;

    private Byte emergTaskActionType;

    private String actionTools;

    private String actionJobs;

    private String emergTaskActionDesc;
    @Tolerate
    public EmergencyTaskAction(String id) {
        this.id = id;
    }

    @Tolerate
    public EmergencyTaskAction() {
    }
}