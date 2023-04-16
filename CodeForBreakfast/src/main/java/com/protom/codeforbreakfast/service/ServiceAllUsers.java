package com.protom.codeforbreakfast.service;
 
import java.util.ArrayList;

import com.protom.codeforbreakfast.dbconnections.DbConnectionMySql;
import com.protom.codeforbreakfast.model.dao.UserDAO;
import com.protom.codeforbreakfast.model.daoimpl.UserDAOimpl;
import com.protom.codeforbreakfast.model.entity.User;
 
public class ServiceAllUsers {
	
	private DbConnectionMySql dbConnection;
	private UserDAO userDAO;  
	
	
	public ServiceAllUsers( ) {
		super();
		this.dbConnection=DbConnectionMySql.getInstance(); 
		this.userDAO = new UserDAOimpl(dbConnection); 
		
	}
	
	
	public void chiudiConnessione() {
		dbConnection.chiudiConnessione();
		
	}
	
	 
 
//	public boolean insertNewPost(Post post) {
//		boolean result = postDAO.createPost(post);
//		return result;
//		
//	}
	
	public ArrayList<User>  caricaAllUsers(){
		 ArrayList<User> listOfAllUsers= userDAO.readAllUsers();
		 return listOfAllUsers; 
		
	}

}
