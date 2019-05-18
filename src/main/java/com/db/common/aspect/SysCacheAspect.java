package com.db.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
@Order(3)
@Aspect
@Service
public class SysCacheAspect {
	@Around("@annotation(com.db.common.annotation.RequiredCache)")
	public Object aroundMehtod(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("从缓存取数据");
		Object result = jp.proceed();
		return result;
		
	}
}
