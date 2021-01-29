package com.junu.spring.guestbook.dao;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.junu.spring.guestbook.config.ApplicationConfig;
import com.junu.spring.guestbook.dto.Guestbook;

public class GuestbookDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		GuestbookDao guestbookDao = ac.getBean(GuestbookDao.class);
		
		Guestbook guestbook = new Guestbook();
		guestbook.setRegdate(new Date());
		Long id = guestbookDao.insert(guestbook);
		System.out.println("id : "+id);
	}
}
