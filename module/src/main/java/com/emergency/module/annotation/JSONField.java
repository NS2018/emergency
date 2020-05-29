package com.emergency.module.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 针对spring jdbc封装，标记一个字段将被使用json序列化和反序列化
 */
@Target({ FIELD })
@Retention(RUNTIME)
public @interface JSONField {

}
