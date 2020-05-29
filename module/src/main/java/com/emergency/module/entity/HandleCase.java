package com.emergency.module.entity;

import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import com.emergency.module.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

@Data
@Builder
@TableName(value = "box_handle_case")
public class HandleCase {

    @JdbcId(strategy = IdGenerationType.APP_MANUAL)
    private String id;

    private String handleCaseName;

    private String handleCaseDesc;

    private String taskName;

    private Long processTime;

    private String taskDescription;

    private String emergencyPersonnel;

    private Long receivedTime;

    private String memo;

    private String taskResource;

    private String processPlace;

    private String eventLevel;

    private String affectedSoftHardware;

    private String intrusionSource;

    private String safetyAdvice;

    private String processSummary;

    private String solveDegree;

    private String taskRange;

    private String taskClass;

    private String taskPlan;

    private String taskRequest;

    private Long limitTime;

    private String taskAuthor;

    private Long createTime;

    private Long taskImportTime;

    private String attackSource;

    private String securityEventReason;

    private String checkAnalyseRecord;

    private String reserveData1;

    private String reserveData2;

    private String reserveData3;

    private String caseMakePerson;

    private Long caseMakeTime;

    private Long finishTime;

    private String handlePreplanTitle;

    private String checkAnalyse;
    @Tolerate
    public HandleCase(String id) {
        this.id = id;
    }

    @Tolerate
    public HandleCase() {
    }
}