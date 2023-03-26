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
	
	public ArrayList<Post> caricaPostForUser(User user){
		
		 ArrayList<Post> listOfPost= new ArrayList<>();
		 
		 for(int i=0; i<user.getSottoscrizioniPost().size(); i++) {
			 int idPost=user.getSottoscrizioniPost().get(i).getPostId(); 
			 Post temporaryPost = postDAO.readPost(idPost);
			 listOfPost.add(temporaryPost);
		 }
		 return listOfPost; 
		
	}

}
