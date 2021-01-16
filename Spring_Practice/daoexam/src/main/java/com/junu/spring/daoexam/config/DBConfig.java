package com.junu.spring.daoexam.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement //트랜젝션 관리와 관련된 어노테이션
public class DBConfig {
	private String driverClassName = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/connectdb?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul&useSSL=false"; 
    private String username = "connectuser";
    private String password = "connect123!@#";
    
    @Bean
    public DataSource dataSource() { //데이터 소스 객체 라이브러리 사용을 위한 설정
    	BasicDataSource dataSource = new BasicDataSource(); 
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
