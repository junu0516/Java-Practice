package org.edwith.webbe.securityexam.service.security;

import java.util.List;

/*
 * 인터페이스 구현체는 로그인한 사용자 id를 파라미터로 받아,
 * UserEntity와 List<UserRoleEntity>를 리턴하는 메소드를 가지고 있음 
 * 
 * */
public interface UserDbService {

	UserEntity getUser(String loginUserId);
	
	List<UserRoleEntity> getUserRoles(String loginUserId);
}
