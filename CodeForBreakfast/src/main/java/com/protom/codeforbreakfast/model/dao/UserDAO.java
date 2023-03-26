package com.protom.codeforbreakfast.model.dao;

import com.protom.codeforbreakfast.model.entity.User; 

public interface UserDAO {
	
	boolean createUser(User user); 
	User readUser(String username, String password);
	boolean updateUser(User User); 
	boolean deleteUser(String username, String password); 
	

}
