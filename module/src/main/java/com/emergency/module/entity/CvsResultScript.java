package com.emergency.module.entity;

import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import com.emergency.module.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "cvs_result_script")
public class CvsResultScript {

    @JdbcId(strategy = IdGenerationType.APP_MANUAL)
    private String id;

    private String scanId;

    private Integer stId;

    private Byte ischeck;

    private Integer ciId;

    private String resultValue;

    private String parse;

}