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
import com.protom.codeforbreakfast.model.entity.Msg;
import com.protom.codeforbreakfast.model.entity.Post;
import com.protom.codeforbreakfast.model.entity.SottoscrizioneConference;
import com.protom.codeforbreakfast.model.entity.SottoscrizionePost;
import com.protom.codeforbreakfast.model.entity.User;
 

public class ServiceUser {
	
	private DbConnectionMySql connessioneDb;
	private UserDAO userDAO;
	private SottoscrizionePostDAO sottoscrizionePostDAO;
	private SottoscrizioneConferenceDAO sottoscrizioneConferenceDAO;
	
	
	public ServiceUser( ) {
		super();
		this.connessioneDb=DbConnectionMySql.getInstance(); 
		this.userDAO = new UserDAOimpl(connessioneDb);
		this.sottoscrizionePostDAO = new SottoscrizionePostDAOimpl(connessioneDb);
		this.sottoscrizioneConferenceDAO = new SottoscrizioneConferenceDAOimpl(connessioneDb);
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
				return new Msg(true,"Article - "+sP.getPost().getTitle()+" - removed");
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
		
		// se il post è già presente l'add non avviene
		boolean isPresent = checkPresence(sottoscrizioniPost, postId);
		
		if(isPresent)
			return new Msg(false,"Articles is in your Desk!");
		
		//creiamo un Post, una sottoscrizionePost 
		Post post = servicePost.cercaPost(postId); 
		int position=findPosition(sottoscrizioniPost);
		SottoscrizionePost sottoscrizionePost = new SottoscrizionePost(user.getUsername(),user.getPassword(),post, position);
		messageResult= new Msg(true, "Article - "+sottoscrizionePost.getPost().getTitle()+" - inserted in position "+position );
		boolean result = sottoscrizionePostDAO.createSottoscrizioneP(sottoscrizionePost);
		if(result)
			return messageResult;
		else return new Msg(false, "sottoscrizione non riuscita!");
		 
		}
	
	public Msg moveUpPost(User user, int idSP) {
		boolean result;
		Msg messageResult; 
		
		ArrayList<SottoscrizionePost> sottoscrizioniPost = user.getSottoscrizioniPost(); 
		
		int position = findPositionById(sottoscrizioniPost, idSP);
		
		// cerco la sottoscrizione poi faccio Update 
		if(position == 0 || position == 1)
			return new Msg(false,"impossibile cambiare l'ordinamento");
		  
		SottoscrizionePost sP1 =  findSottoscrizionePost(sottoscrizioniPost,idSP);
		
		
		
		int idSP2 = findIdByPosition(sottoscrizioniPost, position -1);
		
		SottoscrizionePost sP2 =  findSottoscrizionePost(sottoscrizioniPost,idSP2);
		
		sP1.setPosition(position -1);
		
		if(sP2!=null)
			sP2.setPosition(position);
		
		messageResult= new Msg(true, "Article -"+sP1.getPost().getTitle()+" - moved Up ");
		
		result = sottoscrizionePostDAO.updateSottoscrizioneP(sP1);
		
		if(sP2!=null) 
			result = sottoscrizionePostDAO.updateSottoscrizioneP(sP2);   
		
		if(result)
			return messageResult;
		else return new Msg(false, "swap failed!");
		 
		}
	
	public Msg moveDownPost(User user, int idSP) {
		
		boolean result;
		Msg messageResult; 
		
		ArrayList<SottoscrizionePost> sottoscrizioniPost = user.getSottoscrizioniPost(); 
		
		
		int position = findPositionById(sottoscrizioniPost, idSP);
		
		// cerco la sottoscrizione poi faccio Update 
		if(position == 0 || position == 6)
			return new Msg(false,"impossibile cambiare l'ordinamento");
		  
		SottoscrizionePost sP1 =  findSottoscrizionePost(sottoscrizioniPost,idSP);
		
		System.out.println("debug swap Dawn sottoscrizioni:"+sP1);
		
		int idSP2 = findIdByPosition(sottoscrizioniPost, position + 1);
		
		SottoscrizionePost sP2 =  findSottoscrizionePost(sottoscrizioniPost,idSP2);
		
		 
		sP1.setPosition(position +1);
		 
		 
		 
		if(sP2!=null)
			sP2.setPosition(position);
		 
		
		messageResult= new Msg(true, "Article - "+sP1.getPost().getTitle()+" - moved Down ");
		
		result = sottoscrizionePostDAO.updateSottoscrizioneP(sP1);
		System.out.println("Debug swap down:"+result);
		 
		if(sP2!=null)
			result = sottoscrizionePostDAO.updateSottoscrizioneP(sP2);   
		
		if(result)
			return messageResult;
		else return new Msg(false, "swap failed!");
		 
		}
	
	
	private boolean checkPresence(ArrayList<SottoscrizionePost> sottoscrizioniPost, int postId) {
		for(SottoscrizionePost sP: sottoscrizioniPost) {
			if(sP.getPost().getId()==postId)
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
	
	private int findPositionById(ArrayList<SottoscrizionePost> sottoscrizioniPost,int idSP) { 
		for(SottoscrizionePost sP: sottoscrizioniPost)
			if(sP.getId()==idSP)
				return sP.getPosition();
		return 0; 
	}
	
	private int findIdByPosition(ArrayList<SottoscrizionePost> sottoscrizioniPost,int position) { 
		for(SottoscrizionePost sP: sottoscrizioniPost)
			if(sP.getPosition()==position)
				return sP.getId();
		return 0; 
	}
	
	private SottoscrizionePost findSottoscrizionePost(ArrayList<SottoscrizionePost> sottoscrizioniPost,int idSP) { 
		for(SottoscrizionePost sP: sottoscrizioniPost)
			if(sP.getId()==idSP)
				return sP;
		return null; 
	}
	
 
 
	
	}
	
 
