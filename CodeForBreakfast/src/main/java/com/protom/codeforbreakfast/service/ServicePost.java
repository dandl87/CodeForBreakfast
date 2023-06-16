package com.protom.codeforbreakfast.service;
 
import java.util.ArrayList;
import java.util.TreeMap;

import com.protom.codeforbreakfast.dbconnections.DbConnectionMySql;
import com.protom.codeforbreakfast.model.dao.PostDAO;
import com.protom.codeforbreakfast.model.dao.SottoscrizionePostDAO;
import com.protom.codeforbreakfast.model.daoimpl.PostDAOimpl;
import com.protom.codeforbreakfast.model.daoimpl.SottoscrizionePostDAOimpl; 
import com.protom.codeforbreakfast.model.entity.Post;
import com.protom.codeforbreakfast.model.entity.SottoscrizionePost;
import com.protom.codeforbreakfast.model.entity.User; 

public class ServicePost {
	
	private DbConnectionMySql connessioneDb;
	private PostDAO postDAO;
	private SottoscrizionePostDAO sottoscrizionePostDAO;
	private ServiceMsg serviceMsg;
	
	
	public ServicePost( ) {
		super();
		this.connessioneDb=DbConnectionMySql.getInstance();
		this.postDAO = new PostDAOimpl(connessioneDb);
		this.sottoscrizionePostDAO = new SottoscrizionePostDAOimpl(connessioneDb);
		this.serviceMsg = ServiceMsg.getInstance();
		
	}
	 
	 
	public void addPost(User user, int postId, String section) { 
		
		connessioneDb.avviaConnessione();
		
		System.out.println(user);
		 
		ArrayList<SottoscrizionePost> sottoscrizioniPost = user.getSottoscrizioniPost(); 
		
		if(sottoscrizioniPost.size()==6)
			serviceMsg.setValues(false,"Articles Area is full!", section);
		
		// se il post è già presente l'add non avviene
		boolean isPresent = checkPresenceSP(sottoscrizioniPost, postId);
		
		if(isPresent)
			serviceMsg.setValues(false,"Articles is in your Desk!", section);
		
		//creiamo un Post, una sottoscrizionePost 
		Post post = cercaPost(postId);
		
		int position=findPosition(sottoscrizioniPost);
		SottoscrizionePost sottoscrizionePost = new SottoscrizionePost(user.getUsername(),post, position);
		
		boolean result = sottoscrizionePostDAO.createSottoscrizioneP(sottoscrizionePost);
		if(!result) 
		 serviceMsg.setValues(false, "sottoscrizione non riuscita!", section);
		else serviceMsg.setValues(true, sottoscrizionePost.getPost().getTitle()+" - inserted in position "+position , section);
		 
		connessioneDb.chiudiConnessione();
		
		}
	
	
	//Rimuovo post dalla Personal Area
	public void removePost(User user, int postId, String section) {  
		
		connessioneDb.avviaConnessione();
			
		ArrayList<SottoscrizionePost> sottoscrizioniPost = user.getSottoscrizioniPost();
			
		for(SottoscrizionePost sP: sottoscrizioniPost) {
			if(sP.getPost().getId()==postId) { 
				sottoscrizionePostDAO.deleteSottoscrizionePost(sP.getId());
				serviceMsg.setValues(true,sP.getPost().getTitle()+" - removed", section);
			}	
		}
		
		connessioneDb.chiudiConnessione();
		
		 
	}
	
	
	
	public void moveUpPost(User user, int idSP, String section) { 
		
		connessioneDb.avviaConnessione();
		
		boolean result;  
		
		ArrayList<SottoscrizionePost> sottoscrizioniPost = user.getSottoscrizioniPost(); 
		
		int position = findPositionById(sottoscrizioniPost, idSP);
		
		// cerco la sottoscrizione poi faccio Update 
		if(position == 0 || position == 1) 
			 serviceMsg.setValues(false,"impossibile cambiare l'ordinamento", section);
		else {
			
		
		SottoscrizionePost sP1 =  findSottoscrizionePost(sottoscrizioniPost,idSP);
		
		
		
		int idSP2 = findIdByPosition(sottoscrizioniPost, position -1);
		
		SottoscrizionePost sP2 =  findSottoscrizionePost(sottoscrizioniPost,idSP2);
		
		sP1.setPosition(position -1);
		
		if(sP2!=null)
			sP2.setPosition(position);
		
		
		 
		result = sottoscrizionePostDAO.updateSottoscrizioneP(sP1);
		
		if(sP2!=null) 
			 sottoscrizionePostDAO.updateSottoscrizioneP(sP2);   
		
		if(!result)
			 serviceMsg.setValues(false,"swap failed!", section); 
		 
		} 
		
		connessioneDb.chiudiConnessione();
	}
	
	public void moveDownPost(User user, int idSP, String section) { 
		
		connessioneDb.avviaConnessione();
		
		boolean result; 
		ArrayList<SottoscrizionePost> sottoscrizioniPost = user.getSottoscrizioniPost(); 
		
		
		int position = findPositionById(sottoscrizioniPost, idSP);
		
		// cerco la sottoscrizione poi faccio Update 
		if(position == 0 || position == 6)
			serviceMsg.setValues(false,"impossibile cambiare l'ordinamento", section);
		
		else {  
			
		SottoscrizionePost sP1 =  findSottoscrizionePost(sottoscrizioniPost,idSP);
		
		System.out.println("debug swap Dawn sottoscrizioni:"+sP1);
		
		int idSP2 = findIdByPosition(sottoscrizioniPost, position + 1);
		
		SottoscrizionePost sP2 =  findSottoscrizionePost(sottoscrizioniPost,idSP2);
		
		 
		sP1.setPosition(position +1);
		 
		 
		 
		if(sP2!=null)
			sP2.setPosition(position);
		 
		
		
		
		result = sottoscrizionePostDAO.updateSottoscrizioneP(sP1);
		System.out.println("Debug swap down:"+result);
		 
		if(sP2!=null)
			result = sottoscrizionePostDAO.updateSottoscrizioneP(sP2);   
		
		
		if(!result)
			 serviceMsg.setValues(false, "swap failed!", section);
		 
		
		}
		
		connessioneDb.chiudiConnessione();
		 
	}
	
	public ArrayList<SottoscrizionePost> leggiSottoscrizioniPost(User user){
		 
		connessioneDb.avviaConnessione();
		
		ArrayList<SottoscrizionePost> sottoscrizioniPost;
		
		sottoscrizioniPost = sottoscrizionePostDAO.readSottoscrizionePostOfUser(user.getUsername());
		 
		connessioneDb.chiudiConnessione();
		
		return sottoscrizioniPost;
		
	}
	
	private boolean checkPresenceSP(ArrayList<SottoscrizionePost> sottoscrizioniPost, int postId) {
		
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
 
	
	public Post cercaPost(int postId) { 
		
		Post post = postDAO.readPost(postId); 
		
		return post;
		
	}
	
	private int findIdByPosition(ArrayList<SottoscrizionePost> sottoscrizioniPost,int position) { 
		
		for(SottoscrizionePost sP: sottoscrizioniPost)
			if(sP.getPosition()==position)
				return sP.getId();
		
		return 0; 
	}
	
	 
	private int findPositionById(ArrayList<SottoscrizionePost> sottoscrizioniPost,int idSP) { 
		
		for(SottoscrizionePost sP: sottoscrizioniPost)
			if(sP.getId()==idSP)
				return sP.getPosition();
		
		return 0; 
	}
	
	
	private SottoscrizionePost findSottoscrizionePost(ArrayList<SottoscrizionePost> sottoscrizioniPost,int idSP) { 
		
		for(SottoscrizionePost sP: sottoscrizioniPost)
			if(sP.getId()==idSP)
				return sP;
		
		return null; 
	}
	
	public boolean checkArticleOnScreen(User user, int postId, String articleOnDesk) {
		
		ArrayList<SottoscrizionePost> sottoscrizioniPost = user.getSottoscrizioniPost();
		
		boolean checkArticle = false;
		
		for(SottoscrizionePost sP: sottoscrizioniPost) {
			if((sP.getPost().getId()==postId))
					if(sP.getPost().getLink().equals(articleOnDesk)) 
						checkArticle=true;
			
		}
		
		return checkArticle;
	}
	
	
 

}
