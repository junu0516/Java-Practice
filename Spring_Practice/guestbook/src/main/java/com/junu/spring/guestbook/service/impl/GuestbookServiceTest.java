package com.junu.spring.guestbook.service.impl;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.junu.spring.guestbook.config.ApplicationConfig;
import com.junu.spring.guestbook.dto.Guestbook;
import com.junu.spring.guestbook.service.GuestbookService;

public class GuestbookServiceTest {

	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		GuestbookService guestbookService = ac.getBean(GuestbookService.class);
		
		Guestbook guestbook = new Guestbook();
		guestbook.setName("junu");
		guestbook.setContent("Hello World");
		guestbook.setRegdate(new Date());
		Guestbook result = guestbookService.addGuestbook(guestbook, "127.0.0.1");
		System.out.println(result);
		
		guestbook.setName("junu2");
		guestbook.setContent("Show me your code please~!");
		guestbook.setRegdate(new Date());
		result = guestbookService.addGuestbook(guestbook, "127.0.0.1");
		System.out.println(result);

	}

}
