package com.emergency.module.entity;

import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import com.emergency.module.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
@TableName(value = "box_attachment")
public class BoxAttachment {

    @JdbcId(strategy = IdGenerationType.APP_MANUAL)
    private String id;

    private String attachmentName;

    private String attachmentPath;

    private String attachmentType;

    private String attachmentBelongs;

    private String belongsId;

    private String reserveData1;

    private String reserveData2;

    @Tolerate
    public BoxAttachment() {
    }
    @Tolerate
    public BoxAttachment(String id) {
        this.id = id;
    }
}