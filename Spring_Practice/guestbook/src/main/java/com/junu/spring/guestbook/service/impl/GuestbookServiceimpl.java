package com.junu.spring.guestbook.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junu.spring.guestbook.dao.GuestbookDao;
import com.junu.spring.guestbook.dao.LogDao;
import com.junu.spring.guestbook.dto.Guestbook;
import com.junu.spring.guestbook.dto.Log;
import com.junu.spring.guestbook.service.GuestbookService;

@Service //해당 클래스가 서비스 레이어에 속한다는 것을 명시하는 어노테이션
//Dao 클래스에 @Repository 어노테이션 명시하는 것과 같은 맥락!
public class GuestbookServiceimpl implements GuestbookService {
	@Autowired //해당 어노테이션을 통해 자동으로 인스턴스에 객체를 할당
	GuestbookDao guestbookDao;
	
	@Autowired
	LogDao logDao;
	
	//인터페이스 추상메소드 구현
	@Override
	@Transactional //@Transactional 처리해야 내부적으로 메소드 실행시 'readonly'의 상태로 실행함
	public List<Guestbook> getGuestboks(Integer start) {
		//게스트북 목록 받아오기
		List<Guestbook> guestbooks = guestbookDao.selectAll(start, GuestbookService.LIMIT);
		return guestbooks;
	}

	@Override
	@Transactional(readOnly = false) //readOnly 설정을 해제해야 함
	public int deleteGuestbook(Long id, String ip) {
		int deleteCount = guestbookDao.deleteById(id);
		Log log = new Log();
		log.setIp(ip);
		log.setMethod("delete");
		log.setRegdate(new Date());
		logDao.insert(log);
		
		return deleteCount;
	}

	@Override
	@Transactional(readOnly = false) 
	public Guestbook addGuestbook(Guestbook guestbook, String ip) {
		guestbook.setRegdate(new Date()); //사용자가 날짜를 입력하지 않았으므로 여기서 넣어주면 됨
		Long id = guestbookDao.insert(guestbook);
		guestbook.setId(id); //로그에 남겨주기 위해 처리해주는 것
		/*
		 * Transaction 처리를 했기 때문에 만일 중간에 예외가 발생하게 되면 해당 메소드 전체의 호출을 취소하게 됨
		 * 예를 들어, guestbook을 추가한 후 log를 추가하는 과정에서 예외가 발생한다면,
		 * 메소드 호출을 취소하면서, guestbook의 추가도 결과적으로 일어나지 않게 되는 것
		 * (중간에 의도적으로 예외를 발생시킨 후 mySQL 확인해보면 추가 안된 것을 확인할 수 있을 것임)
		 * 
		 * 즉, Transactional이 아니라면.. 중간에 예외가 발생할 경우
		 * 예외가 발생하기 이전의 과정들은 정상적으로 수행되기 때문에
		 * log 추가중에 예외가 발생해도, 그 전에 실행했던 guestbook의 추가는 정상적으로 이루어지는 것
		 * 
		 * Transaction이라는 것이 미약하게나마 어떤 것인지 이해하도록 하자!
		 * */
		Log log = new Log();
		log.setIp(ip);
		log.setMethod("insert");
		log.setRegdate(guestbook.getRegdate());
		logDao.insert(log);
		
		return guestbook;
	}

	@Override
	public int getCount() {

		return guestbookDao.selectCount();
	}
	
}
