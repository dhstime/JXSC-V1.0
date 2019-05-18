package com.jx.entity;

import java.io.Serializable;

public class SysProduct implements Serializable{
	private static final long serialVersionUID = -8104844113030262340L;
	private Integer id;
	private String brand;
	private String tradeName;
	private String message;
	private Double price;
	private String color;
	private Integer status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String gettradeName() {
		return tradeName;
	}
	public void settradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
