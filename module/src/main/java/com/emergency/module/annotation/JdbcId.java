package com.emergency.module.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ FIELD })
@Retention(RUNTIME)
public @interface JdbcId {

	/**
	 * 主键策略
	 * 
	 * @return
	 */
	public IdGenerationType strategy() default IdGenerationType.APP_AUTO;

	/**
	 * 当strategy为APP_AUTO时，此属性表示用于自增的序列名, 同一序列名使用相同自增序列
	 * 
	 * @return
	 */
	public String sequenceName() default "";

}