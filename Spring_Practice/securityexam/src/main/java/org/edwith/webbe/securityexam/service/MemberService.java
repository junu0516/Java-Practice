package org.edwith.webbe.securityexam.service;

import org.edwith.webbe.securityexam.service.security.UserDbService;

public interface MemberService extends UserDbService {
	/*
	 * 회원 관련 정보를 처리하는 MemberService는 UserDbService를 상속받고 이 ㅆ음
	 * 
	 * UserDbService가 스프링 시큐리티에서 필요로 하는 정보를 가졌기 때문에,
	 * 
	 * 결과적으로 스프링 시큐리티 정보를 포함한 회원 관련 전반적인 기능을 담당하는 MemberService가 이를 상속받는 것
	 * 
	 * */

}
