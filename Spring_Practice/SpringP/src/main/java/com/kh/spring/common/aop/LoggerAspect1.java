package com.kh.spring.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerAspect1 {
	
	private Logger log = LoggerFactory.getLogger(LoggerAspect1.class);
	
	public Object loggerAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		
		Signature signature = joinPoint.getSignature();
		
		String type = signature.getDeclaringTypeName();
		String methodName = signature.getName();
		
		String componentName = "";
		if(type.indexOf("Controller")>=0) {
			componentName = "Controller : ";
		}else if(type.indexOf("Service")>=0) {
			componentName = "ServiceImpl : ";
		}else if(type.indexOf("Dao") >=0) {
			componentName = "Dao : ";
		}
		
		log.info("[Before]"+ componentName + type + "." + methodName + "()");
		Object obj = joinPoint.proceed(); //기준점
		log.info("[After]"+ componentName + type + "." + methodName + "()");		
		
		return obj;
	}

}
