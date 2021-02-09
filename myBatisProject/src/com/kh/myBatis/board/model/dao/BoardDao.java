package com.kh.myBatis.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.myBatis.board.model.vo.Board;
import com.kh.myBatis.board.model.vo.PageInfo;
import com.kh.myBatis.board.model.vo.SearchCondition;

public class BoardDao {

	public ArrayList<Board> selectList(SqlSession sqlSession, PageInfo pageInfo) {
		
		//건너뛰게 될 페이지 개수를 설정해야 함(currentPage가 2이상일 경우에는 일정수만큼 앞의 게시물들을 건너뛰고 보여줘야 하기 때문)
		int offset = (pageInfo.getCurrentPage()-1)*pageInfo.getBoardLimit();
		/*
		 * 1~5 : 0개를 건너뛰고 1~5까지 5개 조회(1페이지)
		 * 6~10 : 5개를건너뛰고, 6~10까지 5개 조회(2페이지)
		 * 11~15 : 10개를 건너뛰고, 11~15까지 5개 조회(3페이지)
		 * 
		 * ..와 같은 원리로 offset의 개수만큼 게시글을 건너뛰고 그 이후로 5개를 조회하는 것
		 * currentPage가 n일 경우에, (한 페이지당 보여줄 게시글의 개수)*(n-1)개만큼의 게시물을 건너뛰는 것
		 * */
		
		//RowBounds : MyBatis에서 기본적으로 제공하는 페이징 처리를 위한 클래스
		/*
		 * 이전에는 페이징 처리를 위한 정보를 직접 쿼리문에 담아서, 원하는 개수만큼 DB로부터 게시물을 불러왔다면,
		 * MyBatis를 사용하게 되면 RowBounds 클래스를 통해 개수 정보만 넘기면 알아서 원하는 개수만큼 DB에서 게시물 리스트를 불러와준다는 것
		 * 
		 * 매개변수로 원하는 구간의 정보를 넘겨서, 해당 구간의 데이터를 DB로부터 불러오는 원리
		 * 
		 * 단, 내부 구조상 건너 뛰어야 할 데이터도 일단은 찾아서 조회하기 때문에 데이터의 크기가 방대할 경우에는 비효율적임
		 * 적당한 양의 데이터에서 필요한 구간만큼만 뽑아오고 싶을 때 사용하도록 하자.
		 * 
		 * */
		RowBounds rowBounds = new RowBounds(offset,pageInfo.getBoardLimit());//매개변수 : 건너뛸 게시물의 개수, 한 페이지당 보여줄 게시글의 최대 개수
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectList",null,rowBounds); //parameter가 없기 때문에 null을 넣어준 것
	}

	public int selectListCount(SqlSession sqlSession) {
		
		return sqlSession.selectOne("boardMapper.selectListCount");
	}

	public int selectListCount(SqlSession sqlSession, SearchCondition searchCondition) {
		
		return sqlSession.selectOne("boardMapper.selectListCountCon",searchCondition);
	}

	public ArrayList<Board> selectList(SqlSession sqlSession, PageInfo pageInfo, SearchCondition searchCondition) {
		
		int offset = (pageInfo.getCurrentPage()-1)*pageInfo.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset,pageInfo.getBoardLimit());
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectListCon",searchCondition,rowBounds); //여기서는 parameter로 searchCondition을 전달해줘야 함
	}
	
	public int updateCount(SqlSession sqlSession, int bno) {
		
		return sqlSession.update("boardMapper.updateCount",bno);
	}

	public Board selectBoard(SqlSession sqlSession, int bno) {

		return sqlSession.selectOne("boardMapper.selectBoard",bno);
	}

	public int updateBoard(SqlSession sqlSession, Board board) {

		return sqlSession.update("boardMapper.updateBoard",board);
	}

	public int deleteBoard(SqlSession sqlSession, Board board) {

		return sqlSession.update("boardMapper.deleteBoard",board);
	}
	
}
