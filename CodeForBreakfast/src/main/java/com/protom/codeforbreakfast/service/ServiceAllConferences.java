package com.protom.codeforbreakfast.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.protom.codeforbreakfast.dbconnections.DbConnectionMySql;
import com.protom.codeforbreakfast.model.dao.ConferenceDAO;
import com.protom.codeforbreakfast.model.daoimpl.ConferenceDAOimpl;
import com.protom.codeforbreakfast.model.entity.Conference; 
 

public class ServiceAllConferences {
	
	private Connection connessione;
	private ConferenceDAO conferenceDAO;  
	
	
	public ServiceAllConferences( ) {
		super();
		this.connessione=DbConnectionMySql.avviaConnessione(); 
		this.conferenceDAO = new ConferenceDAOimpl(connessione); 
		
	}
	
	
	public void chiudiConnessione() {
		DbConnectionMySql.chiudiConnessione(connessione);
		
	}
	
	 
 
//	public boolean insertNewPost(Post post) {
//		boolean result = postDAO.createPost(post);
//		return result;
//		
//	}
	
	public ArrayList<Conference> caricaAllConferences(){
		 ArrayList<Conference> listOfAllConferences= conferenceDAO.readAllConferences(); 
		 return listOfAllConferences; 
		
	}
	
	public ArrayList<Conference> caricaAllConferencesOfPage(int n){
		 ArrayList<Conference> listOfAllConferences= conferenceDAO.readAllConferencesOfPage(n);
		 return listOfAllConferences; 
		
	}

}
