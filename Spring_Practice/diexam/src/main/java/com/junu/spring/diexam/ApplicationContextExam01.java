package com.junu.spring.diexam;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextExam01 {
	//프로그램을 시작시킬 시작점
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ApplicationContext를 구현하는 다양한 컨테이너가 존재(= 공장을 만드는 다양한 방법이 존재한다.)
		//ClassPathXmlApplicationContext 인스턴스로 구현
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml"); //classpath로 context파일 정보 매개변수로 주는 것
		//매개변수로 받은 설정파일에 있는 Bean 정보를 모두 읽어들인 후, 설정된 객체들을 모두 만들어서 메모리에 올려두는 것
		//만일 여기서 문제가 생기면 어플리케이션 종료
		
		System.out.println("초기화 완료"); 
		
		UserBean userBean = (UserBean)ac.getBean("userBean"); //일반적인 자바 클래스와 달리 new를 통해 인스턴스를 선언하지 않고 공장으로 부터 getBean()을 통해 객체를 생성
		userBean.setName("Lee");
		System.out.println(userBean.getName());
		
		UserBean userBean2 = (UserBean)ac.getBean("userBean"); 
		if(userBean == userBean2) {
			//두 UserBean객체가 서로 같은지 확인
			System.out.println("같은 인스턴스 입니다.");
		}else{
			System.out.println("다른 인스턴스 입니다.");
		}
		
	}

}
