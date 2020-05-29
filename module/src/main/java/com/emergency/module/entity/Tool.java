package com.emergency.module.entity;

import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import com.emergency.module.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

@Data
@Builder
@TableName(value = "box_tool")
public class Tool {

    @JdbcId(strategy = IdGenerationType.APP_MANUAL)
    private String id;

    private String toolName;

    private String toolPath;

    private String videoPath;

    private String toolImgPath;

    private String memo;

    private String toolTags;

    private String toolCmd;

    private String toolType;

    private String toolStyle;

    private String toolSummary;

    private String toolUserGuide;

    private String toolCreateTime;

    private String toolCreator;

    private String reserveData1;

    private String reserveData2;

    @Tolerate
    public Tool(String id) {
        this.id = id;
    }

    @Tolerate
    public Tool() {
    }
}