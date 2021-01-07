package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/list.bo")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// ---------------- 페이징 처리 -----------------
		int listCount;			// 총 게시글 갯수
		int currentPage;		// 현재 페이지 (즉, 요청한 페이지)
		int startPage;			// 현재 페이지에 하단에 보여지는 페이징 바의 시작 수 
		int endPage;			// 현재 페이지에 하단에 보여지는 페이징 바의 끝 수
		int maxPage;			// 전체 페이지에서의 가장 마지막 페이지
		
		int pageLimit;			// 한 페이지 하단에 보여질 페이지 최대 갯수
		int boardLimit;			// 한 페이지에 보여질 게시글 최대 갯수
		
		// * listCount : 총 게시글 갯수 
		listCount = new BoardService().getListCount();
		
		// * currentPage : 현재 페이지 (요청한 페이지)
		currentPage = 1;
		
		// 페이지 전환시 전달받은 페이지가 있을 경우 전달받은 페이지를 currentPage로!
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		// * pageLimit : 한 페이지 하단에 보여질 페이지 최대 갯수 
		pageLimit = 10;
		
		// * boardLimit : 한 페이지에 보여질 게시글 최대 갯수
		boardLimit = 10;
		
		// * maxPage : 총 페이지 수
		/*
		 * ex) boardLimit : 10 이라는 가정 하에
		 * 
		 * 총갯수   / boardLimit
		 * 100.0 / 10 = 10.0 = 10.0		=> 10페이지
		 * 101.0 / 10 = 10.1 = 11.0		=> 11페이지
		 * 105.0 / 10 = 10.5 = 11.0		=> 11페이지
		 * 109.0 / 10 = 10.9 = 11.0		=> 11페이지
		 * 
		 * 총게시글갯수(실수)/boardLimit의 값을 올림한 값!
		 */
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		// * startPage : 현재 페이지에 보여지는 페이징 바의 시작 수
		/*
		 * ex) pageLimit : 10
		 * 1, 11, 21, 31, ...			=> n * 10 + 1
		 * 
		 * currentPage = 1				=> 0 * 10 + 1
		 * currentPage = 5				=> 0 * 10 + 1
		 * currentPage = 10				=> 0 * 10 + 1
		 * 
		 * currentPage = 11				=> 1 * 10 + 1
		 * currentPage = 12				=> 1 * 10 + 1
		 * currentPage = 20				=> 1 * 10 + 1
		 * 
		 * currentPage = 1~10			=> n=0
		 * currentPage = 11~20			=> n=1
		 * 
		 * 								   n = (currentPage - 1) / pageLimit
		 */
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		
		// * endPage : 현재 페이지에 보여지는 페이징 바의 끝 수
		// startPage : 1	=> endPage : 10
		// startPage : 11	=> endPage : 20
		endPage = startPage + pageLimit - 1;
		
		// 단, maxPage가 고작 13까지 밖에 안되면? endPage를 13으로 해줘야됨!
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		// 이 요청한 현재 페이지에 대해 보여져야할 이 페이지 정보들을 boardListView에도 보내줘야됨.. 
		// 어딘가에 담아서 보내주자!
		// 페이지 정보들 PageInfo 객체에 담아주기
		PageInfo pi = new PageInfo(listCount, currentPage, startPage, endPage, maxPage, pageLimit, boardLimit);
		
//		System.out.println(pi);
		
		// 자 그러면 현재 페이지(currentPage)에 보여질 게시글 리스트 조회하기
		ArrayList<Board> list = new BoardService().selectList(pi);
		
		// request에 전달값 담기
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		
		request.getRequestDispatcher("views/board/boardListView.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
