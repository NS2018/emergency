package com.emergency.module.entity;

import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import com.emergency.module.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
@TableName(value = "cvs_check_script")
public class CvsCheckScript {

    @JdbcId(strategy = IdGenerationType.APP_MANUAL)
    private Long id;

    private String alias;

    private String sysCode;

    private String context;

    private String info;

    private String name;

    private Integer isBuildIn;

    private Integer isJdbc;

    private String commandSet;

    private String uuid;
    private String soId;
    private String versionId;
    @Tolerate
    public CvsCheckScript(Long id) {
        this.id = id;
    }

    @Tolerate
    public CvsCheckScript() {
    }
}