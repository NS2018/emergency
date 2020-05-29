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
@TableName(value = "box_handle_preplan")
public class HandlePrePlan {

    @JdbcId(strategy = IdGenerationType.APP_MANUAL)
    private String id;

    private String handlePrePlanTitle;

    private String handlePrePlanSummary;

    private String makePerson;

    private String checkAnalyseDesc;

    private String controlDesc;

    private String uprootDesc;

    private Long makeTime;

    private String followDesc;

    private String reserveData1;

    private String reserveData2;

    private String handlePrePlanType;

    private String handlePrePlanDefine;

    private String handlePrePlanSignDesc;

    private String handlePrePlanCheckDesc;

    private String summarySuggestionDesc;

    private String isUse;

    private String checkAnalyse;

    private String handlePrePlanPreparations;

    private String handlePrePlanCase;

    private String summarySuggestion;

    private String recoverDesc;

    private String summary;

    private String reserveData3;

    private String defineExample;

    private String emerResponse;

    private String videoPath;
    @Tolerate
    public HandlePrePlan(String id) {
        this.id = id;
    }

    @Tolerate
    public HandlePrePlan() {
    }
}