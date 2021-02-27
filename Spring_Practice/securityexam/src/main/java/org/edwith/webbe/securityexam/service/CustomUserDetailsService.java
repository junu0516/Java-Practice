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

@Service
public class CustomUserDetailsService implements UserDetailsService  {
	
	@Autowired
	UserDbService userdbService;
	
	//로그인한 사용자의 정보를 DB에서 읽어들여 UserDetails를 구현한 객체를 반환
	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		
		UserEntity customUser = userdbService.getUser(loginId);
		if(customUser == null) {
			throw new UsernameNotFoundException("입력한 아이디에 해당하는 사용자를 찾을 수 없습니다.");
		}
		
		CustomUserDetails customUserDetails = new CustomUserDetails();
		customUserDetails.setUsername(customUser.getLoginUserId());
		customUserDetails.setPassword(customUser.getPassword());
		
		List<UserRoleEntity> customRoles = userdbService.getUserRoles(loginId);
		List<GrantedAuthority> authorities = new ArrayList<>();
		if(customRoles != null) {
			for(UserRoleEntity customRole : customRoles) {
				authorities.add(new SimpleGrantedAuthority(customRole.getRoleName()));
			}
		}
		
        customUserDetails.setAuthorities(authorities);
        customUserDetails.setEnabled(true);
        customUserDetails.setAccountNonExpired(true);
        customUserDetails.setAccountNonLocked(true);
        customUserDetails.setCredentialsNonExpired(true);
		
		return customUserDetails;
	}

}
