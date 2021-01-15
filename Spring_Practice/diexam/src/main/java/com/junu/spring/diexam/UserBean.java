package com.junu.spring.diexam;

public class UserBean {
	/*
	 * Bean 클래스 생성 규칙
	 * 
	 * 1) 기본 생성자를 가지고 있다.
	 * 2) 필드는 private하게 선언한다.
	 * 3) getter,setter 메소드를 가진다.
	 * 
	 * */
	
	private String name;
	private int age;
	private boolean male;
	public UserBean() {
		
	} //기본생성자
	
	public UserBean(String name, int age, boolean male) {
		super();
		this.name = name;
		this.age = age;
		this.male = male;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isMale() {
		return male;
	}

	public void setMale(boolean male) {
		this.male = male;
	}	
	
}
