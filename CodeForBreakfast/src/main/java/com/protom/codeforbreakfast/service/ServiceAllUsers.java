package com.protom.codeforbreakfast.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.protom.codeforbreakfast.dbconnections.DbConnectionMySql;
import com.protom.codeforbreakfast.model.dao.UserDAO;
import com.protom.codeforbreakfast.model.daoimpl.UserDAOimpl;
import com.protom.codeforbreakfast.model.entity.User;
 
public class ServiceAllUsers {
	
	private Connection connessione;
	private UserDAO userDAO;  
	
	
	public ServiceAllUsers( ) {
		super();
		this.connessione=DbConnectionMySql.avviaConnessione(); 
		this.userDAO = new UserDAOimpl(connessione); 
		
	}
	
	
	public void chiudiConnessione() {
		DbConnectionMySql.chiudiConnessione(connessione);
		
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
