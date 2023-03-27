package com.protom.codeforbreakfast.model.dao;

import java.util.ArrayList;

import com.protom.codeforbreakfast.model.entity.Conference;
 

public interface ConferenceDAO {
	
	boolean createConference(Conference conference);
	Conference readConference(int idConference);
	boolean updateConference(Conference conference); 
	boolean deleteConference(int idConference); 
	ArrayList<Conference> readAllConferences();

}
