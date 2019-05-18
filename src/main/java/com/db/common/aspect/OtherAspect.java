package com.db.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
@Order(1) //切面顺序,数字越小优先级越高
@Aspect
@Service
public class OtherAspect {
	@Pointcut("bean(sysLogServiceImpl)") 
	public void pointCut() {}
	/**
	 * 前置通知:目标方法执行之前调用
	 */
	@Before("pointCut()")
	 public void beforeMethod() {
		 System.out.println("OtherAspect.beforeMethod()");
	 }
	/**
	 * 后置通知:目标方法执行之后调用
	 */
	@After("pointCut()")
	public void afterMethod() {
		System.out.println("OtherAspect.afterMethod()");
	}
	/**
	 * 返回通知:目标方法正常结束之后调用(二选一)
	 */
	@AfterReturning("pointCut()")
	public void afterReturnMethod() {
		System.out.println("enclosing_type.enclosing_method()");
	}
	/**
	 * 异常通知:目标方法异常结束之后调用(二选一)
	 */
	@AfterThrowing("pointCut()")
	public void afterThrowMethod() {
		System.out.println("OtherAspect.afterThrowMethod()");
	}
	/**
	 * 环绕通知:优先级最高
	 */
	@Around("pointCut()")
	public Object aroundMethod(ProceedingJoinPoint jp) throws Throwable{
		System.out.println("aroundMethod.Before");
		Object result = jp.proceed();
		System.out.println("aroundMethod.After");
		return result;
	}
}
