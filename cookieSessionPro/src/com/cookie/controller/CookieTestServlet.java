package com.cookie.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieTestServlet
 */
@WebServlet("/cookieTest.do")
public class CookieTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//쿠키 생성
		Cookie cookie = new Cookie("id","user11");
		
		//유효기간 설정
		cookie.setMaxAge(24*60*60); //1일로 유효기간 설정		
		response.addCookie(cookie);
		
		Cookie cookie2 = new Cookie("email","green@naver.com");
		//유효기간 설정
		//cookie2.setMaxAge(24*60*60); //1일로 유효기간 설정
		response.addCookie(cookie2);
		response.setContentType("text/html;charset=UTF-8;");
		
		response.setContentType("text/html;charset=UTF-8;");
		PrintWriter out=response.getWriter();
		String html="<html>";
		html+="<body>";
		html+="<p>쿠키가 전송되었습니다</p>";
		html+="<button onclick='location.assign(\"checkCookie.do\");'>쿠키값확인</button>";
		html+="</body>";
		html+="</html>";
		out.print(html);
		
		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
