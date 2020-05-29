package com.emergency.module.entity;

import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import com.emergency.module.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
@TableName(value = "cvs_system_dict")
public class CvsSystemDict {

    @JdbcId(strategy = IdGenerationType.APP_MANUAL)
    private Long id;

    private String dictCode;

    private String name;

    private String dValue;
    @Tolerate
    public CvsSystemDict(Long id) {
        this.id = id;
    }

    @Tolerate
    public CvsSystemDict() {
    }
}