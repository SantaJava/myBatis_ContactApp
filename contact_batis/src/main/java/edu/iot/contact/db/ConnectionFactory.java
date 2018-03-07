package edu.iot.contact.db;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ConnectionFactory {
	private static SqlSession sqlSession; 
	static { 
		try { 
			String resource = "mybatis-config.xml"; 
			Reader reader = Resources.getResourceAsReader(resource); 
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
													.build(reader); 
			sqlSession = sqlSessionFactory.openSession();
		} catch(Exception e) { 
			e.printStackTrace(); 
		}
	} 
	
	public static SqlSession getSqlSession() { 
		return sqlSession; 
	} 

	
}
