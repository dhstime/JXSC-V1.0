package com.jx.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.common.vo.PageObject;
import com.jx.VO.JsonResult;
import com.jx.entity.SysProduct;
import com.jx.service.SysProductService;

@Controller
@RequestMapping("/product/")
public class SysProductController {

	@Autowired
	private SysProductService sysProductService;
	/**
	 * 商品页面
	 */
	@RequestMapping("doProductListUI")
	public String doProductListUI(){
		return "sys/product_list";
	}

	@RequestMapping("doFindProductObjects")
	@ResponseBody
	public JsonResult doFindProductObjects(String brand,Integer pageCurrent){
		PageObject<SysProduct> productObject=
				sysProductService.findPageObjects(brand,pageCurrent);
		return new JsonResult(productObject);
	}
	/**
	 * 商品菜单编辑
	 */

	@RequestMapping("doProductEditUI")
	public String doUserEditUI(){
		return "sys/product_edit";
	}
	/**
	 * 添加商品
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(
			SysProduct entity){
		sysProductService.insertObject(entity);
		return new JsonResult("save ok");
	}
	/**
	 * 修改商品信息之前需要先查找信息
	 */
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		Map<String,Object> map = sysProductService.findObjectById(id);
		//System.out.println(map);
		return new JsonResult(map);
	}
	/**
	 * 商品信息修改之后需要保存
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(
			SysProduct entity){
		sysProductService.updateObject(entity);
		System.out.println(entity);
		return new JsonResult("update ok");
	}
	/**
	 * 删除商品信息
	 */
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObjects(Integer id){
		int rows = sysProductService.deleteObject(id);
		return new JsonResult("delete ok");
	}
	/**
	 * 详情信息
	 */
	@RequestMapping("doDetailObject")
	public String doDetailObject(){
		return "sys/product_detail";
	}

}
