package com.emergency.module.entity;

import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import com.emergency.module.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
@TableName(value = "box_emer_task_affect_softhardware")
public class EmergencyTaskAffectSoftHardware {

    @JdbcId(strategy = IdGenerationType.APP_MANUAL)
    private String id;

    private String wareName;

    private Byte wareType;

    private String identifyInfo;

    private String wareDes;

    private String taskId;

    private Date gmtCreate;

    private Date gmtModified;
    @Tolerate
    public EmergencyTaskAffectSoftHardware(String id) {
        this.id = id;
    }

    @Tolerate
    public EmergencyTaskAffectSoftHardware() {
    }
}