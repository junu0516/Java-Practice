package com.junu.spring.diexam;

import org.springframework.stereotype.Component;

@Component //컴포넌트 스캔을 위한 컴포넌트임을 어노테이션을 통해 명시
public class Engine {
	public Engine() {
		System.out.println("Engine 기본 생성자");
	}
	public void exec() {
		System.out.println("엔진 동작");
	}
}
