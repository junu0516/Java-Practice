package com.kh.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.kh.spring.member.model.vo.Member;

@Aspect
@Component
public class AfterReturningAspect {

	private Logger log = LoggerFactory.getLogger(AfterReturningAspect.class);
	
	@AfterReturning(pointcut = "execution(* com.kh.spring..*ServiceImpl.login*(..))", returning="returnObj")
	public void loggerAdvice(JoinPoint joinPoint, Object returnObj) {
		
		if(returnObj instanceof Member) {
			
			Member member = (Member)returnObj;
			
			if(member.getUserId().equals("admin")) {
				log.info("[log] : 관리자 접속");
			}else {
				log.debug("[log] : 유저 "+member.getUserId()+" 접속");
			}		
		}
		
	}
}
