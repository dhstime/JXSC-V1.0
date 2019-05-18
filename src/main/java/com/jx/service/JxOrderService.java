package com.jx.service;


import com.db.common.vo.PageObject;
import com.jx.entity.JxOrder;

public interface JxOrderService {
	 /**
     * 通过此方法实现分页查询操作
     * @param id 基于条件查询时的参数id
     * @param pageCurrent 当前的页码值
     * @return 当前页记录+分页信息
     */
	 PageObject<JxOrder> findPageObjects(
			 String userName,
			 Integer pageCurrent);
	 
	 int deleteObjects(Integer... ids);
	 
	 int saveObject(JxOrder entity);
	 
	 JxOrder findObjectById(Integer id);
		 
	 int updateObject(JxOrder entity);

	int generateOrder(Integer id,String name);
}
