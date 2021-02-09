package com.kh.myBatis.member.model.service;

import com.kh.myBatis.member.model.vo.Member;

public interface MemberService {
	Member loginMember(Member member) throws Exception;

	void insertMember(Member member) throws Exception;

	void updateMember(Member member) throws Exception;

	void deleteMember(Member loginUser) throws Exception;

	Member selectMember(Member member) throws Exception;
	
}
