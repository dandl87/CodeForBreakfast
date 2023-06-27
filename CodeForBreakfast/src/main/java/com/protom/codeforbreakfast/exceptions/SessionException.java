package com.protom.codeforbreakfast.exceptions;
 

import javax.servlet.ServletException;
 

@SuppressWarnings("serial")
public class SessionException extends ServletException{

	public SessionException(String message) {
		super(message); 
	}
	@Override
	public Throwable fillInStackTrace() {
	    return null;
	}  
}
