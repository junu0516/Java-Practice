package com.junu.spring.diexam;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextExam03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//config 클래스 읽어들여서 초기화, 어노테이션으로 공장 생성
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		Car car = (Car)ac.getBean("c"); 
		car.run();
		Car car2 = (Car)ac.getBean(Car.class); //이름 대신 클래스 타입을 매개변수로 넣어줘도 됨(매개변수명 잘못 입력해서 나타날 수 있는 오류를 방지)
		car2.run();
	}
}
