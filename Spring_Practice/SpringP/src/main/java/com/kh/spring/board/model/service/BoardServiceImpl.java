package com.kh.spring.board.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dao.BoardDao;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.board.model.vo.Reply;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	BoardDao boardDao;
	
	@Override
	public int selectListCount() {
		
		return boardDao.selectListCount(sqlSession);
	}

	@Override
	public ArrayList<Board> selectList(PageInfo pi) {
		
		return boardDao.selectList(sqlSession,pi);
	}

	@Override
	public int insertBoard(Board board) {
		
		return boardDao.insertBoard(sqlSession,board);
	}

	@Override
	public int updateCount(int bno) {
		
		return boardDao.updateCount(sqlSession,bno);
	}

	@Override
	public Board selectBoard(int bno) {
		
		return boardDao.selectBoard(sqlSession,bno);
	}

	@Override
	public int deleteBoard(int bno) {
		
		return boardDao.deleteBoard(sqlSession,bno);
	}

	@Override
	public int updateBoard(Board board) {
		
		return boardDao.updateBoard(sqlSession,board);
	}

	@Override
	public ArrayList<Board> selectTopList() {
		
		return boardDao.selectTopList(sqlSession);
	}

	@Override
	public int insertReply(Reply r) {
		
		return boardDao.insertReply(sqlSession,r);
	}

	@Override
	public ArrayList<Reply> selectReplyList(int bno) {

		return boardDao.selectReplyList(sqlSession,bno);
	}

}
