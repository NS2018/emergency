package com.emergency.service.auditlog;


import com.emergency.module.dto.AuditLogXml;
import com.emergency.module.entity.AuditLog;

import java.util.List;

/**
 * 审计日志
 */
public interface AuditLogService {

    /**
     * 日志搜索
     * @param fields 字段
     * @param values 值
     * @param offset 起始位置
     * @param limit  页大小
     * @return
     */
    List<AuditLog> query(String[] fields, String[] values, String offset, String limit);

    /**
     * 日志导入
     * @param auditLogXml xml对应对象
     */
    void save(AuditLogXml auditLogXml);
}
