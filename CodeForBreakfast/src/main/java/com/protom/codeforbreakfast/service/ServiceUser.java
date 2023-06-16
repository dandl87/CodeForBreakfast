package com.protom.codeforbreakfast.service;

import java.util.ArrayList; 

import com.protom.codeforbreakfast.dbconnections.DbConnectionMySql;
import com.protom.codeforbreakfast.exceptions.SessionException;
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
	
	private DbConnectionMySql connessioneDb;
	private UserDAO userDAO;
	private SottoscrizionePostDAO sottoscrizionePostDAO;
	private SottoscrizioneConferenceDAO sottoscrizioneConferenceDAO;
	private ServiceMsg serviceMsg;
	
	
	public ServiceUser( ) {
		super();
		this.connessioneDb=DbConnectionMySql.getInstance(); 
		this.userDAO = new UserDAOimpl(connessioneDb);
		this.sottoscrizionePostDAO = new SottoscrizionePostDAOimpl(connessioneDb);
		this.sottoscrizioneConferenceDAO = new SottoscrizioneConferenceDAOimpl(connessioneDb);
		this.serviceMsg= ServiceMsg.getInstance();
	}
	
  
 
	public boolean insertNewUser(User user) {
		connessioneDb.avviaConnessione();
		
		boolean result = userDAO.createUser(user);
		
		connessioneDb.chiudiConnessione();
		
		if(result) {
			serviceMsg.setValues(true,"Hi "+ user.getName(), "Desk");
			return true;
		}
		serviceMsg.setValues(false,"SignUp failed " , "Desk");
		
		return false;
		
	}
	

	
	public User cercaUser(String username, String password)    {
		
		connessioneDb.avviaConnessione();
		 
		User user = userDAO.readUser(username, password); 
		
		if(user==null) {  
				return null; 
			
		}
		
			
		
		// carica sottoscrizioni Post
		ArrayList<SottoscrizionePost> sottoscrizioniPost = sottoscrizionePostDAO.readSottoscrizionePostOfUser(username) ;
		 
		
		// carica sottoscrizioni Conference
		ArrayList<SottoscrizioneConference> sottoscrizioniConference = sottoscrizioneConferenceDAO.readSottoscrizioneConferenceOfUser(username) ;
		 
		connessioneDb.chiudiConnessione();
				
		user.setSottoscrizioniPost(sottoscrizioniPost);
		user.setSottoscrizioniConference(sottoscrizioniConference);  
		serviceMsg.setValues(true, "Hi " +user.getName(), "Desk");
		return user ;
		
	}
	
 
	
	
	
	 
	
	 
	
 
 
	
	}
	
 
