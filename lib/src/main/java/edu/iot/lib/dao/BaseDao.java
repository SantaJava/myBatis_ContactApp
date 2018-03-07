package edu.iot.lib.dao;

import java.util.List;


public interface BaseDao<T> {
	int getCount() throws Exception;

	List<T> selectList(int start, int end) throws Exception;

	T selectOne(String userId) throws Exception;

	int insert(T t) throws Exception;

	int update(T t) throws Exception;

	int delete(String userId) throws Exception;
 

}
