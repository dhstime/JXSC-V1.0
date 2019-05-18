package com.db.common.web;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 编写拦截器对象,对控制层登陆方法,按时间进行访问拦截
 * 说明:拦截器编写好以后需要对拦截器进行配置<mvc:interceptors>
 * 适配类
 * @author jiangzhengdong
 *
 */
@Component
public class HandlerAccessInterceptor extends HandlerInterceptorAdapter {
	// 抽象类不一定有抽象方法,可以用来防止被实例话,方法体中没内容:钩子方法
	
	// 上钩
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("HandlerAccessInterceptor.preHandle()");
		//获取当前系统的日历对象,(时区,年月日,时分秒)
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		long start = c.getTimeInMillis();
		c.set(Calendar.HOUR_OF_DAY, 24);
		long end = c.getTimeInMillis();
		long time = System.currentTimeMillis();
		if(time<start||time>end)
			throw new RuntimeException("不在访问时间之内:4:00:00~22:00:00");
		return true;//true表示放行,false表示拦截
	}
}
