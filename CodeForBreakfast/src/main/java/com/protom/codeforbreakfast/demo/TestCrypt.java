package com.protom.codeforbreakfast.demo;

import org.mindrot.jbcrypt.BCrypt;

public class TestCrypt {
	
	public static void main(String[] args) {
		
		String password = "1234";
		String candidate = "1234";
	
	// Hash a password for the first time
	String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

	// gensalt's log_rounds parameter determines the complexity
	// the work factor is 2**log_rounds, and the default is 10
	//String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));

	// Check that an unencrypted password matches one that has
	// previously been hashed
	if (BCrypt.checkpw(candidate, hashed))
		System.out.println("It matches");
	else
		System.out.println("It does not match");

	}

}