package com.protom.codeforbreakfast.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.protom.codeforbreakfast.dbconnections.DbConnectionMySql;
import com.protom.codeforbreakfast.model.dao.ConferenceDAO;
import com.protom.codeforbreakfast.model.daoimpl.ConferenceDAOimpl;
import com.protom.codeforbreakfast.model.entity.Conference;
import com.protom.codeforbreakfast.model.entity.User; 

public class ServiceConference {
	
	private DbConnectionMySql dbConnection;
	private ConferenceDAO conferenceDAO; 
	
	
	public ServiceConference( ) {
		super();
		this.dbConnection=DbConnectionMySql.getInstance(); 
		this.conferenceDAO = new ConferenceDAOimpl(dbConnection); 
	}
	
	
	public void avviaConnessione() {
		dbConnection.avviaConnessione();
		
	}
	
	public void chiudiConnessione() {
		dbConnection.chiudiConnessione();
		
	}
	
	 
 
//	public boolean insertNewPost(Post post) {
//		boolean result = postDAO.createPost(post);
//		return result;
//		
//	}
	
	

}
