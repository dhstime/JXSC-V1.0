package com.db.common.util;

import java.util.List;

import com.db.common.vo.PageObject;

public class PageUtil {
	public static <T>PageObject<T> newInstance(Integer pageCurrent, int rowCount, int pageSize, List<T> records) {
		//4.对分页信息以及当前页记录进行封装
		//4.1)构建PageObject对象
		PageObject<T> pageObject=new PageObject<>();
		//4.2)封装数据
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		int pageCount = (rowCount-1)/pageSize+1;
		pageObject.setPageCount(pageCount);
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		//5.返回封装结果。
		return pageObject;
	}

}
