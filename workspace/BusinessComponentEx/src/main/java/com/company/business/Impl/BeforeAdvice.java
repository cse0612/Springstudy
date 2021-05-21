package com.company.business.Impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class BeforeAdvice {

	@Pointcut("execution(* com.company.business..*Impl.*(..))")
	public void allPointcut() {}
	
	@Before("allPointcut()")
	public void beforeLog(JoinPoint jp) {
		String method = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		
		System.out.println("[사전처리]" + method + "() 메서드 ARGS 정보 : " + args[0].toString());
	}
}
