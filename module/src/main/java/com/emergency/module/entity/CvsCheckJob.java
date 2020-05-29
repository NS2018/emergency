package com.emergency.module.entity;

import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import com.emergency.module.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
@TableName(value = "cvs_check_job")
public class CvsCheckJob {

    @JdbcId(strategy = IdGenerationType.DB_AUTO)
    private Long id;

    private Long pId;

    private Long sdId;

    private String userId;

    private Long stId;

    private String name;

    private String crtName;

    private String mail;

    private Integer isStart;

    private Integer isSend;

    private Long scTime;

    private Long scEndTime;

    private Long scNextTime;

    private Integer scResult;

    private Integer cycleStyle;

    private Long cycleDay;

    private String cycleTime;

    private Long crtTime;

    private String clusterId;

    private Integer outAsset;
    @Tolerate
    public CvsCheckJob(Long id) {
        this.id = id;
    }

    @Tolerate
    public CvsCheckJob() {
    }
}