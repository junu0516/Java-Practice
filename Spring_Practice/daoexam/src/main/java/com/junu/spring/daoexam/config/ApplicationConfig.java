package com.junu.spring.daoexam.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration //Configuration 클래스 명시
@ComponentScan(basePackages= {"com.junu.spring.daoexam.dao"}) //약속된 어노테이션이 찾아낸 객체를 찾아서 일을 진행(베이스 패키지를 지정)
@Import({DBConfig.class}) //필요한 설정만 따로 넣어줄 수 있음 ... 하나의 클래스가 모든 정보를 가지고 있으면 추후 유지/보수에 거추장스럽기 때문
public class ApplicationConfig {

}
