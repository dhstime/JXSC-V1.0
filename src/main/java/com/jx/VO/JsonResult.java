package com.jx.VO;

import java.io.Serializable;

public final class JsonResult implements Serializable{
	private static final long serialVersionUID = -4138033536625725437L;
	/*状态码:6000以上*/
	private int state=1;//1:正确,0:error
	enum State{
		OK,ERROR
	}
	/*状态码对应的消息*/
	private String message="ok";
	/*页面上说需呈现的数据*/
	private Object data;
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public JsonResult() {
	}
	public JsonResult(String message) {
		super();
		this.message = message;
	}
	public JsonResult(Object data) {
		super();
		this.data = data;
	}
	public JsonResult(Throwable t) {
		super();
		this.state=0;
		this.message=t.getMessage();//异常的基本信息
	}
	
}
