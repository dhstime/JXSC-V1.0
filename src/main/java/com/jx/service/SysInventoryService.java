package com.jx.service;


import com.db.common.vo.PageObject;
import com.jx.entity.SysInventory;

public interface SysInventoryService {
	
//	List<SysInventory> findInventory();
	
	PageObject<SysInventory> findPageObjects(
			Integer pageCurrent);
	
	int deleteObjectById(Integer id);
	
	int SaveObject(SysInventory entity);
	
	SysInventory findInventoryById(Integer id);
	
	int updateObject(SysInventory entity);
	
	
}
