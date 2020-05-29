package com.emergency.module.entity;

import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import com.emergency.module.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.math.BigDecimal;

@Data
@Builder
@TableName(value = "cvs_scan_result")
public class CvsScanResult {

    @JdbcId(strategy = IdGenerationType.APP_MANUAL)
    private String id;

    private String osName;

    private String version;

    private String ip;

    private String type;

    private BigDecimal startTime;

    private BigDecimal endTime;

    private String pid;

    private String cjId;

    private Long stId;

    private Integer itemNum;

    private Integer complianceNum;

    private Integer unComplianceNum;

    private Integer failNum;

    private Integer manualNum;

    private Double score;

    private Long crtTime;

    private Byte progress;

    private String operator;
    @Tolerate
    public CvsScanResult(String id) {
        this.id = id;
    }

    @Tolerate
    public CvsScanResult() {
    }
}