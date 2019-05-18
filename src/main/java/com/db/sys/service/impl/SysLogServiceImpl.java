package com.db.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.db.common.annotation.RequestLog;
import com.db.common.annotation.RequiredCache;
import com.db.common.exception.ServiceException;
import com.db.common.util.PageUtil;
import com.db.common.vo.PageObject;
import com.db.sys.dao.SysLogDao;
import com.db.sys.entity.SysLog;
import com.db.sys.service.SysLogService;
@Service
public class SysLogServiceImpl implements SysLogService {
	/**依赖于dao*/
	@Autowired
//	@Qualifier("SysLogDaoImpl") //自己写Dao
	private SysLogDao sysLogDao;
	@RequiredCache
	@Override
	public PageObject<SysLog> findPageObjects(String username, Integer pageCurrent) {
		//1.判定参数合法性
		if(pageCurrent==null||pageCurrent<1) throw new IllegalArgumentException("当前页不存在");
		//2.查询日志的中记录数,并进行判定
		int rowCount = sysLogDao.getRowCount(username);
		if(rowCount==0) throw new ServiceException("系统没有查到对应记录");
		//3.查询当前页记录
		int pageSize = 3;
		int startIndex = (pageCurrent-1)*pageSize;
		List<SysLog> records = sysLogDao.findPageObjects(username, startIndex, pageSize);
		//4.对查询结果进行封装,并返回
		return PageUtil.newInstance(pageCurrent, rowCount, pageSize, records);
	}
	@RequestLog("删除日志")
	@Override
	public int deleteObjects(Integer... ids) {
		//1.参数校验
		if(ids==null||ids.length==0) throw new IllegalArgumentException("参数无效");
		//2.执行删除操作
		int rows = sysLogDao.deleteObjects(ids);
		if(rows==0)throw new ServiceException("记录可能已经不存在");
		return rows;
	}

}
