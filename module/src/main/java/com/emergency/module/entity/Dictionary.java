package com.emergency.module.entity;

import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import com.emergency.module.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
@TableName(value = "dictionary")
public class Dictionary {

    @JdbcId(strategy = IdGenerationType.APP_MANUAL)
    private String id;

    private String dictionaryName;

    private String dictionaryType;

    private String innerType;

    private String reserveData1;

    private String reserveData2;

    @Tolerate
    public Dictionary(String id) {
        this.id = id;
    }

    @Tolerate
    public Dictionary() {
    }
}