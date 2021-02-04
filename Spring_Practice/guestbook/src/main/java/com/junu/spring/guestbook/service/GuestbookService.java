package com.junu.spring.guestbook.service;

import java.util.List;

import com.junu.spring.guestbook.dto.Guestbook;

public interface GuestbookService { //비즈니스 로직 처리를 위한 서비스 인터페이스
	public static final Integer LIMIT = 5;
	public List<Guestbook> getGuestbooks(Integer start);
	public int deleteGuestbook(Long id, String ip);
	public Guestbook addGuestbook(Guestbook guestbook, String ip);
	public int getCount();
}
