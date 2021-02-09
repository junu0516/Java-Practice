package org.edwith.webbe.calculator.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	//Spring 기본 설정 파일 클래스를 지정
	//해당 클래스가 web.xml의 기능을 대체하게 될 것 -> web.xml을 삭제해야 정상적으로 해당 클래스가 이니셜라이저로 기능하게 됨
	
	//ApplicationConfig.class를 직접 작성해야 함
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {ApplicationConfig.class};
	}
	
	//Spring MVC 설정 파일 클래스 지정
	//MvcConfig.class를 직접 작성해야 함
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] {MvcConfig.class};
	}
	
	//DispatcherServlet이 동작할 매핑정보를 설정
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	//인코딩 필터 설정
	@Override
	protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");

        return new Filter[]{encodingFilter};
	}
	

}
