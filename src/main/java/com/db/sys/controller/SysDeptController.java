package com.db.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.common.vo.JsonVO;
import com.db.sys.entity.SysDept;
import com.db.sys.service.SysDeptService;

@Controller
@RequestMapping("/dept/")
public class SysDeptController {
	
	@Autowired
	private SysDeptService sysDeptService;
	
	@RequestMapping("doDeptListUI")
	public String doDeptListUI(){
		return "sys/dept_list";
	}
	@RequestMapping("doDeptEditUI")
	public String doDeptEditUI(){
		return "sys/dept_edit";
	}
	
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonVO doUpdateObject(SysDept entity){
		sysDeptService.updateObject(entity);
	    return new JsonVO("update ok");
	}
	
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonVO doSaveObject(SysDept entity){
		sysDeptService.saveObject(entity);
		return new JsonVO("save ok");
	}
	
	@RequestMapping("doFindZTreeNodes")
	@ResponseBody
	public JsonVO doFindZTreeNodes(){
		return new JsonVO(
		sysDeptService.findZTreeNodes());
	}
	
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonVO doDeleteObject(Integer id){
		sysDeptService.deleteObject(id);
		return new JsonVO("delete ok");
	}
	
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonVO doFindObjects(){
		return new JsonVO(sysDeptService.findObjects());
	}	
	
	
}
