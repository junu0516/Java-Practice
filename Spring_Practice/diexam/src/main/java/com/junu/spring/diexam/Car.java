package com.junu.spring.diexam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //컴포넌트 스캔을 위한 컴포넌트임을 어노테이션을 통해 명시
public class Car {
	
	@Autowired //아래 setter 없어도 Autowired 어노테이션 보고 알아서 setter 메소드 실행하라고 명시하는 것
	private Engine v8;
	
	public Car() {
		System.out.println("Car 기본 생성자");
	}
	/*
	public void setEngine(Engine e) {
		this.v8 = e;
	}
	*/
	public void run() {
		System.out.println("엔진을 이용하여 달립니다.");
		v8.exec();
	}
	
	//public static void main(String[] args) {
		//메인메소드에서 아래와 같이 new를 통해 인스턴스를 생성하지 않고 스프링 컨테이너가 하도록 지정 가능
	/*	<bean id="e" class="com.junu.spring.diexam.Engine"></bean>
		<bean id="c" class="com.junu.spring.diexam.Car">
			<property name="engine" ref="e"> </property> <!-- setter 메소드 지정 --> <!-- ref는 참고할 매개변수명 -->
		</bean>
	*/
	//	Engine e = new Engine();
	//	Car c = new Car();
	//	c.setEngine(e);
	//	c.run();
	//}
}
