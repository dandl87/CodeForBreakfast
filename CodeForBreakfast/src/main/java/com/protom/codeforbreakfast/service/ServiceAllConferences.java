package com.protom.codeforbreakfast.service;
 
import java.util.ArrayList;

import com.protom.codeforbreakfast.dbconnections.DbConnectionMySql;
import com.protom.codeforbreakfast.model.dao.ConferenceDAO;
import com.protom.codeforbreakfast.model.daoimpl.ConferenceDAOimpl;
import com.protom.codeforbreakfast.model.entity.Conference; 
 

public class ServiceAllConferences {
	
	private DbConnectionMySql dbConnection;
	private ConferenceDAO conferenceDAO;  
	
	
	public ServiceAllConferences( ) {
		super();
		this.dbConnection=DbConnectionMySql.getInstance(); 
		this.conferenceDAO = new ConferenceDAOimpl(dbConnection); 
		
	}
	
	
	public void chiudiConnessione() {
		dbConnection.chiudiConnessione();
		
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
		 dbConnection.avviaConnessione();
		 ArrayList<Conference> listOfAllConferences= conferenceDAO.readAllConferencesOfPage(n);
		 dbConnection.chiudiConnessione();
		 return listOfAllConferences; 
		
	}

}
