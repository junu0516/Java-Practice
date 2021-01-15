package com.junu.spring.diexam;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextExam02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Car car = (Car)ac.getBean("c"); //매개변수로 등록했던 bean 태그 아이디
		//xml 파일에서 이미 properties로 Engine 클래스를 setter에 넣었기 때문에, 빈으로 굳이 불러오지 않아도 됨
		car.run();
	}

}
