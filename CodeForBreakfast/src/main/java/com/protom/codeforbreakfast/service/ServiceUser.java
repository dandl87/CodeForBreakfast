package com.protom.codeforbreakfast.service;

import java.util.ArrayList;
import java.util.TreeMap;

import com.protom.codeforbreakfast.dbconnections.DbConnectionMySql;
import com.protom.codeforbreakfast.model.dao.SottoscrizioneConferenceDAO;
import com.protom.codeforbreakfast.model.dao.SottoscrizionePostDAO;
import com.protom.codeforbreakfast.model.dao.UserDAO;
import com.protom.codeforbreakfast.model.daoimpl.SottoscrizioneConferenceDAOimpl;
import com.protom.codeforbreakfast.model.daoimpl.SottoscrizionePostDAOimpl;
import com.protom.codeforbreakfast.model.daoimpl.UserDAOimpl;
import com.protom.codeforbreakfast.model.entity.Conference; 
import com.protom.codeforbreakfast.model.entity.Post;
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
	
	public void avviaConnessione() {
		connessioneDb.avviaConnessione();
		
	}
	
	public void chiudiConnessione() {
		connessioneDb.chiudiConnessione();
		
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
		ArrayList<SottoscrizionePost> sottoscrizioniPost = sottoscrizionePostDAO.readSottoscrizionePostOfUser(username) ;
		 
		
		// carica sottoscrizioni Conference
		ArrayList<SottoscrizioneConference> sottoscrizioniConference = sottoscrizioneConferenceDAO.readSottoscrizioneConferenceOfUser(username) ;
		 
	 
				
		user.setSottoscrizioniPost(sottoscrizioniPost);
		user.setSottoscrizioniConference(sottoscrizioniConference);  
		serviceMsg.setValues(true, "Hi " +user.getName());
		return user ;
		
	}
	

	
	//Rimuovo post dalla Personal Area
	public void removePost(User user, int postId) { 
		
		ArrayList<SottoscrizionePost> sottoscrizioniPost = user.getSottoscrizioniPost();
		
		for(SottoscrizionePost sP: sottoscrizioniPost) {
			if(sP.getPost().getId()==postId) { 
				sottoscrizionePostDAO.deleteSottoscrizionePost(sP.getId());
				serviceMsg.setValues(true,sP.getPost().getTitle()+" - removed");
			}	
		}
	
	}
	
	
	
	//Rimuovo conference dalla Personal Area
		public void removeConference(User user, int conferenceId) { 
			
			ArrayList<SottoscrizioneConference> sottoscrizioniConference = user.getSottoscrizioniConference();
			
			for(SottoscrizioneConference sC: sottoscrizioniConference) {
				if(sC.getConference().getId()==conferenceId) { 
					sottoscrizioneConferenceDAO.deleteSottoscrizioneC(sC.getId());
					serviceMsg.setValues(true,"Conference - "+sC.getConference().getTitle()+" - removed");
				}	
			}
		}
	
	
	
	public void addPost(User user, int postId) { 
		ServicePost servicePost = new ServicePost();
		ArrayList<SottoscrizionePost> sottoscrizioniPost = user.getSottoscrizioniPost(); 
		
		if(sottoscrizioniPost.size()==6)
			serviceMsg.setValues(false,"Articles Area is full!");
		
		// se il post è già presente l'add non avviene
		boolean isPresent = checkPresenceSP(sottoscrizioniPost, postId);
		
		if(isPresent)
			serviceMsg.setValues(false,"Articles is in your Desk!");
		
		//creiamo un Post, una sottoscrizionePost 
		Post post = servicePost.cercaPost(postId); 
		int position=findPosition(sottoscrizioniPost);
		SottoscrizionePost sottoscrizionePost = new SottoscrizionePost(user.getUsername(),post, position);
		
		boolean result = sottoscrizionePostDAO.createSottoscrizioneP(sottoscrizionePost);
		if(!result) 
		 serviceMsg.setValues(false, "sottoscrizione non riuscita!");
		else serviceMsg.setValues(true, sottoscrizionePost.getPost().getTitle()+" - inserted in position "+position );
		 
		}
	
	public void addConference(User user, int conferenceId) { 
		ServiceConference serviceConference = new ServiceConference();
		ArrayList<SottoscrizioneConference> sottoscrizioniConference = user.getSottoscrizioniConference(); 
		
		if(sottoscrizioniConference.size()==6)
			serviceMsg.setValues(false,"Conference Area is full!");
		
		// se il post è già presente l'add non avviene
		boolean isPresent = checkPresenceSC(sottoscrizioniConference, conferenceId);
		
		if(isPresent)
			serviceMsg.setValues(false,"Conference is in your Desk!");
		
		//creiamo un Post, una sottoscrizionePost 
		Conference conference = serviceConference.cercaConference(conferenceId); 
		 
		SottoscrizioneConference sottoscrizioneConference = new SottoscrizioneConference(user.getUsername(),conference);
		
		boolean result = sottoscrizioneConferenceDAO.createSottoscrizioneC(sottoscrizioneConference);
		if(!result) 
		 serviceMsg.setValues(false, "sottoscrizione non riuscita!");
		else serviceMsg.setValues(true, "Conference - "+sottoscrizioneConference.getConference().getTitle()+" - inserted");
		 
		}
	
	
	
	
	private boolean checkPresenceSP(ArrayList<SottoscrizionePost> sottoscrizioniPost, int postId) {
		for(SottoscrizionePost sP: sottoscrizioniPost) {
			if(sP.getPost().getId()==postId)
				return true;
		}
		return false; 
	}
	
	private boolean checkPresenceSC(ArrayList<SottoscrizioneConference> sottoscrizioniConference, int conferenceId) {
		for(SottoscrizioneConference sC: sottoscrizioniConference) {
			if(sC.getConference().getId()==conferenceId)
				return true;
		}
		return false; 
	}
	
	
	private int findPosition(ArrayList<SottoscrizionePost> sottoscrizioniPost) {
		TreeMap<Integer, SottoscrizionePost> sottoscrizioniOrdinate = new TreeMap<>();
		for(SottoscrizionePost sP: sottoscrizioniPost)
			sottoscrizioniOrdinate.put(sP.getPosition(), sP);
		int i =1;
		while(sottoscrizioniOrdinate.get(i)!=null)
			i++;
		return i; 
	}
	
	
	

	
 
 
	
	}
	
 
