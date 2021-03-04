package org.edwith.webbe.securityexam.service;

import java.util.ArrayList;
import java.util.List;

import org.edwith.webbe.securityexam.service.security.CustomUserDetails;
import org.edwith.webbe.securityexam.service.security.UserDbService;
import org.edwith.webbe.securityexam.service.security.UserEntity;
import org.edwith.webbe.securityexam.service.security.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
 * UserDetailsService 인터페이스를 구현한 서비스 Bean을 스프링 시큐리티가 사용하게 됨
 * 
 * */

//UserDetailsService 인터페이스를 구현한 클래스
@Service
public class CustomUserDetailsService implements UserDetailsService  {
	
	@Autowired
	UserDbService userdbService; //DB로부터 유저 정보를 읽어들이는 Bean을 등록
	
	//로그인한 사용자의 정보를 DB에서 읽어들여 UserDetails를 구현한 객체를 반환
	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		System.out.println("DB로부터 사용자 읽어들이기 시작");
		UserEntity customUser = userdbService.getUser(loginId); //로그인한 아이디를 통해 이에 해당하는 유저 정보를 DB로부터 읽어들임
		if(customUser == null) {
			throw new UsernameNotFoundException("입력한 아이디에 해당하는 사용자를 찾을 수 없습니다.");
		}
		
		/*
		 *customUser 객체에 저장된 유저 정보를 customUserDetails 객체에 저장
		 *customUserDetails 객체의 타입인 CustomUserDetails는 UserDetails 인터페이스의 구현체임
		 *  -> 즉 customUserDetails에 저장된 정보를 UserDetailsService를 구현한 CustomUSerDetailsService 객체가 반환하는 것!  
		 * */
		CustomUserDetails customUserDetails = new CustomUserDetails();
		customUserDetails.setUsername(customUser.getLoginUserId());
		customUserDetails.setPassword(customUser.getPassword());
		
		/*
		 * 로그인한 사용자의 권한 정보를 GrantedAuthority를 구현한 SimpleGrantedAuthority 객체에 담아서,
		 * 리스트에 추가해줌
		 * 
		 * 여기서 MemberRole의 이름은 "ROLE_"로 시작되어야 함
		 * 
		 * */
		List<UserRoleEntity> customRoles = userdbService.getUserRoles(loginId);
		List<GrantedAuthority> authorities = new ArrayList<>();
		if(customRoles != null) {
			for(UserRoleEntity customRole : customRoles) {
				authorities.add(new SimpleGrantedAuthority(customRole.getRoleName()));
			}
		}
		
		//이후, CustomUserDetails 객체에 권한 목록(authorities)를 설정
        customUserDetails.setAuthorities(authorities);
        customUserDetails.setEnabled(true);
        customUserDetails.setAccountNonExpired(true);
        customUserDetails.setAccountNonLocked(true);
        customUserDetails.setCredentialsNonExpired(true);
		
		return customUserDetails;
	}

}
