package com.kh.spring.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public Member loginMember(Member member) throws Exception {
		
		Member loginUser = memberDao.loginUser(sqlSession,member);
	
		return loginUser;
	}

	@Override
	public int insertMember(Member member) throws Exception {
		
		int result = memberDao.insertMember(sqlSession,member);
	
		return result;
	}
	
	@Override
	public Member loginMember(Member member, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
		
		Member loginUser = memberDao.loginUser(sqlSession,member);
		
		if(loginUser == null){
			throw new Exception("No such user exists.");
		}
		
		//기존에 저장된 패스워드와, 입력받은 패스워드의 일치 여부
		if(!bCryptPasswordEncoder.matches(member.getUserPwd(), loginUser.getUserPwd())) {
				throw new Exception("Input password does not match with real password");
		}
		
		return loginUser;
	}

	@Override
	//@Transactional
	public int updateMember(Member member) {
		
		
		return memberDao.updateMember(sqlSession,member);
	}

	@Override
	public int deleteMember(String userId) {
		
		return memberDao.deleteMember(sqlSession,userId);
	}

	@Override
	public int updatePassword(Member member) {
		return memberDao.updatePassword(sqlSession,member);
	}

	@Override
	public int idCheck(String userId) {
		
		return memberDao.idCheck(sqlSession,userId);
	}

}
