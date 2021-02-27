package org.edwith.webbe.securityexam.config;

import org.edwith.webbe.securityexam.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/*
	 * 
	 * AuthenticationFilter : 아이디/암호를 입력해서 로그인할 때 처리해주는 필터
	 * 여기서 아이디에 해당하는 정보를 DB로부터 읽어들일 경우  UserDetailsService 인터페이스를 구현하는 서비스 객체를 이용하는 것
	 * 
	 * */
    @Autowired
    CustomUserDetailsService customUserDetailsService;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		//인증이나 인가가 필요없는 페이지에 대한 설정
		web.ignoring().antMatchers("/webjars/**"); //명시된 경로에 대해서는 인증/인가를 거치지 않아도 됨 ... 여기서는 /webjars 이하의 모든 경로를 명시
	}
	
	
	/*
	 * AuthenticationFilter가 아이디/암호 입력 후, 로그인 처리를 위한 필터로서 기능함
	 * 여기서 아이디에 해당하는 정보를 DB로부터 읽어들일 때 UserDetailsService 인터페이스를 구현한 객체를 활용하는데,
	 * CustomUserDetailsService가 여기서 이러한 객체인 것
	 * 
	 * */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(customUserDetailsService); 
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//인증, 인가에 대한 설정
		http.csrf().disable() //csrf : post 방식으로 값을 전송시 토큰사용을 위한 보안 설정 -> 여기서는 편의상 이를 disable 시켜서 끈 것
			//보통 csrf가 기본으로 설정되어 있는데, 보안성이 높아지지만 초기에는 불편하기 때문에 일단 끈 것
			.authorizeRequests()
			.antMatchers("/","/main", "/memembers/loginerror", "/members/joinform", "/members/join", "/members/welcome").permitAll() //명시한 경로에 대해 모두 permit(누구나 인증/인가 없이 접근이 가능한 것)
			.antMatchers("/securepage","/members/**").hasRole("USER") //로그인 및 USER 권한은 가지고 있어야 접근할 수 있는 URL
			.anyRequest().authenticated() //나머지 경로는 인증이나 인가를 모두 거쳐야 할 것임을 명시
			.and() //로그인 폼에 대한 설정
				.formLogin()
				.loginPage("/members/loginform") //로그인 폼 경로
				.usernameParameter("userId") //로그인 폼에서의 아이디 관련 input 태그의 이름은 userId이어야 한다는 것
				.passwordParameter("password") //마찬가지로 비밀번호 관련 input 태그의 이름이 password이어야 한다는 것
				.loginProcessingUrl("/authenticate") //아이디와 암호를 입력받아 로그인 처리를 하게 될 url ... 이에 대한 처리는 직접 구현하는 것이 아님(form 태그의 action 속성에 정의된 것을 기반으로 프레임워크가 처리해주는 것)
				.failureForwardUrl("/members/loginerror?login_error=1") //로그인 실패시 이동할 에러페이지 경로
				.defaultSuccessUrl("/",true) //로그인 성공시 리다이렉트할 경로
				.permitAll() //permitAll이 붙으면 로그인 폼은 아무나 접근 가능하다는 것
			.and() //로그아웃에 대한 설정
				.logout() 
				.logoutUrl("/logout")
				.logoutSuccessUrl("/"); //세션에서 로그인 정보 비활성화 후 / 로 리다이렉트
	}
	
	//암호화
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
