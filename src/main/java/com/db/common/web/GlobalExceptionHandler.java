package com.db.common.web;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.common.vo.JsonVO;
/**
 * @ControllerAdvice 修饰的类为一个控制层全局异常处理类,前端控制器收到异常后会查找异常处理
 * @author jiangzhengdong
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * @ExceptionHandler 指定类型处理异常的方法,及其子类型
	 * @param e
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public JsonVO doHandleServiceException(RuntimeException e) {
		e.printStackTrace();//输出详细信息到控制台,也可以输出到日志
		return new JsonVO(e);//封装异常基本信息,呈现给客户端
	}
	
	@ExceptionHandler(ShiroException.class)
	@ResponseBody
	public JsonVO doHandleShiroException(ShiroException e) {
		JsonVO r = new JsonVO();
		r.setState(0);
		if(e instanceof UnknownAccountException) {
			r.setMessage("账户不存在");
		}else if(e instanceof LockedAccountException) {
			r.setMessage("账户已被禁用,请联系管理员");
		}else if(e instanceof IncorrectCredentialsException) {
			r.setMessage("密码错误");
		}else if(e instanceof AuthorizationException){
			r.setMessage("没有此操作权限");
		}else {
			r.setMessage("系统异常");
		}
		return r;
	}
	
}
