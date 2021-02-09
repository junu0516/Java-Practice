package com.kh.myBatis.board.model.service;

import java.util.ArrayList;

import com.kh.myBatis.board.model.vo.Board;
import com.kh.myBatis.board.model.vo.PageInfo;
import com.kh.myBatis.board.model.vo.SearchCondition;

public interface BoardService {

	ArrayList<Board> selectList(PageInfo pageInfo) throws Exception;

	int getListCount() throws Exception;

	Board selectBoard(int bno) throws Exception;

	int getListCount(SearchCondition searchCondition) throws Exception;

	ArrayList<Board> selectList(PageInfo pageInfo, SearchCondition searchCondition) throws Exception;

	void updateBoard(Board board) throws Exception;

	void deleteBoard(Board board) throws Exception;

}
