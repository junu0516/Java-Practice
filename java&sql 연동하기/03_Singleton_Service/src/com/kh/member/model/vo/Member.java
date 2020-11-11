package com.kh.member.model.vo;

import java.io.*;
import java.sql.*;

/*
 * VO value Object : 데이터베이스 테이블 Member의 각 컬럼값 저장용 객체 : 한 행씩 정보를 저장
 * DTO : Data Transfer Object
 * DO : Domain Object
 * Entity
 * bean
 * 
 * VO : 
 * 1. 모든 필드는 반드시 private 이어야 함
 * 2. 기본 생성자와 매개변수가 있는 생성자를 만들어야 함
 * 3. 모든 필드에 대해서 getter, setter 만들어야 함
 * 4. 직렬화를 반드시 해야 함(입출력)
 * 
 * */
public class Member implements Serializable {
	/*
	 * 직렬화, 역직렬화에서 ...
	 * 40 byte 객체를 파일 입출력 단위로 직렬화, 역직렬화하여 수신측에서 역직렬화할 때,
	 * 여러 객체를 구분하기 위한 고유 아이디가 필요
	 * 
	 */
	//1. 각각의 변수는 필드변수 컬럼에 대응
	private static  final long serialVersionUID = 1L;
	private String userId;
	private String password;
	private String userName;
	private String gender;
	private int age;
	private String email;
	private String phone;
	private String address;
	private String hobby;
	private Date enrollDate;
	
	//생성자
	public Member() {}

	public Member(String userId, String password, String username, String gender, int age, String email, String phone,
			String address, String hobby, Date enrolldate) {
		super();
		this.userId = userId;
		this.password = password;
		this.userName = username;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
		this.enrollDate = enrolldate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Date getEnrolldate() {
		return enrollDate;
	}

	public void setEnrolldate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return userId+" , "+password+" , "+userName+" , "+gender+" , "+age+" , "
				+email+" , "+email+" , "+phone+" , "+address+" , "+hobby+" , "+enrollDate;
	};
	
	
}
