package com.emergency.dao.mybatis.common;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface GeneralMapper<T> {

	@InsertProvider(method = "insert", type = SQLGen.class)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int save(T t);

	@DeleteProvider(method = "del", type = SQLGen.class)
	int del(T t);

	@UpdateProvider(method = "update", type = SQLGen.class)
	int update(T t);

	@UpdateProvider(method = "updateByMap", type = SQLGen.class)
	int updateByMap(UpdateMapPara<String, Object> a);

	@SelectProvider(method = "selectById", type = SQLGen.class)
	T getById(T object);

	@SelectProvider(method = "select", type = SQLGen.class)
	List<T> get(T object);

	@SelectProvider(method = "selectCount", type = SQLGen.class)
	long count(Class<T> classz);

	@Select(" <script> SELECT id," + "p_t pT," + " WHERE " + "1+1" + " and s = #{pa.s}"
			+ " <if test=\"#{pa.d} != null\">and uid = #{pa.d} </if> "
			+ " <if test=\"#{pa.d} != null \">and brand_id = #{pa.d} </if> "
			+ " <if test=\"#{pa.d1} != null \">and id limit #{pa.d1},#{pa.d2} </if> </script>")
	List<T> findByid1Andid3(@Param("pa") Map<String, Object> pa);

	// 条数更改和修改
	// 解释：where条件比较多时最好写在一行 否则查询失败
	@Update("update ss set " + "bt = #{bt} , tt = #{tt} , update_date = #{updateDate} WHERE id = #{id}")
	void updatexxx(@Param("id") int id, @Param("bt") int balanceCount, @Param("tt") int tt,
                   @Param("updateDate") int updateDate);

}
