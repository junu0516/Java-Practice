package org.edwith.webbe.securityexam.service;

import java.util.ArrayList;
import java.util.List;

import org.edwith.webbe.securityexam.dao.MemberDao;
import org.edwith.webbe.securityexam.dao.MemberRoleDao;
import org.edwith.webbe.securityexam.dto.Member;
import org.edwith.webbe.securityexam.dto.MemberRole;
import org.edwith.webbe.securityexam.service.security.UserEntity;
import org.edwith.webbe.securityexam.service.security.UserRoleEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService { //MemberService의 구현체
	
	private final MemberDao memberDao;
	private final MemberRoleDao memberRoleDao;
	
	//Autowired 없이 생성자를 통한 주입으로 Bean을 생성
    public MemberServiceImpl(MemberDao memberDao, MemberRoleDao memberRoleDao) {
        this.memberDao = memberDao;
        this.memberRoleDao = memberRoleDao;
    }
	
	@Override
	@Transactional
	public UserEntity getUser(String loginUserId) {
		
		Member member = memberDao.getMemberByEmail(loginUserId);
		System.out.println("member : "+member);
		return new UserEntity(member.getEmail(),member.getPassword());
	}

	@Override
	@Transactional
	public List<UserRoleEntity> getUserRoles(String loginUserId) {
		
		List<MemberRole> memberRoles = memberRoleDao.getRolesByEmail(loginUserId);
		List<UserRoleEntity> list = new ArrayList<>();
		
		for(MemberRole memberRole : memberRoles) {
			list.add(new UserRoleEntity(loginUserId,memberRole.getRoleName()));
		}
		System.out.println(list);
		
		return list;
	}

	@Override
	@Transactional(readOnly = false)
	public void addMember(Member member, boolean isAdmin) {
		
		memberDao.addMember(member);
		Member selectedMember = memberDao.getMemberByEmail(member.getEmail());
		System.out.println(selectedMember);
		Long memberId = selectedMember.getId();
		if(isAdmin==true) {
			memberRoleDao.addAdminRole(memberId);
		}
		memberRoleDao.addUserRole(memberId);
		
	}

	@Override
	public Member getMemberByEmail(String email) {
		
		return memberDao.getMemberByEmail(email);
	}

	

}
