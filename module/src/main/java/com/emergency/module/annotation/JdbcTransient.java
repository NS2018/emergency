package com.emergency.module.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 针对spring jdbc封装 标记字段不参与数据库存储及读取
 */
@Target({ FIELD })
@Retention(RUNTIME)
public @interface JdbcTransient {

}