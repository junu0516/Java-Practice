package org.edwith.webbe.securityexam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		//인증이나 인가가 필요없는 페이지에 대한 설정
		web.ignoring().antMatchers("/webjars/**"); //명시된 경로에 대해서는 인증/인가를 거치지 않아도 됨 ... 여기서는 /webjars 이하의 모든 경로를 명시
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//인증, 인가에 대한 설정
		http.csrf().disable() //csrf : post 방식으로 값을 전송시 토큰사용을 위한 보안 설정 -> 여기서는 편의상 이를 disable 시켜서 끈 것
			//보통 csrf가 기본으로 설정되어 있는데, 보안성이 높아지지만 초기에는 불편하기 때문에 일단 끈 것
			.authorizeRequests()
			.antMatchers("/","/main").permitAll() //명시한 경로에 대해 모두 permit(누구나 인증/인가 없이 접근이 가능한 것)
			.anyRequest().authenticated(); //나머지 경로는 인증이나 인가를 모두 거쳐야 할 것임을 명시
	}
	
	//암호화
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
