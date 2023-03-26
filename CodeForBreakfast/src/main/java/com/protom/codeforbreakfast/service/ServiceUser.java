package com.protom.codeforbreakfast.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.protom.codeforbreakfast.dbconnections.DbConnectionMySql;
import com.protom.codeforbreakfast.model.dao.SottoscrizionePostDAO;
import com.protom.codeforbreakfast.model.dao.UserDAO;
import com.protom.codeforbreakfast.model.daoimpl.SottoscrizionePostDAOimpl;
import com.protom.codeforbreakfast.model.daoimpl.UserDAOimpl;
import com.protom.codeforbreakfast.model.entity.SottoscrizionePost;
import com.protom.codeforbreakfast.model.entity.User;
 

public class ServiceUser {
	
	private Connection connessione;
	private UserDAO userDAO;
	private SottoscrizionePostDAO sottoscrizionePostDAO;
	
	
	public ServiceUser( ) {
		super();
		this.connessione=DbConnectionMySql.avviaConnessione(); 
		this.userDAO = new UserDAOimpl(connessione);
		this.sottoscrizionePostDAO = new SottoscrizionePostDAOimpl(connessione);
	}
	
	
	public void chiudiConnessione() {
		DbConnectionMySql.chiudiConnessione(connessione);
		
	}
	
	 
 
	public boolean insertNewUser(User user) {
		boolean result = userDAO.createUser(user);
		return result;
		
	}
	

	
	public User cercaUser(String username, String password) {
		 
		User user = userDAO.readUser(username, password); 
		
		if(user==null)
			return null;
		
		// carica sottoscrizioni Post
		ArrayList<SottoscrizionePost> sottoscrizioniPost = sottoscrizionePostDAO.readSottoscrizionePostOfUser(username, password) ;
		 
		user.setSottoscrizioniPost(sottoscrizioniPost);
		// carica sottoscrizioni Conference
		//ArrayList<SottoscrizioneConference> sottoscrizioniConference;
		
  
		return user ;
		
	}
	

}
