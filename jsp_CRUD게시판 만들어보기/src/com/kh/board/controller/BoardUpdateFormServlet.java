
package com.kh.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;

/**
 * Servlet implementation class BoardUpdateFormServlet
 */
@WebServlet("/updateForm.bo")
public class BoardUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int bno = Integer.parseInt(request.getParameter("bno"));
		Board board = new BoardService().selectUpdateBoard(bno);
		Attachment at = new BoardService().selectAttachment(bno);
		
		if(board != null) {
			request.setAttribute("board", board);
			request.setAttribute("at", at);
			request.getRequestDispatcher("views/board/boardUpdateForm.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "수정할 게시글을 불러오는 데 실패하였습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
