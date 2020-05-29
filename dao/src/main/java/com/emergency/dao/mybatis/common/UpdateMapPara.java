package com.emergency.dao.mybatis.common;

import java.util.HashMap;

public class UpdateMapPara<K, E> extends HashMap<String, Object> {

	/**
	 * 
	 */
	public final static String KEY_ID = "DATA_ID";
	public final static String KEY_CLASS = "DATA_CLASS";
	private static final long serialVersionUID = 1L;

	private UpdateMapPara() {
	}

	public static <T> UpdateMapPara<String, Object> create(Number id, Class<T> classz) {
		UpdateMapPara<String, Object> updateMapPara = new UpdateMapPara();
		updateMapPara.put(KEY_ID, id);
		updateMapPara.put(KEY_CLASS, classz);
		return updateMapPara;
	}
}
