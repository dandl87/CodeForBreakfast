package com.protom.codeforbreakfast.service;
 

import java.util.ArrayList;

import com.protom.codeforbreakfast.dbconnections.DbConnectionMySql;
import com.protom.codeforbreakfast.model.dao.ConferenceDAO;
import com.protom.codeforbreakfast.model.dao.SottoscrizioneConferenceDAO;
import com.protom.codeforbreakfast.model.daoimpl.ConferenceDAOimpl;
import com.protom.codeforbreakfast.model.daoimpl.SottoscrizioneConferenceDAOimpl;
import com.protom.codeforbreakfast.model.entity.Conference;
import com.protom.codeforbreakfast.model.entity.SottoscrizioneConference; 
import com.protom.codeforbreakfast.model.entity.User; 

public class ServiceConference {
	
	private DbConnectionMySql dbConnection;
	private ConferenceDAO conferenceDAO;
	private SottoscrizioneConferenceDAO sottoscrizioneConferenceDAO; 
	
	
	public ServiceConference( ) {
		super();
		this.dbConnection=DbConnectionMySql.getInstance(); 
		this.conferenceDAO = new ConferenceDAOimpl(dbConnection);
		this.sottoscrizioneConferenceDAO= new SottoscrizioneConferenceDAOimpl(dbConnection);
	}
	
	
	public void avviaConnessione() {
		dbConnection.avviaConnessione();
		
	}
	
	public void chiudiConnessione() {
		dbConnection.chiudiConnessione();
		
	}
	
	public Conference cercaConference(int conferenceId) {
		Conference conference = conferenceDAO.readConference(conferenceId);
		return conference;
		
	}
	
	public ArrayList<SottoscrizioneConference> leggiSottoscrizioniConference(User user){
		return sottoscrizioneConferenceDAO.readSottoscrizioneConferenceOfUser(user.getUsername());
		
	}
	 
 
//	public boolean insertNewPost(Post post) {
//		boolean result = postDAO.createPost(post);
//		return result;
//		
//	}
	
	

}
