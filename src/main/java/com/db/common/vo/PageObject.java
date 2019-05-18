package com.db.common.vo;

import java.io.Serializable;
import java.util.List;

public final class PageObject<T> implements Serializable{//类泛型
	/**
	 * 值对象VO (value Object)
	 * 
	 */
	private static final long serialVersionUID = -7368493786259905794L;
	private List<T> records;
	private Integer rowCount=0;
	private Integer pageCount=0;
	private Integer pageCurrent=1;
	private Integer pageSize=3;
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	public Integer getRowCount() {
		return rowCount;
	}
	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(Integer pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
