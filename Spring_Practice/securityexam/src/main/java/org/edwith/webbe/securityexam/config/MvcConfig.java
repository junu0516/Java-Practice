package org.edwith.webbe.securityexam.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//스프링 mvc 사용을 위한 설정
@Configuration
@EnableWebMvc //mvc 기반의 설정을 자동으로 실행
@ComponentScan(basePackages= {"org.edwith.webbe.securityexam.controller"})
public class MvcConfig implements WebMvcConfigurer {
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		
		configurer.enable();
	}
	
	//jsp view 파일의 경로 설정
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		
		registry.jsp("/WEB-INF/view/",".jsp");
	}

	// "/"로 요청시 바로 main.jsp로 redirect 하도록 설정
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addRedirectViewController("/", "/main");
	}
	
	
	
}
