package com.db.common.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.db.common.annotation.RequestLog;
import com.db.common.util.IPUtils;
import com.db.sys.dao.SysLogDao;
import com.db.sys.entity.SysLog;
import com.db.sys.entity.SysUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 切面对象类型-切面:是对扩增业务的封装
 * 内部包括:
 * 1)实现业务的方法(advice:通知)
 * 2)切入业务的点(切入点:pointcut)
 * @author jiangzhengdong
 * @since 2019-04-17
 */
@Order(2) //优先级高,本身没有事务,调用有事务的方法时,会建立新的事务,改为默认的readOnly=false
@Aspect 
@Service
public class SysLogAspect { // 日志切面

	@Pointcut("bean(*ServiceImpl)") 
	public void pointCut() {}

	/**
	 * @Around 环绕通知:目标业务前后都可以添加扩展业务
	 * "bean(sysUserServiceImpl)" 粗粒度切入点表达式:
	 * @param jp 连接点:封装了要执行的方法的信息
	 * @return
	 * @throws Throwable
	 */
//	@Around("pointCut()")
	@Around("@annotation(com.db.common.annotation.RequestLog)")
	public Object aroundMethod(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("order2");
		//1.目标业务执行前的记录
		long t1 = System.currentTimeMillis();
		//2.执行目标业务(底层通过反射执行目标业务)
		Object result = jp.proceed();
		//3.目标业务执行之后的记录
		long t2 = System.currentTimeMillis();
//		System.out.println("执行时间:"+(t2-t1));
		saveObject(jp,(t2-t1));
		//4.返回目标业务执行的结果
		return result;
	}
	@Autowired
	private SysLogDao sysLogDao;

	private void saveObject(ProceedingJoinPoint jp, long time) throws JsonProcessingException, NoSuchMethodException, SecurityException {
		// 1.获取日志信息
		// 获取方法签名
		MethodSignature ms=
				(MethodSignature)jp.getSignature();
		Class<?> targetClass=
				jp.getTarget().getClass();
		String className=targetClass.getName();
		//获取接口声明的方法
		String methodName=ms.getMethod().getName();// 接口名
		Class<?>[] parameterTypes=ms.getMethod().getParameterTypes();
		//获取目标对象方法
		Method targetMethod=targetClass.getDeclaredMethod(
				methodName,parameterTypes);
//		//判定目标方法上是否有RequestLog注解
//		boolean flag=
//				targetMethod.isAnnotationPresent(RequestLog.class);
		//假如目标方法对象上有注解,我们获取注解定义的操作值
//		if(flag){
			// 获取登陆用户
			SysUser user=//此工具类需要学完shiro，AOP再进行自定义实现
					(SysUser)SecurityUtils.getSubject().getPrincipal();
			String username = user.getUsername();
			//获取方法参数
			Object[] paramsObj=jp.getArgs();
//			System.out.println("paramsObj="+paramsObj);
			//将参数转换为字符串
			String params=new ObjectMapper()
					.writeValueAsString(paramsObj);
			//2.封装日志信息
			SysLog log=new SysLog();
			// 2.封装日志信息
			log.setCreatedTime(new Date());
			log.setUsername(username);//登陆的用户
			log.setIp(IPUtils.getIpAddr()); //获取ip地址的工具类
			log.setMethod(className+"."+methodName);
			log.setParams(params);
			log.setTime(time);
			RequestLog requestLog=
					targetMethod.getDeclaredAnnotation(RequestLog.class);
			log.setOperation(requestLog.value());
			// 3.保存到数据库
			sysLogDao.insertObject(log);
//		}
	}

}
