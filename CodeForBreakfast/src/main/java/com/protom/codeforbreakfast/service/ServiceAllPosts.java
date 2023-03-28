package com.protom.codeforbreakfast.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.protom.codeforbreakfast.dbconnections.DbConnectionMySql;
import com.protom.codeforbreakfast.model.dao.PostDAO;
import com.protom.codeforbreakfast.model.daoimpl.PostDAOimpl;
import com.protom.codeforbreakfast.model.entity.Post; 

public class ServiceAllPosts {
	
	private Connection connessione;
	private PostDAO postDAO;  
	
	
	public ServiceAllPosts( ) {
		super();
		this.connessione=DbConnectionMySql.avviaConnessione(); 
		this.postDAO = new PostDAOimpl(connessione); 
		
	}
	
	
	public void chiudiConnessione() {
		DbConnectionMySql.chiudiConnessione(connessione);
		
	}
	
	 
 
//	public boolean insertNewPost(Post post) {
//		boolean result = postDAO.createPost(post);
//		return result;
//		
//	}
	
	public ArrayList<Post>  caricaAllPosts(){
		 ArrayList<Post> listOfAllPosts= postDAO.readAllPosts();
		 return listOfAllPosts; 
		
	}

}
