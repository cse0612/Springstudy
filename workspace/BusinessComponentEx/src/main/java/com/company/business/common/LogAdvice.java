package com.company.business.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect	// Pointcut + Advice
public class LogAdvice {
	
	//pointcut
	@Pointcut("execution(* com.company.business..*Impl.*(..))")
	public void allPointcut() {}
	
	@Before("allPointcut()")	//Advice
	public void printLog() {
		System.out.println("[공통로그] 비지니스 로직 수행전 동작");
	}
}
