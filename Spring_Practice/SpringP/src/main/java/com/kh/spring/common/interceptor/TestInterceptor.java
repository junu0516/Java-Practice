package com.kh.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TestInterceptor extends HandlerInterceptorAdapter {
	
	private Logger log = LoggerFactory.getLogger(TestInterceptor.class);

	//DispatcherServlet이 컨트롤러 실행 전 수행하게 되는 부분
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		log.debug("============START============");
		return super.preHandle(request, response, handler);
	}
	
	//컨트롤러에서 DispatcherServlet으로 리턴되는 수간 수행되는 부분
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		log.debug("============VIEW=============");
	}
	
	//모든 작업이 완료된 후 수행
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		log.debug("============END=============");
	}
	
	
	
	
}
