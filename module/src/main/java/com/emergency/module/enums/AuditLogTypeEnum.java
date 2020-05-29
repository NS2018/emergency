package com.emergency.module.enums;

public enum AuditLogTypeEnum {

    ADM_IN_OUT("管理员登录/退出"),
    ADM_ADD("添加管理员"),
    ADM_MODIFY("修改管理员"),
    ADM_DEL("删除管理员"),
    SYS_CONF("系统配置"),
    SYS_BACKUP("系统备份"),
    SYS_RECOVERY("系统恢复"),
    SYS_UPGRADE("系统升级"),
    LOG_OPERATE("管理员操作日志"),
    SEC_CHECK_EVENT("安全检查事件"),
    DATA_ERASE_EVENT("数据擦除事件"),
    TRUST_MEASURE("可信度量"),
    ;

    private String operation;

    AuditLogTypeEnum(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}
