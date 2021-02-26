package com.kh.spring.member.model.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kh.spring.member.model.vo.Member;

public interface MemberService {
	
	Member loginMember(Member member) throws Exception;
	
	int insertMember(Member member) throws Exception;
	
	Member loginMember(Member member, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception;
	
	int updateMember(Member member);
	
	int deleteMember(String userId);

	int updatePassword(Member member);

	int idCheck(String userId);
}
