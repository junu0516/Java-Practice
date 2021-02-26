package com.kh.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kh.spring.member.model.vo.Member;

public class BoardListInterceptor extends HandlerInterceptorAdapter {

	private Logger log = LoggerFactory.getLogger(BoardListInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			log.info("비로그인 상태에서 ["+request.getRequestURI()+"]에 접근하려고 합니다.");
			
			request.setAttribute("msg", "로그인 후 이용하세요");
			request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);
			
			return false; //컨트롤러로 넘어가지 않도록 false를 반드시 반환해야 함(true일 경우 controller 실행)
		}else {//세션에 로그인된 유저의 정보가 존재할 경우
			return super.preHandle(request, response, handler);
		}
		
	}

}
