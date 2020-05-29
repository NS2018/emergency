package com.emergency.module.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 标识对像所对应的表名
 */
@Target({ TYPE })
@Retention(RUNTIME)
public @interface TableName {

	/**
	 * 
	 * @return
	 */
	public String value() default "";

}