package com.emergency.module.annotation;

/**
 * 主键id生成方式
 */
public enum IdGenerationType {
	/**
	 * 应用层自增
	 */
	APP_AUTO,
	/**
	 * 数据库自增
	 */
	DB_AUTO,
	
	/**
	 * 手动设置 不做自动设置
	 */
	APP_MANUAL,
}