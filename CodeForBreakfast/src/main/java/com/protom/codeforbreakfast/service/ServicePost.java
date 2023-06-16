package com.protom.codeforbreakfast.service;
 
import java.util.ArrayList;

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
	
	
	public void chiudiConnessione() {
		connessioneDb.chiudiConnessione();
		
	}
	
	 
 
//	public boolean insertNewPost(Post post) {
//		boolean result = postDAO.createPost(post);
//		return result;
//		
//	}
	
	public Post cercaPost(int postId) {
		Post post = postDAO.readPost(postId);
		return post;
		
	}
	
	public void moveUpPost(User user, int idSP, String section) {
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
		else serviceMsg.setValues(true,sP1.getPost().getTitle()+" article moved up in position: "+sP1.getPosition(), section);
		 
		}
	}
	
	public void moveDownPost(User user, int idSP, String section) {
		
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
		else serviceMsg.setValues(true, sP1.getPost().getTitle()+" article moved down in position: "+sP1.getPosition(), section);
		
		}
		 
	}
	
	public ArrayList<SottoscrizionePost> leggiSottoscrizioniPost(User user){
		return sottoscrizionePostDAO.readSottoscrizionePostOfUser(user.getUsername());
		
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
	
	public boolean checkArticleOnScreen(User user, int postId, String articleOnDesk) {
		ArrayList<SottoscrizionePost> sottoscrizioniPost = user.getSottoscrizioniPost();
		boolean checkArticle = false;
		for(SottoscrizionePost sP: sottoscrizioniPost) {
			if((sP.getPost().getId()==postId))
					if(sP.getPost().getLink().equals(articleOnDesk)) {
					
					checkArticle=true;
			}
		}
		return checkArticle;
	}
 

}
