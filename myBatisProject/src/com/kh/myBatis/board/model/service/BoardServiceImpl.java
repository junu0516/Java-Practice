package com.kh.myBatis.board.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import static com.kh.myBatis.common.Template.*;

import com.kh.myBatis.board.model.dao.BoardDao;
import com.kh.myBatis.board.model.vo.Board;
import com.kh.myBatis.board.model.vo.PageInfo;
import com.kh.myBatis.board.model.vo.SearchCondition;

public class BoardServiceImpl implements BoardService {
	private BoardDao boardDao = new BoardDao();
	
	@Override
	public ArrayList<Board> selectList(PageInfo pageInfo) throws Exception {
		SqlSession sqlSession = getSqlSession();
		
		ArrayList<Board> boardList = boardDao.selectList(sqlSession,pageInfo);
		sqlSession.close();
		
		return boardList;
	}

	@Override
	public ArrayList<Board> selectList(PageInfo pageInfo, SearchCondition searchCondition) throws Exception {
		SqlSession sqlSession = getSqlSession();
		
		ArrayList<Board> boardList = boardDao.selectList(sqlSession,pageInfo,searchCondition);
		
		sqlSession.close();
		
		return boardList;
	}
	
	@Override
	public int getListCount() throws Exception {
		SqlSession sqlSession = getSqlSession();
		
		int listCount = boardDao.selectListCount(sqlSession);
		
		sqlSession.close();
		
		return listCount;
	}
	
	@Override
	public int getListCount(SearchCondition searchCondition) throws Exception {
		SqlSession sqlSession = getSqlSession();
		
		int listCount = boardDao.selectListCount(sqlSession,searchCondition);
		
		sqlSession.close();
		
		return listCount;
	}

	@Override
	public Board selectBoard(int bno) throws Exception {
		SqlSession sqlSession = getSqlSession();
		
		//게시물 클릭시 조회수 업데이트 
		int result = boardDao.updateCount(sqlSession,bno);
		Board board = null;
		
		if(result>0) {
			sqlSession.commit();
			board = boardDao.selectBoard(sqlSession,bno);
		}else {
			sqlSession.rollback();
			throw new Exception("게시글 조회 과정에서 에러 발생");
			
		}
		
		sqlSession.close();
		
		return board;
	}

	@Override
	public void updateBoard(Board board) throws Exception {
		SqlSession sqlSession = getSqlSession();
		
		int result = boardDao.updateBoard(sqlSession,board);
		
		if(result>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
			throw new Exception("게시글 수정 실패");
		}
		
		sqlSession.close();
	}

	@Override
	public void deleteBoard(Board board) throws Exception {
		SqlSession sqlSession = getSqlSession();
		
		int result = boardDao.deleteBoard(sqlSession,board);
		
		if(result>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
			throw new Exception("게시글 삭제 실패");
		}
		
		sqlSession.close();
		
	}	

}
