package com.protom.codeforbreakfast.model.dao;

import java.util.ArrayList;

import com.protom.codeforbreakfast.model.entity.SottoscrizioneConference; 
 

public interface SottoscrizioneConferenceDAO {
	
	boolean createSottoscrizioneC(SottoscrizioneConference sottoscrizioneC);
	SottoscrizioneConference readSottoscrizioneC(int idSottoscrizioneC);
	boolean updateSottoscrizioneC(SottoscrizioneConference sottoscrizioneC); 
	boolean deleteSottoscrizioneC(int idSottoscrizioneC);
	ArrayList<SottoscrizioneConference> readSottoscrizioneConferenceOfConference(int idConference);
	SottoscrizioneConference[] readSottoscrizioneConferenceOfUser(String username, String password);

}
