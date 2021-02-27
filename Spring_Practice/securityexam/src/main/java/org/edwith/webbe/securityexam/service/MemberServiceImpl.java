package org.edwith.webbe.securityexam.service;

import java.util.ArrayList;
import java.util.List;

import org.edwith.webbe.securityexam.service.security.UserEntity;
import org.edwith.webbe.securityexam.service.security.UserRoleEntity;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService { //MemberService의 구현체
	
	@Override
	public UserEntity getUser(String loginUserId) {
	
		return new UserEntity("caramel","$2a$10$G/ADAGLU3vKBd62E6GbrgetQpEKu2ukKgiDR5TWHYwrem0cSv6Z8m");
	}

	@Override
	public List<UserRoleEntity> getUserRoles(String loginUserId) {
		
		List<UserRoleEntity> list = new ArrayList<>();
		list.add(new UserRoleEntity("caramel","ROLE_USER"));
		return list;
	}

}
