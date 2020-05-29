package com.emergency.service.auditlog;

import com.emergency.dao.mybatis.mapper.auditlog.AuditLogMapper;
import com.emergency.module.dto.AuditLogXml;
import com.emergency.module.entity.AuditLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 审计日志
 */
@Service
public class AuditLogServiceImpl implements AuditLogService{

    @Resource
    private AuditLogMapper auditLogMapper;


    @Override
    public List<AuditLog> query(String[] fields, String[] values, String offset, String limit) {
        return null;
    }

    @Override
    @Transactional
    public void save(AuditLogXml auditLogXml) {
        List<AuditLog> auditLogs = auditLogXml.getAuditLogs();
        if(CollectionUtils.isEmpty(auditLogs)){
            return;
        }
        for (AuditLog auditLog : auditLogs) {
            AuditLog log = auditLogMapper.getById(auditLog);
            if(log != null){
                continue;
            }
            auditLogMapper.save(auditLog);
        }
    }
}
