package com.protom.codeforbreakfast.model.entity;
 

public class Msg {
	private boolean status;
	private String message;
	
	public Msg( ) {
		super();
		this.status = true;
		this.message = "";
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

	@Override
	public String toString() {
		return "Msg [status=" + status + ", message=" + message + "]";
	}
	
 
	 
		
	}
	
 
