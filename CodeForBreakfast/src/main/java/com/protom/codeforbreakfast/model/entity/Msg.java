package com.protom.codeforbreakfast.model.entity;

public class Msg {
	private boolean result;
	private String message;
	
	public Msg(boolean result, String message) {
		super();
		this.result = result;
		this.message = message;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Msg [result=" + result + ", message=" + message + "]";
	}
	
	

}
