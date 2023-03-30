package com.protom.codeforbreakfast.model.dao;

import java.util.ArrayList;

import com.protom.codeforbreakfast.model.entity.SottoscrizionePost; 

public interface SottoscrizionePostDAO {
	
	boolean createSottoscrizioneP(SottoscrizionePost sottoscrizioneP);
	SottoscrizionePost readSottoscrizioneP(int idSottoscrizioneP);
	boolean updateSottoscrizioneP(SottoscrizionePost sottoscrizioneP); 
	boolean deleteSottoscrizioneP(int idSottoscrizioneP);
	ArrayList<SottoscrizionePost> readSottoscrizionePostOfPost(int idPost);
	SottoscrizionePost[] readSottoscrizionePostOfUser(String username, String password);

}
