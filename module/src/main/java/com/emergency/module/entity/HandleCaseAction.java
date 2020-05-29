package com.emergency.module.entity;

import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import com.emergency.module.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
@TableName(value = "box_case_action")
public class HandleCaseAction {

    @JdbcId(strategy = IdGenerationType.APP_MANUAL)
    private String id;

    private String caseActionName;

    private String caseActionDesc;

    private String caseActionRecord;

    private String caseId;

    private Integer caseActionOrder;

    private String caseActionType;

    private String caseActionTools;

    private String reserveData1;
    @Tolerate
    public HandleCaseAction(String id) {
        this.id = id;
    }

    @Tolerate
    public HandleCaseAction() {
    }
}