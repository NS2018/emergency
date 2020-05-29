package com.emergency.module.entity;

import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import com.emergency.module.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
@TableName(value = "box_preserve_file")
public class PreserveFile {

    @JdbcId(strategy = IdGenerationType.APP_MANUAL)
    private String id;

    private String storeName;

    private String originalName;

    private String fileDes;

    private String jobId;

    private String jobXmlPath;
    @Tolerate
    public PreserveFile(String id) {
        this.id = id;
    }

    @Tolerate
    public PreserveFile() {
    }
}