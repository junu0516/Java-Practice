package org.edwith.webbe.calculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages= {"org.edwith.webbe.calculator.controller"})
public class MvcConfig implements WebMvcConfigurer {


	//DifaultServlet에 대한 설정 처리
	//DispatcherServlet에서 처리하지 못하는  URL은 DefaultServlet이 처리하는 것
	//해당 설정이 없으면 자동 생성된 Swagger 페이지를 볼 수 없음
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	/*
	 * Swagger 사용시 Docket Bean을 품고 있는 설정 클래스 1개가 기본으로 필요함
	 * 만일 스프링부트를 사용할 경우에는 기본적인 설정파일 하나로도 Swagger, Swagger UI 둘 다 사용 가능하지만,
	 * Spring MVC의 경우에는 Swagger UI를 위한 별도의 설정이 필요함
	 * 
	 * */
	@Bean
	public Docket api() {//어떤 경로에  web-api들을 자동으로 문서화할 것인지에 대한 설정
		return new Docket(DocumentationType.SWAGGER_2)
						.select()
						.apis(RequestHandlerSelectors.any())
						.paths(PathSelectors.ant("/api/**"))
						.build()
						.apiInfo(apiInfo())
						.useDefaultResponseMessages(false);
	}
	
	private ApiInfo apiInfo() {
		Contact contact = new Contact("이준우","https://www.edwith.org","junu0516@yonsei.ac.kr");
		ApiInfo apiInfo = new ApiInfo("Swagger Sample","APIs Sample","Sample Doc 0.1v","",contact,"This sentence will be displayed.","/");
		return apiInfo;
	}
	
}
