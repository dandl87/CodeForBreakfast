package com.protom.codeforbreakfast.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.protom.codeforbreakfast.dbconnections.DbConnectionMySql;
import com.protom.codeforbreakfast.model.dao.SottoscrizioneConferenceDAO;
import com.protom.codeforbreakfast.model.dao.SottoscrizionePostDAO;
import com.protom.codeforbreakfast.model.dao.UserDAO;
import com.protom.codeforbreakfast.model.daoimpl.SottoscrizioneConferenceDAOimpl;
import com.protom.codeforbreakfast.model.daoimpl.SottoscrizionePostDAOimpl;
import com.protom.codeforbreakfast.model.daoimpl.UserDAOimpl;
import com.protom.codeforbreakfast.model.entity.SottoscrizioneConference;
import com.protom.codeforbreakfast.model.entity.SottoscrizionePost;
import com.protom.codeforbreakfast.model.entity.User;
 

public class ServiceUser {
	
	private Connection connessione;
	private UserDAO userDAO;
	private SottoscrizionePostDAO sottoscrizionePostDAO;
	private SottoscrizioneConferenceDAO sottoscrizioneConferenceDAO;
	
	
	public ServiceUser( ) {
		super();
		this.connessione=DbConnectionMySql.avviaConnessione(); 
		this.userDAO = new UserDAOimpl(connessione);
		this.sottoscrizionePostDAO = new SottoscrizionePostDAOimpl(connessione);
		this.sottoscrizioneConferenceDAO = new SottoscrizioneConferenceDAOimpl(connessione);
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
		SottoscrizionePost[] sottoscrizioniPost = sottoscrizionePostDAO.readSottoscrizionePostOfUser(username, password) ;
		
		//Debug
		for(SottoscrizionePost sP: sottoscrizioniPost)
			System.out.println("Debug:"+sP);
		
		
		// carica sottoscrizioni Conference
		SottoscrizioneConference[] sottoscrizioniConference = sottoscrizioneConferenceDAO.readSottoscrizioneConferenceOfUser(username, password) ;
		 
		//Debug
		for(SottoscrizioneConference sC: sottoscrizioniConference)
			System.out.println("Debug:"+sC);
				
		user.setSottoscrizioniPost(sottoscrizioniPost);
		user.setSottoscrizioniConference(sottoscrizioniConference); 
		System.out.println("DEBUG: user:"+user);
		return user ;
		
	}
	

}
