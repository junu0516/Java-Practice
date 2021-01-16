package com.junu.spring.daoexam.main;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.junu.spring.daoexam.config.ApplicationConfig;

public class DataSourceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class); //공장 만들기~!
		//DBConfig에 대해서는, ApplicationConfig에서 이미 설정정보를 읽었기 때문에 위에서 명시하지 않아도 됨
		DataSource ds = ac.getBean(DataSource.class); //getBean 메소드를 통해 DataSource 객체를 요청
		Connection conn = null;
		try {
			conn = ds.getConnection();
			if(conn!=null) {
				System.out.println("접속 성공");
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("접속 실패");
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
