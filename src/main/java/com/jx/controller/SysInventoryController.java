package com.jx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.common.exception.ServiceException;
import com.db.common.vo.PageObject;
import com.db.sys.service.SysUserService;
import com.jx.VO.JsonResult;
import com.jx.entity.SysInventory;
import com.jx.service.SysInventoryService;
@Controller
@RequestMapping("/inventory/")
public class SysInventoryController {
	@Autowired
	private SysInventoryService sysInventoryService;
	@Autowired
	private SysUserService sysUserService;
	
	/*
	 * @RequestMapping("dofindInventory")
	 * 
	 * @ResponseBody public JsonResult dofindInventory (Integer pageCurrent) {
	 * List<SysInventory> result = sysInventoryService.findInventory(pageCurrent);
	 * return new JsonResult(result); }
	 */
	@RequestMapping("doInventoryListUI")
	public String doInventoryListUI() {
		return "sys/inventory_list";
	}
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(Integer pageCurrent) {
		PageObject<SysInventory> pageObjects = sysInventoryService.findPageObjects( pageCurrent);
		return new JsonResult(pageObjects);
	}
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id) {
		sysInventoryService.deleteObjectById(id);
		
		return new JsonResult("delete ok");
	}
	//添加页面呈现
	@RequestMapping("doInventoryEditUI")
	public String doInventoryEditUI() {
		
		return "sys/inventory_edit";
	}
	//保存数据
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(SysInventory entity) {
		try {
			sysInventoryService.SaveObject(entity);
		} catch (ServiceException e) {
			return new JsonResult(e);
		}
		return  new JsonResult("save ok");
	}
	@RequestMapping("doFindObjectById")
	@ResponseBody
	 public JsonResult doFindObjectById(Integer id){
		System.out.println("-------------"+id);
	    	SysInventory result = sysInventoryService.findInventoryById(id);
	    	System.out.println(result.toString());
	    	return new JsonResult(result);
	 }
	
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(SysInventory entity) {
		System.out.println(entity.toString());
		sysInventoryService.updateObject(entity);
		return new JsonResult("update ok");
	}
}










