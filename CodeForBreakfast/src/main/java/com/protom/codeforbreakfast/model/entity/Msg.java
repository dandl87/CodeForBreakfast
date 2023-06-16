package com.protom.codeforbreakfast.model.entity;
 

public class Msg {
	private boolean status;
	private String message;
	private String fromSection;
	
	public Msg( ) {
		super();
		this.status = true;
		this.message = "";
		this.fromSection="";
		
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	 

 
	public String getFromSection() {
		return fromSection;
	}

	public void setFromSection(String fromSection) {
		this.fromSection = fromSection;
	}

	@Override
	public String toString() {
		return "Msg [status=" + status + ", message=" + message + ", is from desk= "+this.fromSection+"]";
	}
	
 
	 
		
	}
	
 
