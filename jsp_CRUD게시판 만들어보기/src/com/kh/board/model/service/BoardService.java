package com.kh.board.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.PageInfo;
import com.kh.board.model.vo.Reply;

public class BoardService {
	
	//게시물 조회용 서비스
	public int getListCount() {
		Connection conn = getConnection();
		int listCount = new BoardDao().getListCount(conn);
		close(conn);
		return listCount;
	}

	public ArrayList<Board> selectList(PageInfo pi) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		ArrayList<Board> list = new BoardDao().selectList(conn,pi);
		close(conn);
		return list;
	}

	public int insertBoard(Board b, Attachment at) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();

		int result1 = new BoardDao().insertBoard(conn,b);
		int result2 = 1; /*default : 1*/
		
		if(at!=null) {/*첨부파일 insert*/
			result2 = new BoardDao().insertAttachment(conn,at); 
		}
		
		if(result1*result2 > 0) {
			commit(conn);
		}else {
			/*=0인 경우는 제대로 처리가 되지 않은 것*/
			rollback(conn);
		}
		
		close(conn);
		
		return result1 * result2;
	}

	public Board selectBoard(int bno) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		
		int result = new BoardDao().increaseCount(conn,bno);
		
		Board b = null;
		if(result>0) {
			commit(conn);
			b = new BoardDao().selectBoard(conn,bno);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return b;
	}

	public Attachment selectAttachment(int bno) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		Attachment at = new BoardDao().selectAttachment(conn,bno);
		
		
		close(conn);
		return at;
	}

	public Board selectUpdateBoard(int bno) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		Board b = new BoardDao().selectBoard(conn, bno);
		close(conn);
		return b;
	}

	public int updateBoard(Board b, Attachment at) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int result1 = new BoardDao().updateBoard(conn,b);
		int result2 = 1;
		if(at != null) {
			if(at.getFileNo() != 0) {
				result2 = new BoardDao().updateAttachment(conn,at);
			}else {
				result2 = new BoardDao().insertNewAttachment(conn,at);
			}
		}
		
		if(result1>0 && result2>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result1*result2;
	}


	public int deleteBoard(int bid) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int result1 = new BoardDao().deleteBoard(conn,bid);
		int result2 = new BoardDao().deleteAttachment(conn,bid);
		
		if(result1>0 && result2>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result1;
	}

	public ArrayList<Board> selectThList() {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		ArrayList<Board> list = new BoardDao().selectThList(conn);
		close(conn);
		return list;
	}

	public int insertThumbnail(Board b, ArrayList<Attachment> fileList) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		BoardDao bdao = new BoardDao();
		int result1 = bdao.insertThBoard(conn,b);
		int result2 = bdao.insertAttachment(conn, fileList);
		if(result1>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
				
		return result1 * result2;
	}

	public ArrayList<Attachment> selectThumbnail(int bid) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		ArrayList<Attachment> list = new BoardDao().selectThumbnail(conn,bid);
		close(conn);
		return list;
	}

	public ArrayList<Board> selectTopList() {
		// TODO Auto-generated method stub'
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectTopList(conn);
		close(conn);
		
		return list;
	}

	public ArrayList<Reply> selectRlist(int bId) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		ArrayList<Reply> list = new BoardDao().selectRlist(conn,bId);
		close(conn);
		return list;
	}

	public int insertReply(Reply r) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int result = new BoardDao().insertReply(conn,r);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
}
