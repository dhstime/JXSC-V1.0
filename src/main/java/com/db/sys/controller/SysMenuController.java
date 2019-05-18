package com.db.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.common.vo.JsonVO;
import com.db.sys.entity.SysMenu;
import com.db.sys.service.SysMenuService;
@Controller
@RequestMapping("/menu/")
public class SysMenuController {
	
	@Autowired
	private SysMenuService sysMenuService;
	
	@RequestMapping("doMenuListUI")
	public String doMenuListUI(){
	return "sys/menu_list";
	}
	@RequestMapping("doMenuEditUI")
	public String doMenuEditUI(){
		return "sys/menu_edit";
	}
	
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonVO doFindObjects() {
		return new JsonVO(sysMenuService.findObjects());
	}
	
	@RequestMapping("doDeleteObject")//使用了TreeGrid-JQuery插件
	 @ResponseBody
	 public JsonVO doDeleteObject(Integer id){
		 sysMenuService.deleteObject(id);
		 return new JsonVO("delete OK");
	 }
	
	@RequestMapping("doFindZtreeMenuNodes")//使用ZTree-JQuery插件
	 @ResponseBody
	 public JsonVO doFindZtreeMenuNodes(){
		 return new JsonVO(
		 sysMenuService.findZtreeMenuNodes());
	 }
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonVO doSaveObject(SysMenu entity){
		sysMenuService.saveObject(entity);
		return new JsonVO("save ok");
	}
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonVO doUpdateObject(SysMenu entity){
	    sysMenuService.updateObject(entity);
	    return new JsonVO("update ok");
	}
}
