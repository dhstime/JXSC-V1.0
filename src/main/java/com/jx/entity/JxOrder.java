package com.jx.entity;

import java.io.Serializable;
import java.util.Date;

public class JxOrder implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -1849123704843169167L;

	private Integer id;					//id
	private String userName;			//用户名
	private String type;				//商品类型
	private String tradeName;			//商品名
	private Date addTime;				//下单时间
	private Integer purchaseQuantity; 	//购买数量
	private Date payTime;				//支付时间
	private String payType;				//支付类型
	private Double price;				//商品价格
	private String distributionLoading; //配送(快递方式)
	private String orderStatus;			//配送状态
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Integer getPurchaseQuantity() {
		return purchaseQuantity;
	}
	public void setPurchaseQuantity(Integer purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDistributionLoading() {
		return distributionLoading;
	}
	public void setDistributionLoading(String distributionLoading) {
		this.distributionLoading = distributionLoading;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
