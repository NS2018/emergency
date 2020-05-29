package com.emergency.module.dto;

import com.emergency.module.entity.AuditLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * 对应日志xml实体
 */
@XmlRootElement(name = "Logs")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogXml {

    @XmlElement(name = "Log")
    private List<AuditLog> auditLogs;
}
