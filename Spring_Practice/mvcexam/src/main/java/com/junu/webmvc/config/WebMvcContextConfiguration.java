package com.junu.webmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/*
 * Dispatcher Servlet이 어떻게 동작하는 지를 구현해보자
 * 
 * 
 * @EnableWebMvc를 이용하면 기본적인 설정이 모두 자동으로 되지만,
 * 기본 설정 이외의 설정이 필요할 경우 해당 클래스를 상속받은 후, 메소드를 오버라이딩해서 구현
 * 
 * */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.junu.webmvc.controller"}) //Component 스캔시 어떤 패키지를 스캔할 지를 반드시 명시, 여기서는 controller 패키지 탐색
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) { //요청이 들어왔을 때, 특정 url 요청에 대해서 알맞은 디렉토리에서 찾도록 설정(그렇지 않으면 죄다 매핑으로 찾으려 들기 때문에 오류 발생)
		// TODO Auto-generated method stub
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
	}
	
	@Override //default servlet handler 사용하도록 하는 메소드
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		configurer.enable(); //enable() 메소드를 통해 매핑정보가 없는 url 요청은 서블릿에 대해 DefaultServletHandler가 처리하게
		/*
		 * 즉, WAS의 DefaultServlet으로 넘기면,
		 * DefaultServlet의 static 자원을 읽어들이는 것
		 * 
		 * */
	}

	@Override //특정 url에 대한 처리를 컨트롤러 클래스 작성 없이 매핑하도록 함
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		System.out.println("addViewControllers 호출");
		registry.addViewController("/").setViewName("main"); // /로 들어온 요청에 대해 main이라는 view name을 찾아 보여줌(메인 페이지를 디폴트로 두는 것으로 이해하자)
	}
	
	/*
	 * 하지만 view name만 가지고 모든 view 정보를 당연히 찾을 수 없음
	 * view 정보는 아래의 getInternalResourceViewResolver() 메소드로 찾음
	 * */
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/"); //맨 앞에는 view를 담는 공통 경로를,(접두사)
		resolver.setSuffix(".jsp"); //맨 뒤에는 파일 확장자를(접미사) ...
		//views 디렉토리에서 viewname.jsp로 뷰 정보를 찾는 구조인 것!(여기서는 main.jsp)
		return resolver;
	}

	/*
	 * 필요한 메소드를 모두 구성했으면,
	 * DispatcherServlet를 FrontController로 설정해야 함
	 * 
	 * web.xml에서 설정
	 * */

}
