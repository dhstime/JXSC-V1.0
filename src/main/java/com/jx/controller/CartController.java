package com.jx.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.common.vo.JsonVO;
import com.db.sys.entity.SysUser;
import com.jx.service.CartService;
@RequestMapping("/cart/")
@Controller
public class CartController {
	@Autowired
	CartService cartService;
	
	@RequestMapping("doCartListUI")
	public String doCartListUI() {
		return "sys/cart_list";
	}
	
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonVO doFindObjects(Integer userId) {
		SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
		JsonVO result = cartService.findObjects(user.getId());
		return result;
	}
	
	@RequestMapping("doDeleteObjects")
	@ResponseBody
	public JsonVO doDeleteObjects(@RequestParam("ids")Integer...ids) {
		System.out.println("ids:"+ids.length);
		int rows = cartService.doDeleteObjects(ids);
		return new JsonVO("delete ok!");
	}
	
	@RequestMapping("doEmptyAll")
	@ResponseBody
	public JsonVO doEmptyAll() {
		SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
		int rows= cartService.doEmptyAll(user.getId());
		return new JsonVO("empty ok!");
	}
	
	@RequestMapping("doAddLike")
	@ResponseBody
	public JsonVO doAddLike(
			Integer id,
			Integer islike){
		// 获取身份信息
		int rows = cartService.doAddLike(id, islike);
		return new JsonVO("save ok");
	}
	@RequestMapping("doAddLikes")
	@ResponseBody
	public JsonVO doAddLikes(
			Integer...ids){
		// 获取身份信息
		int rows = cartService.doAddLikes(ids);
		return new JsonVO("save ok");
	}
	
	@RequestMapping("doChangeChecked")
	@ResponseBody
	public JsonVO doChangeChecked(
			Integer id,
			Integer checked){
		// 获取身份信息
		int rows = cartService.doChangeChecked(id, checked);
		return new JsonVO("save ok");
	}
	
	@RequestMapping("doAddintoCart")
	@ResponseBody
	public JsonVO doAddintoCart(Integer id) {
		SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
		int rows = cartService.doAddintoCart(user.getId(), id);
		return new JsonVO("add ok");
	}
	
	@RequestMapping("doupdateQuantity")
	@ResponseBody
	public JsonVO doupdateQuantity(Integer id,Integer quantity) {
		int rows = cartService.doupdateQuantity(id, quantity);
		return new JsonVO("add ok");
	}
	
}
