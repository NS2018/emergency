package com.emergency.module.entity;

import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import com.emergency.module.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * 工具箱标签实体类，标签包括应急知识的标签，处置预案标签 等等
 */
@Data
@Builder
@TableName(value = "box_label")
public class BoxLabel {

    @JdbcId(strategy = IdGenerationType.APP_MANUAL)
    private String id;

    private String labelName;

    private String labelBelongs;

    private String belongsId;

    private String reserveData1;

    private String reserveData2;

    private String reserveData3;
    @Tolerate
    public BoxLabel(String id) {
        this.id = id;
    }

    @Tolerate
    public BoxLabel() {
    }
}