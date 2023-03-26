package com.protom.codeforbreakfast.demo;

import com.protom.codeforbreakfast.model.entity.User;
import com.protom.codeforbreakfast.service.ServiceUser;

public class Test1 {

	public static void main(String[] args) {
		
		User user;
		
		ServiceUser service = new ServiceUser();
		
		user = service.cercaUser("Dan87", "1234"); 
		
		System.out.println(user);
		System.out.println(user.getName());
		
		
	}
}
