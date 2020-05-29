package com.emergency.module.entity;

import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import com.emergency.module.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
@TableName(value = "system_basic_info")
public class SystemBasicInfo {

    @JdbcId(strategy = IdGenerationType.APP_MANUAL)
    private String id;

    private String name;

    private String version;

    private String unit;

    private String phoneNumber;

    private String copyRight;

    private String copyRightEnglish;

    private String logo;

    private String reserveData1;

    private String reserveData2;

    private String isSelected;
    @Tolerate
    public SystemBasicInfo(String id) {
        this.id = id;
    }

    @Tolerate
    public SystemBasicInfo() {
    }
}