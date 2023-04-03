package com.protom.codeforbreakfast.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.protom.codeforbreakfast.dbconnections.DbConnectionMySql;
import com.protom.codeforbreakfast.model.dao.PostDAO;
import com.protom.codeforbreakfast.model.daoimpl.PostDAOimpl;
import com.protom.codeforbreakfast.model.entity.Post; 

public class ServiceAllPosts {
	
	private DbConnectionMySql dbConnection;
	private PostDAO postDAO;  
	
	
	public ServiceAllPosts( ) {
		super();
		this.dbConnection=DbConnectionMySql.getInstance(); 
		this.postDAO = new PostDAOimpl(dbConnection); 
		
	}
	
	
	public void chiudiConnessione() {
		dbConnection.chiudiConnessione();
		
	}
	
	 
 
//	public boolean insertNewPost(Post post) {
//		boolean result = postDAO.createPost(post);
//		return result;
//		
//	}
	
	public ArrayList<Post> caricaAllPosts(){
		 ArrayList<Post> listOfAllPosts= postDAO.readAllPosts();
		 return listOfAllPosts; 
		
	}
	
	public ArrayList<Post> caricaAllPostsOfPage(int n){
		 ArrayList<Post> listOfAllPosts= postDAO.readAllPostsOfPage(n);
		 return listOfAllPosts; 
		
	}

}
