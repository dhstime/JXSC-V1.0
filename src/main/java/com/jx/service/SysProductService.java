package com.jx.service;


import java.util.Map;

import com.db.common.vo.PageObject;
import com.jx.entity.SysProduct;


public interface SysProductService {
	/*
	 * 通过此方法实现分页查询
	 */
	PageObject<SysProduct> findPageObjects(String brand,
			Integer pageCurrent);
	/**
	 * 新增商品信息
	 */
	int insertObject(SysProduct entity);
	/**
	 * 修改商品时显示详细信息
	 * @param userId
	 * @return
	 */
	Map<String, Object> findObjectById(Integer userId);
	/**
	 * 修改商品信息
	 */
	int updateObject(SysProduct entity);
	 /**
	  * 删除商品信息
	  * @param ids
	  * @return
	  */
	 public int deleteObject(Integer id);
}
