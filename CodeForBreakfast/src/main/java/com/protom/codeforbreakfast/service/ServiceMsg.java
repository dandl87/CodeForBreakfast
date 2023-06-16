package com.protom.codeforbreakfast.service;

import com.protom.codeforbreakfast.model.entity.Msg; 

public class ServiceMsg {
	
	private Msg msg;

	private  ServiceMsg() {
		this.msg = new Msg();
	}
 
	public Msg getMsg() {
		return msg;
	}
  

	public void verifyValues() { 
		this.msg.setStatus(true);
		this.msg.setMessage("state of user");
		this.msg.setFromSection("");
	}
	
	public void setValues(boolean status, String message, String section) { 
		this.msg.setStatus(status);
		this.msg.setMessage(message);
		this.msg.setFromSection(section);
	}
	
	public static ServiceMsg getInstance() {
		return BuilderInstance.instance;
		 
	}
	
	private static class BuilderInstance{
		private static ServiceMsg instance = new ServiceMsg();
	}
}
