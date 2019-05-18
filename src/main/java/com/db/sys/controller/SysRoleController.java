package com.db.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.common.vo.JsonVO;
import com.db.common.vo.PageObject;
import com.db.sys.entity.SysRole;
import com.db.sys.service.SysRoleService;

@Controller
@RequestMapping("/role/")
public class SysRoleController {
	@Autowired
	private SysRoleService sysRoleService;
	@RequestMapping("doRoleListUI")
	public String doRoleListUI(){
		return "sys/role_list";
	}
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonVO doFindPageObjects(
			String name,Integer pageCurrent){
		PageObject<SysRole> pageObject=
				sysRoleService.findPageObjects(name,pageCurrent);
		return new JsonVO(pageObject);
	}
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonVO doDeleteObject(Integer id){
	 sysRoleService.deleteObject(id);
	return new JsonVO("delete Ok");
	}
	@RequestMapping("doRoleEditUI")
	public String doRoleEditUI(){
			return "sys/role_edit";
	}
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonVO doSaveObject(
	    		SysRole entity,Integer[] menuIds){
	    	sysRoleService.saveObject(entity,menuIds);
	return new JsonVO("save ok");    
	}
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonVO doFindObjectById(Integer id){
		return new JsonVO(sysRoleService.findObjectById(id));
	}
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonVO doUpdateObject(SysRole entity,
	Integer[] menuIds){
			  sysRoleService.updateObject(entity, menuIds);
	 return new JsonVO("update ok");
	 }
	 @RequestMapping("doFindRoles")
	 @ResponseBody
	 public JsonVO doFindObjects(){
	 	 return new JsonVO(sysRoleService.findObjects());
	 }
}