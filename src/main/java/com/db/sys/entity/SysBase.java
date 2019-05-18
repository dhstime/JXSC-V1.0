package com.db.sys.entity;

import java.io.Serializable;
import java.util.Date;

public class SysBase implements Serializable{
	private static final long serialVersionUID = -534196947312483554L;
	/**创建用户*/
	protected String createdUser;
	/**修改用户*/
	protected String modifiedUser;
	protected Date createdTime;
	protected Date modifiedTime;
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	
}
