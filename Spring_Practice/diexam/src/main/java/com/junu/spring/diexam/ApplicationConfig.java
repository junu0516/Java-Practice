package com.junu.spring.diexam;

import org.springframework.context.annotation.*;;

@Configuration //해당 어노테이션을 읽고 해당 클래스가 config 파일임을 인지(스프링 설정 클래스)
public class ApplicationConfig {
	/*
	 * @Bean 어노테이션이 붙은 메소드(생성자)를 실행해서,
	 * 리턴값으로 받은 객체들을 자동으로 싱글톤으로 관리해줌
	 * pom.xml에서 일일히 등록하는 것보다 더욱 편리!!
	 * 
	 * */
	
	@Bean
	public Car car(Engine e) { //클래스명과 실행파일에서의 getBean()의 매개변수를 서로 맞춰주도록 함
		Car c = new Car();
		//c.setEngine(e);
		return c;
	}
	
	@Bean
	public Engine engine() {
		return new Engine();
	}
	
}
