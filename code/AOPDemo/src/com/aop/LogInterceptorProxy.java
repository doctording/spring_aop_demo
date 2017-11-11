package com.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogInterceptorProxy implements InvocationHandler{
	
	// ������Ķ���
	private Object target;
	
	public void beforeMethod(){
		System.out.println("log start");
	}

	@Override
	public Object invoke(Object proxy, Method m, Object[] args)
			throws Throwable {
		
		beforeMethod();
		
		m.invoke(target, args);
		
		return null;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	
}
