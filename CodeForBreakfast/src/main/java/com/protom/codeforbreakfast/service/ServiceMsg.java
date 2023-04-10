package com.protom.codeforbreakfast.service;

import com.protom.codeforbreakfast.model.entity.Msg;

public class ServiceMsg {
	
	private Msg msg;

	public ServiceMsg() {
		super();
		this.msg = null;
	}

	 
	
	public Msg getMsg() {
		return msg;
	}
 

	public void setMsg(Msg msg) {
		this.msg=msg;
	}

	public void verifyStatus() {
		this.msg = new Msg(true, "state of User");
	}
	
	
}
