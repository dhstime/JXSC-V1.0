package com.db.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.common.vo.JsonVO;
import com.db.common.vo.PageObject;
import com.db.sys.entity.SysLog;
import com.db.sys.service.SysLogService;
@Controller
@RequestMapping("/log/")//请求路径映射
public class SysLogController {
	@Autowired
	private SysLogService sysLogService;
	
	@GetMapping(value= {"doLogListUI","LogListUI"})//请求路径映射,或者rest架构风格
	public String doLogListUI() {
		return "sys/log_list";
	}
	//rest风格实例:
//	@RequestMapping(value = "/fetch/{id:[a-z]+}/{name}", method = RequestMethod.GET)  
//    String getDynamicUriValueRegex(@PathVariable("name") String name) {  
//        System.out.println("Name is " + name);  
//        return "Dynamic URI parameter fetched using regex";  
//    } 
//	方法 getDynamicUriValueRegex() 会在发起到 localhost:8080/home/fetch/category/shirt 的请求时执行。
//	不过，如果发起的请求是 /home/fetch/10/shirt 的话，会抛出异常，因为这个URI并不能匹配正则表达式。 
	/**
	 * 返回值要转换为json格式的串
	 * 1)添加依赖(jackson)
	 * 2)配置文件中添加:<mvc:annotation-driven />
	 * 3)使用@ResponseBody注解修饰方法
	 * 4)springmvc默认支持Jackson,其他的如fastjson配置文件要另修改
	 * @param username
	 * @param pageCurrent
	 * @return
	 */
	@RequestMapping("doFindPageObjects")
	@ResponseBody //告诉springmvc该方法的返回值转换成一个串,<mvc:annotation-driven />提供了bean对象
	public JsonVO doFindPageObjects(@RequestParam(required=false)String username,Integer pageCurrent) {
		PageObject<SysLog> pageObject = sysLogService.findPageObjects(username, pageCurrent);
		return new JsonVO(pageObject);
	}
	@PostMapping("doDeleteObjects")//请求方式映射
	@ResponseBody
	public JsonVO doDeleteObjects(@RequestParam("ids")Integer...ids) {//请求参数映射,@RequestParam注解,默认required=true
		int rows = sysLogService.deleteObjects(ids);
		return new JsonVO("delete ok,rows="+rows);
	}

}
