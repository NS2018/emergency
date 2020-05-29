package com.emergency.module.entity;

import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import com.emergency.module.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
@Builder
@TableName(value = "vwf_audit_log")
public class AuditLog {

    @JdbcId(strategy = IdGenerationType.APP_MANUAL)
    private String id;

    private String devGuid;

    private String userName;

    private String userIp;

    private Date logTime;

    private String operation;

    private Integer ending;

    private String bornGuid;

    private Integer flag;

    private Long createTime;

    private String loginType;

    private String userDn;

    private String logType;

    private String operObject;

    private String operObjectIp;

    private String operObjectType;

    private String operContent;

    @Tolerate
    public AuditLog(String id) {
        this.id = id;
    }

    @Tolerate
    public AuditLog() {
    }
}