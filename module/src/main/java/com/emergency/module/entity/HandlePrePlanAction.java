package com.emergency.module.entity;

import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import com.emergency.module.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
@TableName(value = "box_handle_preplan_action")
public class HandlePrePlanAction {

    @JdbcId(strategy = IdGenerationType.APP_MANUAL)
    private String id;

    private String handleActionName;

    private String handlePrePlanId;

    private String handleActionType;

    private Integer handleActionOrder;

    private Long editorTime;

    private String handleActionEditor;

    private String handleActionRemark;

    private String reserveData1;

    private String reserveData2;

    private String toolIds;

    private String handleActionDesc;
    @Tolerate
    public HandlePrePlanAction(String id) {
        this.id = id;
    }

    @Tolerate
    public HandlePrePlanAction() {
    }
}