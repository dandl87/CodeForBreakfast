package com.protom.codeforbreakfast.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.TreeMap;

import com.protom.codeforbreakfast.dbconnections.DbConnectionMySql;
import com.protom.codeforbreakfast.model.dao.SottoscrizioneConferenceDAO;
import com.protom.codeforbreakfast.model.dao.SottoscrizionePostDAO;
import com.protom.codeforbreakfast.model.dao.UserDAO;
import com.protom.codeforbreakfast.model.daoimpl.SottoscrizioneConferenceDAOimpl;
import com.protom.codeforbreakfast.model.daoimpl.SottoscrizionePostDAOimpl;
import com.protom.codeforbreakfast.model.daoimpl.UserDAOimpl; 
import com.protom.codeforbreakfast.model.entity.Msg;
import com.protom.codeforbreakfast.model.entity.Post;
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
		ArrayList<SottoscrizionePost> sottoscrizioniPost = sottoscrizionePostDAO.readSottoscrizionePostOfUser(username, password) ;
		
		//Debug
		for(SottoscrizionePost sP: sottoscrizioniPost)
			System.out.println("Debug:"+sP);
		
		
		// carica sottoscrizioni Conference
		ArrayList<SottoscrizioneConference> sottoscrizioniConference = sottoscrizioneConferenceDAO.readSottoscrizioneConferenceOfUser(username, password) ;
		 
		//Debug
		for(SottoscrizioneConference sC: sottoscrizioniConference)
			System.out.println("Debug:"+sC);
				
		user.setSottoscrizioniPost(sottoscrizioniPost);
		user.setSottoscrizioniConference(sottoscrizioniConference); 
		System.out.println("DEBUG: user:"+user);
		return user ;
		
	}
	
	//Rimuovo post dalla Personal Area
	public Msg removePost(User user, int postId) { 
		
		ArrayList<SottoscrizionePost> sottoscrizioniPost = user.getSottoscrizioniPost();
		
		for(SottoscrizionePost sP: sottoscrizioniPost) {
			if(sP.getPost().getId()==postId) { 
				sottoscrizionePostDAO.deleteSottoscrizionePost(sP.getId());
				return new Msg(true,"Article removed");
			}	
		}
	
	return new Msg(false, "Article remotion failed!");
	}
	
	
	
	public Msg addPost(User user, int postId) {
		Msg messageResult;
		ServicePost servicePost = new ServicePost();
		ArrayList<SottoscrizionePost> sottoscrizioniPost = user.getSottoscrizioniPost(); 
		
		if(sottoscrizioniPost.size()==6)
			return new Msg(false,"Articles Area is full!");
		
		//creiamo un Post, una sottoscrizionePost 
		Post post = servicePost.cercaPost(postId); 
		int position=findPosition(sottoscrizioniPost);
		SottoscrizionePost sottoscrizionePost = new SottoscrizionePost(user.getUsername(),user.getPassword(),post, position);
		messageResult= new Msg(true, "Articles inserted in position "+position );
		boolean result = sottoscrizionePostDAO.createSottoscrizioneP(sottoscrizionePost);
		if(result)
			return messageResult;
		else return new Msg(false, "sottoscrizione non riuscita!");
		 
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
	
 
