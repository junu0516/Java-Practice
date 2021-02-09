package com.kh.el.model.vo;

public class Member {
	private String id;
	private String password;
	private String name;
	private int age;
	private String phone;
	private String email;
	
	public Member() {
		
	}

	
	public Member(String id, String password, String name, int age, String phone, String email) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.age = age;
		this.phone = phone;
		this.email = email;
	}

	public Member(String name, int age, String phone, String email) {
		super();
		this.name = name;
		this.age = age;
		this.phone = phone;
		this.email = email;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[name : "+name+", age : "+age+", phone : "+phone+", email : "+email+"]";
	}
	
	
	
}
