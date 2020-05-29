package com.emergency.module.entity;

import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import com.emergency.module.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
@TableName(value = "box_tool_command")
public class BoxToolCommand {

    @JdbcId(strategy = IdGenerationType.APP_MANUAL)
    private String id;

    private String toolId;

    private String commandInstruction;

    private String commandContent;

    private String reserveData1;

    private String reserveData2;
    @Tolerate
    public BoxToolCommand(String id) {
        this.id = id;
    }

    @Tolerate
    public BoxToolCommand() {
    }
}