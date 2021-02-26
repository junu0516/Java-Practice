package com.kh.spring.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.Member;

@Repository("memberDao")
public class MemberDao {
	
	public Member loginUser(SqlSessionTemplate sqlSession, Member member) {
		
		return sqlSession.selectOne("memberMapper.loginMember",member);
	}

	public int insertMember(SqlSessionTemplate sqlSession, Member member) {

		return sqlSession.insert("memberMapper.insertMember",member);
	}

	public int updateMember(SqlSessionTemplate sqlSession, Member member) {
	
		return sqlSession.update("memberMapper.updateMember",member);
	}

	public int deleteMember(SqlSessionTemplate sqlSession, String userId) {
		
		return sqlSession.update("memberMapper.deleteMember", userId);
	}

	public int updatePassword(SqlSessionTemplate sqlSession, Member member) {
		return sqlSession.update("memberMapper.updatePassword",member);
	}

	public int idCheck(SqlSessionTemplate sqlSession, String userId) {

		return sqlSession.selectOne("memberMapper.idCheck",userId);
	}

}
