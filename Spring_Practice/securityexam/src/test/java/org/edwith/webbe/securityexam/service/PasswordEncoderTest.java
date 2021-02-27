package org.edwith.webbe.securityexam.service;

import org.edwith.webbe.securityexam.config.ApplicationConfig;
import org.edwith.webbe.securityexam.config.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {ApplicationConfig.class,SecurityConfig.class})
public class PasswordEncoderTest {
	
	/*
	 * 비밀번호 암호화가 제대로 이루어지는지 확인하기 위한 테스트 코드
	 * 
	 * */
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	public void passwordEncode() throws Exception{
		
		System.out.println(passwordEncoder.encode("1234")); //인코딩 테스트
	}
	
	@Test
	public void passwordTest() throws Exception{
		
    	String encodePasswd = "$2a$10$USbExG2YOZJqu5rR9eWAqO3NqwjS6c8uI0c695cnURA2gxqRnx41O";
    	String password = "1234";
    	boolean test = passwordEncoder.matches(password, encodePasswd);
    	System.out.println(test);
	}
	
}
