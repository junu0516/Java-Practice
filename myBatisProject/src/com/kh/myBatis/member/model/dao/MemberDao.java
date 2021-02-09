package com.kh.myBatis.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.myBatis.member.model.vo.Member;

public class MemberDao {

	public Member loginMember(SqlSession sqlSession, Member member) throws Exception {
		Member loginUser = null;
		
		//member-mapper.xml 설정정보와 연동시키는 것
		//기존 JDBCTemplate을 직접 만들어서 연동하는 것보다 Dao 클래스에 들어가는 코드의 양이 확연하게 줄어듦(설정파일을 통해 알아서 매핑하기 때문)
		loginUser = sqlSession.selectOne("memberMapper.loginMember",member);
		
		return loginUser;
	}

	public int insertMember(SqlSession sqlSession, Member member) {
		
		return sqlSession.insert("memberMapper.insertMember",member);
	}

	public int updateMember(SqlSession sqlSession, Member member) {
	
		return sqlSession.update("memberMapper.updateMember",member);
	}

	public int deleteMember(SqlSession sqlSession, Member member) {

		return sqlSession.update("memberMapper.deleteMember",member);
	}

}
