package com.kh.java.common;

import java.io.InputStream;
import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;



public class Template {

	public static SqlSession getSqlSession() {
		
		SqlSession sqlSession = null;
		
		// '/'는 모든 src폴더의 최상위 폴더라는 의미(classes)
		String config = "/mybatis-config.xml";
		
		try {
			InputStream stream = Resources.getResourceAsStream(config);
			sqlSession = new SqlSessionFactoryBuilder().build(stream).openSession();
			System.out.println(sqlSession);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sqlSession;
	}
}
