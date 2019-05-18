package com.jx.entity;


import java.io.Serializable;
import java.util.Date;

public class SysInventory implements Serializable{
	private static final long serialVersionUID = -7242090050123973378L;
	/** 库存id*/
	private Integer id;
	/**商品id*/
	private Integer productId;
	/**库存数量*/
	private Integer currentCount;
	/**修改时间*/
	private Date modifiedTime;
	/**销量*/
	private Integer salesVolume;
	/**创建时间*/
	private Date createTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(Integer currentCount) {
		this.currentCount = currentCount;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public Integer getSalesVolume() {
		return salesVolume;
	}
	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "SysInventory [id=" + id + ", productId=" + productId  + ", currentCount="
				+ currentCount + ", modifiedTime=" + modifiedTime + ", salesVolume=" + salesVolume + ", createTime="
				+ createTime + "]";
	}
	
}
