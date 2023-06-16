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
	private ServiceMsg serviceMsg;
	
	
	public ServiceConference( ) {
		super();
		this.dbConnection=DbConnectionMySql.getInstance(); 
		this.conferenceDAO = new ConferenceDAOimpl(dbConnection);
		this.sottoscrizioneConferenceDAO= new SottoscrizioneConferenceDAOimpl(dbConnection);
		this.serviceMsg= ServiceMsg.getInstance();
	}
	
	
	public void avviaConnessione() {
		dbConnection.avviaConnessione();
		
	}
	
	public void chiudiConnessione() {
		dbConnection.chiudiConnessione();
		
	}
	
	public Conference cercaConference(int conferenceId) { 
		
		dbConnection.avviaConnessione();
		
		Conference conference = conferenceDAO.readConference(conferenceId); 
		
		dbConnection.chiudiConnessione();
		
		return conference;
		
	}
	
	public ArrayList<SottoscrizioneConference> leggiSottoscrizioniConference(User user){
		dbConnection.avviaConnessione();
		
		ArrayList<SottoscrizioneConference> sottoscrizioniConference;
		sottoscrizioniConference = sottoscrizioneConferenceDAO.readSottoscrizioneConferenceOfUser(user.getUsername());
		
		dbConnection.chiudiConnessione();
		return sottoscrizioniConference;
		
	}
	
	public void addConference(User user, int conferenceId, String section) { 
		
		dbConnection.avviaConnessione();
		
		System.out.println(user);
		 
		ArrayList<SottoscrizioneConference> sottoscrizioniConference = user.getSottoscrizioniConference(); 
		
		if(sottoscrizioniConference.size()==6)
			serviceMsg.setValues(false,"Conference Area is full!", section);
		
		// se il post è già presente l'add non avviene
		boolean isPresent = checkPresenceSC(sottoscrizioniConference, conferenceId);
		
		if(isPresent)
			serviceMsg.setValues(false,"Conference is in your Desk!", section);
			else {
		//creiamo un Conference, una sottoscrizioneConference
		Conference conference = conferenceDAO.readConference(conferenceId); 
		 
		SottoscrizioneConference sottoscrizioneConference = new SottoscrizioneConference(user.getUsername(),conference);
		
		boolean result = sottoscrizioneConferenceDAO.createSottoscrizioneC(sottoscrizioneConference);
		
			
		if(!result) 
		 serviceMsg.setValues(false, "sottoscrizione non riuscita!", section);
		else serviceMsg.setValues(true, "Conference - "+sottoscrizioneConference.getConference().getTitle()+" - inserted", section);
		 
			}
		
		dbConnection.chiudiConnessione();
	}
	
	//Rimuovo conference dalla Personal Area
	public void removeConference(User user, int conferenceId, String section) { 
		
		dbConnection.avviaConnessione();
		ArrayList<SottoscrizioneConference> sottoscrizioniConference = user.getSottoscrizioniConference();
		
		for(SottoscrizioneConference sC: sottoscrizioniConference) {
			if(sC.getConference().getId()==conferenceId) { 
				sottoscrizioneConferenceDAO.deleteSottoscrizioneC(sC.getId());
				serviceMsg.setValues(true,"Conference - "+sC.getConference().getTitle()+" - removed", section);
			}	
		}
		
		dbConnection.chiudiConnessione();

	}

	
	private boolean checkPresenceSC(ArrayList<SottoscrizioneConference> sottoscrizioniConference, int conferenceId) {
		for(SottoscrizioneConference sC: sottoscrizioniConference) {
			if(sC.getConference().getId()==conferenceId)
				return true;
		}
		return false; 
	}
	 
 
//	public boolean insertNewPost(Post post) {
//		boolean result = postDAO.createPost(post);
//		return result;
//		
//	}
	
	

}
