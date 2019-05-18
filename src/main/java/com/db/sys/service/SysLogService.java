package com.db.sys.service;

import com.db.sys.entity.SysLog;

public interface SysLogService extends PageService<SysLog>{
	/**
	 * 按条件查询日志信息
	 * @param username
	 * @param pageCurrent
	 * @return 当前页日志信息以及分页信息
	 * Map可读性不高且值不可控,虽然更简单
	 */
//	PageObject<SysLog> findPageObjects(String username, Integer pageCurrent);
	
	/**
	 * 基于id信息删除日志
	 * @param ids
	 * @return
	 */
	int deleteObjects(Integer...ids);
}
