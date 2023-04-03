package com.protom.codeforbreakfast.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.protom.codeforbreakfast.dbconnections.DbConnectionMySql;
import com.protom.codeforbreakfast.model.dao.PostDAO; 
import com.protom.codeforbreakfast.model.daoimpl.PostDAOimpl; 
import com.protom.codeforbreakfast.model.entity.Post;
import com.protom.codeforbreakfast.model.entity.User; 

public class ServicePost {
	
	private DbConnectionMySql connessioneDb;
	private PostDAO postDAO; 
	
	
	public ServicePost( ) {
		super();
		this.connessioneDb=DbConnectionMySql.getInstance();
		this.postDAO = new PostDAOimpl(connessioneDb); 
	}
	
	
	public void chiudiConnessione() {
		connessioneDb.chiudiConnessione();
		
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
