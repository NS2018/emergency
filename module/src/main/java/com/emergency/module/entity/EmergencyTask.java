package com.emergency.module.entity;

import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import com.emergency.module.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
@TableName(value = "box_emergency_task")
public class EmergencyTask {

    @JdbcId(strategy = IdGenerationType.APP_MANUAL)
    private String id;

    private String taskName;

    private Long processTime;

    private String taskDescription;

    private String emergencyPersonnel;

    private Byte taskState;

    private Long receivedTime;

    private String memo;

    private String taskResource;

    private String processPlace;

    private String safetyAdvice;

    private Byte eventLevel;

    private Byte solveDegree;

    private String importId;

    private String taskRange;

    private String taskClass;

    private String taskRequest;

    private String limitTime;

    private String taskImportState;

    private String taskAuthor;

    private String createTime;

    private String actionPlan;

    private String taskImportTime;

    private String intrusionSource;

    private String currentTask;

    private Byte attackSource;

    private String securityEventReason;

    private String taskFlag;

    private String taskPlan;

    private String checkAnalyseRecode;

    private String finishTime;

    private String handlePrePlanTitle;

    private String handlePreplanId;

    private String authorUnit;

    private String planType;

    private String ProcessSummary;

    private String produceTime;

    private Byte eventType;

    private String affectedSoftHardware;

    private String processSummary;

    private String checkAnalyse;
    @Tolerate
    public EmergencyTask(String id) {
        this.id = id;
    }

    @Tolerate
    public EmergencyTask() {
    }
}