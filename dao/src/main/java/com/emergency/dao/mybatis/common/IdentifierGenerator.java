package com.emergency.dao.mybatis.common;

/**
 * ID生成器
 * @param <T>
 */
public interface IdentifierGenerator<T> {

	/**
	 * 指定作用范围生成key
	 * 
	 * @param namespace
	 * @return
	 */
	T generate(String namespace);

	/**
	 * 获取当前值
	 * 
	 * @param namespace
	 * @return
	 */
	T get(String namespace);

}
