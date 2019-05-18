package com.db.sys.controller;

import java.util.Arrays;
import java.util.Map;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.common.vo.JsonVO;
import com.db.common.vo.PageObject;
import com.db.common.vo.SysUserDeptResult;
import com.db.sys.entity.SysUser;
import com.db.sys.service.SysUserService;

@Controller
@RequestMapping("/user/")
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;
	@RequestMapping("doUserListUI")
	public String doUserListUI(){
		return "sys/user_list";
	}
	@RequestMapping("doUserEditUI")
	public String doUserEditUI(){
		return "sys/user_edit";
	}
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonVO doFindPageObjects(
			String username,Integer pageCurrent){
		PageObject<SysUserDeptResult> pageObject=
				sysUserService.findPageObjects(username,
						pageCurrent);
		return new JsonVO(pageObject);
	}
	@RequestMapping("doValidById")
	@ResponseBody
	public JsonVO doValidById(
			Integer id,
			Integer valid){
		// 获取身份信息
		SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
		sysUserService.validById(
				id,
				valid, 
				user.getUsername());//"admin"用户将来是登陆用户
		return new JsonVO("update ok");
	}
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonVO doSaveObject(
			SysUser entity,
			Integer[] roleIds){
		sysUserService.saveObject(entity,roleIds);
		return new JsonVO("save ok");
	}
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonVO doFindObjectById(
			Integer id){
		Map<String,Object> map=
				sysUserService.findObjectById(id);
		return new JsonVO(map);
	}
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonVO doUpdateObject(
			SysUser entity,Integer[] roleIds){
		sysUserService.updateObject(entity,
				roleIds);
		return new JsonVO("update ok");
	}

	@RequestMapping("doLogin")
	@ResponseBody
	public JsonVO doLogin(String username,String password,Boolean rememberMe){
		//1.获取Subject对象
		Subject subject=SecurityUtils.getSubject();
		//2.通过Subject提交用户信息,交给shiro框架进行认证操作
		//2.1对用户进行封装
		UsernamePasswordToken token=
				new UsernamePasswordToken(
						username,//身份信息
						password);//凭证信息
		if(rememberMe)
		token.setRememberMe(true);
		//2.2对用户信息进行身份认证
		subject.login(token);
		System.out.println("姓名:"+username);
//		SecurityUtils.getSubject().getSession().setTimeout(1800000);
		//分析:
		//1)token会传给shiro的SecurityManager
		//2)SecurityManager将token传递给认证管理器
		//3)认证管理器会将token传递给realm
		return new JsonVO("login ok");
	}
	@RequestMapping("doPwdEditUI")
	public String doPwdEditUI() {
		return "sys/pwd_edit";
	}
	@RequestMapping("doUpdatePassword")
	@ResponseBody
	public JsonVO doUpdatePassword(String pwd,String newPwd,String cfgPwd) {
		sysUserService.updatePassword(pwd, newPwd, cfgPwd);
		return new JsonVO("save ok");
	}
	@RequestMapping("doExcelUsers")
	@ResponseBody
	public JsonVO doExcelUsers() {
		sysUserService.doExcelUsers();
		return new JsonVO("excel ok");
	}
	@RequestMapping("doPdfUsers")
	@ResponseBody
	public JsonVO doPdfUsers() {
		sysUserService.doPDFUsers();
		return new JsonVO("pdf ok");
	}
	
	@RequestMapping("doQueryMenus")
	@ResponseBody
	public JsonVO doQueryMenus() {
		SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
		String[] menus = sysUserService.doQueryMenus(user.getId());
		System.out.println(Arrays.toString(menus));
		return new JsonVO(menus);
	}
	
}
