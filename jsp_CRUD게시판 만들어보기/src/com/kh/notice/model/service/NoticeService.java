package com.kh.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;
import static com.kh.common.JDBCTemplate.*;

public class NoticeService {

	public ArrayList<Notice> selectList() {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		ArrayList<Notice> list = new NoticeDao().selectList(conn);
		close(conn);
		return list;
	}

	public int insertNotice(Notice notice) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int result = 0;
		result = new NoticeDao().insertNotice(conn,notice);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public Notice selectNotice(int nno) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		//먼저 조회수를 1 증가시켜줌
		int result = new NoticeDao().increaseCount(conn, nno);
		Notice notice = null;
		if(result>0) {
			commit(conn);
			//조회수 증가가 완료되면, 해당 게시물을 불러와서 반환
			notice = new NoticeDao().selectNotice(conn,nno);
		}else {
			rollback(conn);
		}
		close(conn);
		return notice;
	}

	public Notice selectUpdateNotcie(int nno) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		Notice notice = new NoticeDao().selectNotice(conn, nno);
		close(conn);
		return notice;
	}

	public int updateNotice(Notice notice) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int result = new NoticeDao().updateNotice(conn,notice);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteNotice(int nno) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		
		int result = new NoticeDao().deleteNotice(conn,nno);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
}
