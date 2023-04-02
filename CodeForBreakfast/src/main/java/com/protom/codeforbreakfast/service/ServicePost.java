package com.protom.codeforbreakfast.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.protom.codeforbreakfast.dbconnections.DbConnectionMySql;
import com.protom.codeforbreakfast.model.dao.PostDAO; 
import com.protom.codeforbreakfast.model.daoimpl.PostDAOimpl; 
import com.protom.codeforbreakfast.model.entity.Post;
import com.protom.codeforbreakfast.model.entity.User; 

public class ServicePost {
	
	private Connection connessione;
	private PostDAO postDAO; 
	
	
	public ServicePost( ) {
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
	
	public Post cercaPost(int postId) {
		Post post = postDAO.readPost(postId);
		return post;
		
	}
	 
	
 

}
