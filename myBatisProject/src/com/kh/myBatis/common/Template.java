package com.kh.myBatis.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {
	
	public static SqlSession getSqlSession() {
		SqlSession sqlSession  = null;
		
		try {
			/*
			 * - Session Factory Builder(Object)에서 InputStream을 통해 Session Factory 객체를 생성
			 * - 이후 Session 객체를 Session Factory 객체를 통해 다시 생성(3단계를 통해 세션 연결하는 것)
			 * 
			 * - openSession(false)를 할 경우, commit을 수동으로 할 수 있도록 설정하게 됨
			 * 
			 * */
			InputStream stream = Resources.getResourceAsStream("/mybatis-config.xml");
			sqlSession = new SqlSessionFactoryBuilder().build(stream).openSession(false);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sqlSession;
	}
}
