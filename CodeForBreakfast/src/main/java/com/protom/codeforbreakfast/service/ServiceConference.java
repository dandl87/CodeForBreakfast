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
	
	public ArrayList<Conference> caricaConferenceForUser(User user){
		
		 ArrayList<Conference> listOfConference= new ArrayList<>(); 
		 for(int i=0; i<user.getSottoscrizioniConference().size(); i++) {
			 int idConference=user.getSottoscrizioniConference().get(i).getConferenceId(); 
			 Conference temporaryConference = conferenceDAO.readConference(idConference);
			 listOfConference.add(temporaryConference);
		 }
		
		 return listOfConference; 
		
	}

}
