package com.junu.spring.guestbook.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement 
public class DBConfig implements TransactionManagementConfigurer {
	private final String driverClassName = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/connectdb?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul&useSSL=false"; 
    private final String username = "connectuser";
    private final String password = "connect123!@#";
	
    @Bean
    public DataSource dataSource() {
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setDriverClassName(driverClassName);
    	dataSource.setUrl(url);
    	dataSource.setUsername(username);
    	dataSource.setPassword(password);
    	return dataSource;
    }
    
    @Bean
  	public PlatformTransactionManager transactionManger() {
  		return new DataSourceTransactionManager(dataSource());
  	}
    
    public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManger();
	}
    
  
    
    
    
	

}
