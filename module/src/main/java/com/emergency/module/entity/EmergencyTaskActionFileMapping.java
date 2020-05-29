package com.emergency.module.entity;

import com.emergency.module.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName(value = "box_taskact_file_mapping")
public class EmergencyTaskActionFileMapping {

    private String taskActionId;

    private String fileId;

}