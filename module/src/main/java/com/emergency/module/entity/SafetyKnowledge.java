package com.emergency.module.entity;

import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import com.emergency.module.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
@TableName(value = "safety_knowledge")
public class SafetyKnowledge {

    private String skName;

    private String makePerson;

    private Long makeTime;

    private String reserveData2;

    private String reserveData3;

    private String reserveData1;

    private String skDesc;

    @JdbcId(strategy = IdGenerationType.APP_MANUAL)
    private String id;
    @Tolerate
    public SafetyKnowledge(String id) {
        this.id = id;
    }

    @Tolerate
    public SafetyKnowledge() {
    }
}