package org.edwith.webbe.securityexam.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//dao, service 클래스들을 컴포넌트 스캔하기 위한 설정
@Configuration
@ComponentScan(basePackages= {"org.edwith.webbe.securityexam.dao","org.edwith.webbe.securityexam.service"})
public class ApplicationConfig{
	
}
