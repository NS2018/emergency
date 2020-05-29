package com.emergency.module.annotation;

import com.emergency.module.enums.AuditLogTypeEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 日志类型
     * @return
     */
     AuditLogTypeEnum value();

    /**
     * 操作内容
     * @return
     */
     String desc();

}
