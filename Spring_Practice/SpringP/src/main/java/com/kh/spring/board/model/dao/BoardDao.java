package com.kh.spring.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.board.model.vo.Reply;

@Repository("boardDao")
public class BoardDao {

	public int selectListCount(SqlSessionTemplate sqlSession) {
		
		return sqlSession.selectOne("boardMapper.selectListCount");
	}

	public ArrayList<Board> selectList(SqlSessionTemplate sqlSession, PageInfo pi) {
		
		//RowBounds 객체를 생성하여 (페이징 처리를 위한) 구간을 설정한 후, 해당 구간만큼 데이터를 뽑아오도록 mapper로 위임
		int offset = (pi.getCurrentPage()-1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset,pi.getBoardLimit());
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectList",null,rowBounds); //여기서는 따로 파라미터는 없음
	}

	public int insertBoard(SqlSessionTemplate sqlSession, Board board) {
		
		return sqlSession.insert("boardMapper.insertBoard",board);
	}

	public int updateCount(SqlSessionTemplate sqlSession, int bno) {
		
		return sqlSession.update("boardMapper.updateCount", bno);
	}

	public Board selectBoard(SqlSessionTemplate sqlSession, int bno) {
		
		return	sqlSession.selectOne("boardMapper.selectBoard", bno);
	}

	public int deleteBoard(SqlSessionTemplate sqlSession, int bno) {
		
		return sqlSession.update("boardMapper.deleteBoard",bno);
	}

	public int updateBoard(SqlSessionTemplate sqlSession, Board board) {
	
		return sqlSession.update("boardMapper.updateBoard",board) ;
	}

	public ArrayList<Board> selectTopList(SqlSessionTemplate sqlSession) {
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectTopList");
	}

	public int insertReply(SqlSessionTemplate sqlSession, Reply r) {
		
		return sqlSession.insert("boardMapper.insertReply",r);
	}

	public ArrayList<Reply> selectReplyList(SqlSessionTemplate sqlSession, int bno) {
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectReplyList",bno);
	}

}
