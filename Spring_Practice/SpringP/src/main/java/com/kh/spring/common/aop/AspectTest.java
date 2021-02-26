package com.kh.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class AspectTest {
	
	@Pointcut("execution(* com.kh.spring..*ServiceImpl.*(..))")
	public void beforePointCut() {}
	
	@Before("beforePointCut()")
	public void before(JoinPoint join) throws Exception{
		Signature sig = join.getSignature();
		Object[] params = join.getArgs();
		
		for(Object obj : params) {
			System.out.println("obj ->"+obj);
		}
		System.out.println("메소드 호출 전 확인 "+sig.getDeclaringTypeName()+" : "+sig.getName());
	}
	
	@Pointcut("execution(* com.kh.spring..*ServiceImpl.*(..))")
	public void afterPointCut() {}
	
	@After("afterPointCut()")
	public void after(JoinPoint join) throws Exception{
		Signature sig = join.getSignature();
		
		System.out.println("메소드 종료 후 확인 "+sig.getDeclaringTypeName()+" : "+sig.getName());
	}
	
	@Pointcut("execution(* com.kh.spring..*ServiceImpl.*(..))")
	public void afterReturningPoint() {}
	
	@AfterReturning(pointcut = "afterReturningPoint()", returning="returnObj")
	public void returningPoint(JoinPoint join, Object returnObj) throws Exception{
		Signature sig = join.getSignature();
		
		System.out.println("@AfterReturning 확인 "+sig.getDeclaringTypeName()+" : "+sig.getName()+" returningObj : "+returnObj);
	}
	
	@Pointcut("execution(* com.kh.spring..*ServiceImpl.*(..))")
	public void afterThrowingPoint() {}
	
	@AfterThrowing(pointcut="afterThrowingPoint()",throwing="exceptionObj")
	public void throwingPoint(JoinPoint join, Exception exceptionObj) throws Exception{
		Signature sig = join.getSignature();
		
		System.out.println("@AfterThrowing 확인 "+sig.getDeclaringTypeName()+" : "+sig.getName()+" throwing : "+exceptionObj);
		System.out.println("message : "+exceptionObj.getMessage());
		System.out.println("예외 발생 종류 : "+exceptionObj.getClass().getName());
	}
	
	@Pointcut("execution(* com.kh.spring..*ServiceImpl.*(..))")
	public void aroundPoint() {}
	
	@Around("aroundPoint()")
	public Object aroundLog(ProceedingJoinPoint join) throws Throwable{
		String methodName = join.getSignature().getName();
		
		StopWatch stopWatch = new StopWatch();
		
		stopWatch.start();
		Object obj = join.proceed(); //전후를 나누는 기준
		stopWatch.stop();
		
		System.out.println(methodName+" () 소요시간(ms) : "+stopWatch.getTotalTimeMillis()+" 초"); //start() ~ stop() 까지 걸린 시간
		return obj;
	}
}
