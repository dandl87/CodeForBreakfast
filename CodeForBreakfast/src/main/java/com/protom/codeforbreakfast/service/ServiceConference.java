package com.protom.codeforbreakfast.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.protom.codeforbreakfast.dbconnections.DbConnectionMySql;
import com.protom.codeforbreakfast.model.dao.ConferenceDAO;
import com.protom.codeforbreakfast.model.daoimpl.ConferenceDAOimpl;
import com.protom.codeforbreakfast.model.entity.Conference;
import com.protom.codeforbreakfast.model.entity.User; 

public class ServiceConference {
	
	private Connection connessione;
	private ConferenceDAO conferenceDAO; 
	
	
	public ServiceConference( ) {
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
	
	public Conference[] caricaConferenceForUser(User user){
		
		 Conference[] listOfConference= new Conference[6];
		 for(int i=0; i<user.getSottoscrizioniConference().length; i++) {
			 int idConference=user.getSottoscrizioniConference()[i].getConferenceId(); 
			 Conference temporaryConference = conferenceDAO.readConference(idConference);
			 listOfConference[i]=temporaryConference;
		 }
		
		 return listOfConference; 
		
	}

}
