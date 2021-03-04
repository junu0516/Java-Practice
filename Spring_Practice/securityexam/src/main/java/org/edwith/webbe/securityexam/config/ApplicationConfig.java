package org.edwith.webbe.securityexam.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

//dao, service 클래스들을 컴포넌트 스캔하기 위한 설정
@Configuration
@ComponentScan(basePackages= {"org.edwith.webbe.securityexam.dao","org.edwith.webbe.securityexam.service"})
@EnableTransactionManagement //어노테이션으로 트랜잭션을 관리
public class ApplicationConfig implements TransactionManagementConfigurer{
	
	//DB 연동을 위한 설정 추가
	private String driverClassName = "com.mysql.cj.jdbc.Driver"; //jdbc 버전 6 이후 드라이브 클래스명 바뀌었음
	
    private String url = "jdbc:mysql://localhost:3306/connectdb?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
    private String username = "connectuser";
    private String password = "connect123!@#";
    
    //DataSource Bean 등록
    @Bean
    public DataSource dataSource() {
    	System.out.println("DataSource 생성");
    	
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setDriverClassName(driverClassName);
    	dataSource.setUrl(url);
    	dataSource.setUsername(username);
    	dataSource.setPassword(password);
    	System.out.println("드라이버 등록 완료");
    	
    	return dataSource;
    	
    }
    
    //트랜잭션 관리자 생성
    @Bean
    public PlatformTransactionManager transactionManager() {
    	System.out.println("트랜잭션 관리자 생성");
    	return new DataSourceTransactionManager(dataSource());
    }
    
	@Override
	public TransactionManager annotationDrivenTransactionManager() {
		System.out.println("트랜잭션 관리자 객체 리턴");
		return  transactionManager();
	}
	
}
