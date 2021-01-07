package com.kh.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Reply;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class ReplyInsertServlet
 */
@WebServlet("/rinsert.bo")
public class ReplyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String content = request.getParameter("content");
		int bId = Integer.parseInt(request.getParameter("bId"));
		int writer = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		Reply r = new Reply();
		r.setReplyContent(content);
		r.setRefBoardId(bId); //게시물 아이디
		r.setReplyWriter(String.valueOf(writer));
		
		int result = new BoardService().insertReply(r);
		PrintWriter out = response.getWriter();
		
		if(result>0) {
			out.print("success");
		}else {
			out.print("fail");
		}
		
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
