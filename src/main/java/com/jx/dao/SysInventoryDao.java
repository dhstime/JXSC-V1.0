package com.jx.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jx.entity.SysInventory;

public interface SysInventoryDao {
	
	List<SysInventory> findInventory();
	
	List<SysInventory>findPageObjects(
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	int getRowCount();
	
	SysInventory findInventoryproductById(Integer id);
	
	int deleteObjectById(Integer id);
	
	int insertObject(SysInventory entity);
	
	SysInventory findInventoryById(Integer id);
	
	int updateObject(SysInventory entity);
	
}
