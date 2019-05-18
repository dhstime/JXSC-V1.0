package com.jx.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.common.vo.PageObject;
import com.db.sys.entity.SysUser;
import com.jx.VO.JsonResult;
import com.jx.entity.JxOrder;
import com.jx.service.JxOrderService;

@Controller
@RequestMapping("/order/")
public class JxOrderController {
	@Autowired
	private JxOrderService jxOrderService;

	@RequestMapping("doOrderListUI")
	public String doUserListUI(){
		return "sys/order_list";
	}

	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String userName,Integer pageCurrent) {
		PageObject<JxOrder> pageObject = jxOrderService.findPageObjects(userName,pageCurrent);
		//System.out.println(userName);
		return new JsonResult(pageObject);
	}

	@RequestMapping("doDeleteObjects")
	@ResponseBody
	public JsonResult doDeleteObjects(Integer... ids){
		jxOrderService.deleteObjects(ids);
		return new JsonResult("delete ok");
	}

	@RequestMapping("doOrderEditUI")
	public String doOrderEditUI(){
		return "sys/order_edit";
	}

	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(JxOrder entity){
		jxOrderService.saveObject(entity);
		return new JsonResult("save ok");
	}

	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(JxOrder entity){
		jxOrderService.updateObject(entity);
		return new JsonResult("update ok");
	}

	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
		JxOrder type=jxOrderService.findObjectById(id);
		return new JsonResult(type);
	}
	
	@RequestMapping("dogenerateOrder")
	@ResponseBody
	public JsonResult dogenerateOrder() {
		SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
		int rows = jxOrderService.generateOrder(user.getId(),user.getUsername());
		return new JsonResult("generate ok");
	}
}
