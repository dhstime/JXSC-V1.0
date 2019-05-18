package com.jx.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jx.entity.JxOrder;

public interface JxOrderDao {
	/**
	 * 
	 * @param startIndex 	当前网页的起始为止
	 * @param pageSize		当前页面的页面大小
	 * @return
	 */
	List<JxOrder> findPageObjects(
			@Param("userName")String userName,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	int getRowCount(@Param("userName")String userName);
	
	int deleteObjects(@Param("ids")Integer...ids);
	
	int insertObject(JxOrder entity);
	
	JxOrder findObjectById(Integer id);
	
	int updateObject(JxOrder entity);
}
