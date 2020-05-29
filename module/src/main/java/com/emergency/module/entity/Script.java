/*
 * Copyright (c) 2019. 启明星辰信息技术集团股份有限公司 版权所有
 * 本源代码受法律保护，侵权必究！
 *
 */

package com.emergency.module.entity;

import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import lombok.Data;

/**
 * @author gengyuanbo
 * 2019/01/10
 */
@Data
public class Script {

    @JdbcId(strategy = IdGenerationType.APP_MANUAL)
    private long id;
    private String name;
    private String content;
    private int type;
    private String alias;
    private String dValue;
}