package com.serverlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet1
 */
@WebServlet("/url.do")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//여기에 만들 동적 웹페이지를 작성
		
		//request
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String region = request.getParameter("region");
		String height = request.getParameter("height");
		String[] foodArr = request.getParameterValues("food");
		
		System.out.println("라디오 태그에서 가져온 값들 확인");
		if(foodArr!=null) {
			for(int i=0;i<foodArr.length;i++) {
				System.out.println("food :"+foodArr[i]);
			}
		}
		
		//response
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>입력 결과 출력 화면</title>");
		out.println("<style>");
		out.println("h2{color:red;}");
		out.println("span.name{color:orange; font-weight:bold;}");
		out.println("span.gender{color:yellow; font-weight:bold; background-color:black;}");
		out.println("span.age{color:green; font-weight:bold;}");
		out.println("span.city{color:blue; font-weight:bold;}");
		out.println("span.height{color:navy; font-weight:bold;}");
		out.println("span.food{color:purple; font-weight:bold;}");
		out.println("</style>");
		out.println("<body>");
		out.println("<h2>입력 결과(POST)</h2>");
		out.printf("<span class='name'>%s</span> 님은 ", name);
		out.printf("<span class='age'>%s</span>이시며, ", age);
		out.printf("<span class='region'>%s</span>에 사는 ", region);
		out.printf("키 <span class='height'>%s</span>cm인 ", height);
		out.printf("<span class='gender'>%s</span>입니다. ", gender);
		out.print("좋아하는 음식은 <span class='food'>");

		for (int i = 0; i < foodArr.length; i++) {
			if (i == 0) {
				out.printf("%s", foodArr[i]);
			} else {
				out.printf(", %s", foodArr[i]);
			}
		}
		out.println("</span>입니다.");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
