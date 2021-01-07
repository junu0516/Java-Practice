package com.kh.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class EncodingFilter
 */
/*
* 어떤 서블릿이 실행되기 전에 공통적으로 처리해야할 내용이 있을 경우 필터를 사용해서 처리할 수 있다. 
* 
* 즉, 서블릿이 바로 실행되지 않고 이 필터를 거치고 나서 그담에 서블릿이 실행 될 것
* 
* * 이 필터 클래스의 라이프 사이클 
* 1) 자동으로 init()메소드가 호출되면서 Filter 클래스가 생성될것
* 2) 그리고 나서 doFilter(request, response, chain)메소드가 호출됨
*    : 즉, 서블릿 가기 전에 실행할 내용을 이 doFilter 메소드 내에 작성해주면 됨 
* 3) destroy()는 해당 이 필터 객체가 다 끝나면 소멸될 때 자동으로 실행됨
* 
* * 그럼 이렇게 만든 필터 클래스를 적용시키는 방법 2가지 있음 (둘 중에 하나 하면 된다)
* 1) web.xml에 등록하는 방법
*    > web.xml에 해당 만든 filter 클래스를 등록하고 어떤 서블릿이 실행되기 전에 이 필터를 실행시킬 건지 지정하는 방법
*    
* 2) 어노테이션을 이용하는 방법
*    > 해당 이 필터 클래스위에 @WebFilter 어노테이션을 활용해서 어떤 서블릿이 실행되기 전에 이 필터를 거쳐갈건지 지정하는 방법
*    > filterName을 통해서 어떤 역할을 하는 필터인지 지정해주고
	  > urlPatterns를 통해서 어떠한 서블릿을 가기전에 거칠 것인지를 지정해준다. --> /*로 지정하게 되면 모든 서블릿을 뜻한다. 
*/

/*아래와 같이 할 수도 있고, web.xml에서 매핑할 수도 있음*/
//@WebFilter(filterName = "encodingFilter",urlPatterns="/*") /*filter 이름도 지정할 수 있음*/
public class EncodingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		/*
		 * 일일히 서블릿마다 인코딩 지정할 필요 없이, 실행하면 모든 서블릿에 대해 인코딩 필터가 실행되도록 하면 더욱 효율적
		 * 
		 * */
		
		System.out.println("인코딩 필터 동작 start");
		// pass the request along the filter chain
		if(((HttpServletRequest)request).getMethod().equalsIgnoreCase("post")) {/*equalsIgnoreCase : 대소문자 구분하지 않고 동일설한지 판별*/
			System.out.println("post방식이 요청함");
			request.setCharacterEncoding("utf-8");
		} 
		chain.doFilter(request, response);
		System.out.println("서블릿 동작하고 나서 실행");
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
