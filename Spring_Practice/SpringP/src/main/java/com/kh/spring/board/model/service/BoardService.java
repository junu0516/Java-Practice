package com.kh.spring.board.model.service;

import java.util.ArrayList;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.board.model.vo.Reply;

public interface BoardService {
	
	int selectListCount();

	ArrayList<Board> selectList(PageInfo pi);

	int insertBoard(Board board);

	int updateCount(int bno);

	Board selectBoard(int bno);

	int deleteBoard(int bno);

	int updateBoard(Board board);

	ArrayList<Board> selectTopList();

	int insertReply(Reply r);

	ArrayList<Reply> selectReplyList(int bno);
}
