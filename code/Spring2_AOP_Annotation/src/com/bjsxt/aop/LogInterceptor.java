package com.bjsxt.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogInterceptor {
	// ִ�� xx����֮ǰ���� before()����
	@Before("execution(public * com.bjsxt.service..*.add(..))")
	public void before() {
		System.out.println("method before");
	}

}
