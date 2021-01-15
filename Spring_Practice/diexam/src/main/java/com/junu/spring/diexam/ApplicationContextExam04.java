package com.junu.spring.diexam;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextExam04 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//config 클래스 읽어들여서 초기화, 어노테이션으로 공장 생성
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig2.class);
		
		Car car = (Car) ac.getBean(Car.class);
		car.run();
	}
}
