package com.kh.myBatis.member.model.service;

import org.apache.ibatis.session.SqlSession;
import static com.kh.myBatis.common.Template.*;

import com.kh.myBatis.member.model.dao.MemberDao;
import com.kh.myBatis.member.model.vo.Member;

public class MemberServiceImpl implements MemberService {
	private MemberDao memberDao  = new MemberDao(); 
	
	@Override
	public Member loginMember(Member member) throws Exception {
		SqlSession sqlSession = getSqlSession();
		
		Member loginUser = memberDao.loginMember(sqlSession,member);
		
		sqlSession.close();
		
		return loginUser;
	}

	@Override
	public void insertMember(Member member) throws Exception {
		SqlSession sqlSession = getSqlSession();
		
		int result = memberDao.insertMember(sqlSession,member);
		
		if(result>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
			throw new Exception("회원가입 도중 예외 발생");
		}

		sqlSession.close();
	}

	@Override
	public void updateMember(Member member) throws Exception {
		SqlSession sqlSession = getSqlSession();
		
		int result = memberDao.updateMember(sqlSession,member);
		
		if(result>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
			throw new Exception("회원정보 수정 중 문제 발생");
		}

		sqlSession.close();
		
	}

	@Override
	public void deleteMember(Member member) throws Exception {
		SqlSession sqlSession = getSqlSession();
		
		int result = memberDao.deleteMember(sqlSession,member);
		
		if(result>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
			throw new Exception("회원탈퇴 처리중 문제 발생");
		}

		sqlSession.close();
	}

	
	@Override
	public Member selectMember(Member member) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
