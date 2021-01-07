package com.kh.board.model.dao;

import static com.kh.common.JDBCTemplate.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.PageInfo;
import com.kh.board.model.vo.Reply;
import com.kh.member.model.dao.MemberDao;

public class BoardDao {
	private Properties prop = new Properties();
	//생성자
	public BoardDao() {
		String fileName = MemberDao.class.getResource("/sql/board/board-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));		
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getListCount(Connection conn) {
		// TODO Auto-generated method stub
		int listCount = 0;
		
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("getListCount"); //properties파일에서 해당하는 쿼리 키 가져오면 됨
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}
	
	public ArrayList<Board> selectList(Connection conn, PageInfo pi) {
		// TODO Auto-generated method stub
		ArrayList<Board> list = new ArrayList<Board>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
/*		selectList=SELECT * FROM (SELECT ROWNUM RNUM, A.* FROM (SELECT BOARD_NO, CATEGORY_NAME, BOARD_TITLE, USER_ID, COUNT, CREATE_DATE FROM BOARD B JOIN CATEGORY USING(CATEGORY_NO) JOIN MEMBER ON (BOARD_WRITER=USER_NO) WHERE BOARD_TYPE=1 AND B.STATUS='Y' ORDER BY BOARD_NO DESC) A) WHERE RNUM BETWEEN ? AND ?
*/
		
		//start row와 end row 계산해서 식 잘 세워보자 ... 
		int startRow = (pi.getCurrentPage()-1)*pi.getBoardLimit()+1;
		int endRow = startRow + pi.getBoardLimit()-1;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Board(rset.getInt("BOARD_NO"),
									rset.getString("CATEGORY_NAME")
									,rset.getString("BOARD_TITLE")
									,rset.getString("USER_ID")
									,rset.getInt("COUNT")
									,rset.getDate("CREATE_DATE")
						));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public int insertBoard(Connection conn, Board b) {
		// TODO Auto-generated method stub
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(b.getCategory()));
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setInt(4, Integer.parseInt(b.getBoardWriter())); 
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int insertAttachment(Connection conn, Attachment at) {
		// TODO Auto-generated method stub
		int result = 0;	
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
				
			result = pstmt.executeUpdate();
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int increaseCount(Connection conn, int bno) {
		// TODO Auto-generated method stub
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,bno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public Board selectBoard(Connection conn, int bno) {
		// TODO Auto-generated method stub
		Board b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectBoard");
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				b = new Board(rset.getInt("BOARD_NO"),
									rset.getString("CATEGORY_NAME")
									,rset.getString("BOARD_TITLE")
									,rset.getNString("BOARD_CONTENT")
									,rset.getString("USER_ID")
									,rset.getInt("COUNT")
									,rset.getDate("CREATE_DATE")
						);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return b;
	}
	
	public Attachment selectAttachment(Connection conn, int bno) {
		// TODO Auto-generated method stub
		Attachment at = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
	
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				at = new Attachment();
				at.setFileNo(rset.getInt("FILE_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}finally {
				close(rset);
				close(pstmt);
		}	
		return at;
	}
	
	public int updateBoard(Connection conn, Board b) {
		// TODO Auto-generated method stub
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(b.getCategory()));
			pstmt.setString(2,b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setInt(4, b.getBoardNo());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateAttachment(Connection conn, Attachment at) {
		// TODO Auto-generated method stub
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAttachment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getChangeName());
			pstmt.setString(2,at.getOriginName());
			pstmt.setString(3,at.getFilePath());
			pstmt.setInt(4, at.getFileNo());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertNewAttachment(Connection conn, Attachment at) {
		// TODO Auto-generated method stub
		int result = 0;	
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertNewAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			//insertNewAttachment=INSERT INTO ATTACHMENT VALUES(SEQ_FNO.NEXTVAL, ?, ?, ?, ?, SYSDATE, 1, DEFAULT)
			pstmt.setInt(1, at.getRefBoardNo());
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setString(4, at.getFilePath());
			result = pstmt.executeUpdate();				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteBoard(Connection conn, int bid) {
		// TODO Auto-generated method stub
		int result = 0;	
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteAttachment(Connection conn, int bid) {
		// TODO Auto-generated method stub
		int result = 0;	
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	
		return result;
	}

	public ArrayList<Board> selectThList(Connection conn) {
		// TODO Auto-generated method stub
		ArrayList<Board> list = new ArrayList<Board>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectThList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			/*			
					SELECT BOARD_NO, BOARD_TITLE, COUNT, CHANGE_NAME \
					FROM BOARD JOIN (SELECT * FROM ATTACHMENT \
					WHERE FILE_NO IN( \
					SELECT MIN(FILE_NO) FILE_NO FROM ATTACHMENT WHERE STATUS='Y' GROUP BY REF_BNO)) ON (REF_BNO = BOARD_NO) \
					WHERE BOARD.STATUS='Y' AND FILE_LEVEL=2 ORDER BY BOARD_NO DESC
			*/
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setCount(rset.getInt("COUNT"));
				b.setTitleImg(rset.getString("CHANGE_NAME"));
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertThBoard(Connection conn, Board b) {
		// TODO Auto-generated method stub
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertThBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setInt(3, Integer.parseInt(b.getBoardWriter()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertAttachment(Connection conn, ArrayList<Attachment> fileList) {
		// TODO Auto-generated method stub
		int result = 0;	
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachment");
		
		try {
			for(int i=0;i<fileList.size();i++) {
				Attachment at = fileList.get(i);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePath());
				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Attachment> selectThumbnail(Connection conn, int bid) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Attachment> list = null;
		
		String sql = prop.getProperty("selectAttachment");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			rset = pstmt.executeQuery();
			list = new ArrayList<Attachment>();
			
			while(rset.next()) {
				Attachment at = new Attachment();
				at.setFileNo(rset.getInt("FILE_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
				
				list.add(at);
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
		}finally {
				close(rset);
				close(pstmt);
		}	

		return list;
	}

	public ArrayList<Board> selectTopList(Connection conn) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Board> list = new ArrayList<Board>();
		
		String sql = prop.getProperty("selectTopList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Board board = new Board();
				board.setBoardNo(rset.getInt("BOARD_NO"));
				board.setBoardTitle(rset.getString("BOARD_TITLE"));
				board.setTitleImg(rset.getString("CHANGE_NAME"));
				
				list.add(board);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Reply> selectRlist(Connection conn, int bId) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Reply> list = new ArrayList<Reply>();
		String sql = prop.getProperty("selectRlist");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Reply(rset.getInt("REPLY_NO"),
								   rset.getString("REPLY_CONTENT"),
								   rset.getString("USER_ID"),
								   rset.getDate("CREATE_DATE")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int insertReply(Connection conn, Reply r) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, r.getReplyContent());
			pstmt.setInt(2, r.getRefBoardId());
			pstmt.setString(3, r.getReplyWriter());
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
}
