package com.emergency.dao.mybatis.common;

import com.emergency.framework.util.DateUtil;
import com.emergency.framework.util.SpringContextUtil;
import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import com.emergency.module.annotation.JdbcTransient;
import com.emergency.module.annotation.TableName;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class SQLGen<T> {

	public String select(T object) throws IllegalAccessException {
		return new SQL() {
			{
				SELECT("*");
				FROM(getTableName(object));

				Field[] fields = object.getClass().getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					Object v = field.get(object);
					if (v != null) {
						String fieldName = field.getName();
						if (v instanceof String && ((String) v).contains("%")) {
							WHERE(fieldName + " like '" + v + "'");
						} else {
							WHERE(fieldName + "=#{" + fieldName + "}");
						}
					}
				}
			}
		}.toString();
	}

	public String selectById(T object) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		return new SQL() {
			{
				SELECT("*");
				FROM(getTableName(object));
				Method declaredMethod = object.getClass().getDeclaredMethod("getId");
				Number id = (Number) declaredMethod.invoke(object);
				WHERE("id" + "=" + id + "");
			}
		}.toString();
	}

	public String selectCount(Class<T> classz) throws IllegalAccessException, NoSuchFieldException {
		return new SQL() {
			{
				SELECT(" count(1)");
				FROM(getTableName(classz));
			}
		}.toString();
	}

	public String update(T object) throws IllegalAccessException {
		return new SQL() {
			{
				UPDATE(object.getClass().getSimpleName());

				Field[] fields = object.getClass().getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					Object v = field.get(object);
					if (v != null) {
						String fieldName = field.getName();
						SET(fieldName + "=#{" + fieldName + "}");
					}
				}

				WHERE("id=#{id}");
			}
		}.toString();
	}

	public String updateByMap(Map<String, Object> mapfeilds)
			throws IllegalAccessException, NoSuchFieldException, ParseException {
		return new SQL() {

			{
				Object object = mapfeilds.get(UpdateMapPara.KEY_CLASS);
				@SuppressWarnings("unchecked")
				Class<T> classz = (Class<T>) object;
				UPDATE(getTableName(classz));
				Number id = 0;
				Set<String> keySet = mapfeilds.keySet();
				for (String key : keySet) {
					if (UpdateMapPara.KEY_CLASS.equals(key))
						continue;
					if (UpdateMapPara.KEY_ID.equals(key)) {
						id = (Number) mapfeilds.get(key);
						continue;
					}
					Object value = mapfeilds.get(key);
					if (value instanceof Number) {
						SET(key + "=" + ((Number) value).toString());
					} else if (value instanceof String) {
						SET(key + "='" + ((String) value) + "'");
					} else if (value instanceof Enum) {
						SET(key + "=" + ((Enum<?>) value).ordinal());
					} else if (value instanceof Date) {
						SET(key + "='" + DateUtil.formatMysqlDate((Date) value) + "'");
					}
				}
				SET(" version=version+1 ");
				SET(" mtime=current_timestamp() ");

				if (id.longValue() == 0)
					throw new IllegalAccessException("lack data id!");
				WHERE(" id=" + id);
			}
		}.toString();
	}

	public String insert(T object) throws IllegalAccessException {
		return new SQL() {
			{
				INSERT_INTO(getTableName(object));
				Field[] fields = object.getClass().getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					String fieldName = field.getName();
					if ("serialVersionUID".equals(fieldName))
						continue;
					if (field.getAnnotation(JdbcTransient.class) != null)
						continue;

					JdbcId anno_id = field.getAnnotation(JdbcId.class);

					if (anno_id != null && anno_id.strategy().equals(IdGenerationType.APP_AUTO)) {
						@SuppressWarnings("unchecked")
						IdentifierGenerator<Long> identifierGenerator = (IdentifierGenerator<Long>) SpringContextUtil
								.getBean(IdentifierGenerator.class);
						VALUES(fieldName, Long.toString(identifierGenerator.generate(getTableName(object))));
						continue;
					} else if (anno_id != null && anno_id.strategy().equals(IdGenerationType.DB_AUTO)) {
						continue;
					}

					Object v = field.get(object);
					if (v != null) {
						VALUES(fieldName, "#{" + fieldName + "}");
					}
				}
//				VALUES("ctime", "#{ctime}");
			}
		}.toString();
	}

	public String del(T object) throws IllegalAccessException {
		return new SQL() {
			{
				DELETE_FROM(object.getClass().getSimpleName());

				Field[] fields = object.getClass().getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					Object v = field.get(object);
					if (v != null) {
						String fieldName = field.getName();
						if (v instanceof String && ((String) v).contains("%")) {
							WHERE(fieldName + " like '" + v + "'");
						} else {
							WHERE(fieldName + "=#{" + fieldName + "}");
						}
					}
				}

			}
		}.toString();
	}

	/**
	 * 获取表名
	 * 
	 * @param object
	 * @return
	 */
	public String getTableName(T object) {
		Class<? extends Object> tClass = object.getClass();
		TableName anno_tableName = tClass.getAnnotation(TableName.class);
		if (anno_tableName == null || StringUtils.isEmpty(anno_tableName.value())) {
			return object.getClass().getSimpleName().toLowerCase();
		}
		return anno_tableName.value();
	}

	public static String getTableName(Class<? extends Object> tClass) {
		TableName anno_tableName = tClass.getAnnotation(TableName.class);
		if (anno_tableName == null || StringUtils.isEmpty(anno_tableName.value())) {
			return tClass.getSimpleName().toLowerCase();
		}
		return anno_tableName.value();
	}

}
