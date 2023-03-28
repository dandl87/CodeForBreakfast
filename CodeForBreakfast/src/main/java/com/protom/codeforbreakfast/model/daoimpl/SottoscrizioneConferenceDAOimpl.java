package com.protom.codeforbreakfast.model.daoimpl;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.protom.codeforbreakfast.model.dao.SottoscrizioneConferenceDAO;
import com.protom.codeforbreakfast.model.entity.SottoscrizioneConference;

 

 

public class SottoscrizioneConferenceDAOimpl implements SottoscrizioneConferenceDAO {
	
	private Connection connection; 

	public SottoscrizioneConferenceDAOimpl(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public boolean createSottoscrizioneC(SottoscrizioneConference sottoscrizioneC) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SottoscrizioneConference readSottoscrizioneC(int idSottoscrizioneC) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateSottoscrizioneC(SottoscrizioneConference sottoscrizioneC) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteSottoscrizioneC(int idSottoscrizioneC) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<SottoscrizioneConference> readSottoscrizioneConferenceOfConference(int idConference) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SottoscrizioneConference> readSottoscrizioneConferenceOfUser(String username, String password) {
		ArrayList<SottoscrizioneConference> sottoscrizioniConferenceList= new ArrayList<SottoscrizioneConference>();
		ResultSet rs; 
		
		try {
		String query = "SELECT * FROM sottoscrizione_conference WHERE user_username = '" + username+"' AND user_password='"+password+"';"; 
		PreparedStatement ps = connection.prepareStatement(query);
		
		
			rs = ps.executeQuery();
		
		while (rs.next()) {

			int idSottoscrizioneConferenceFromDB = rs.getInt("id");
			String userUsernameFromDB = rs.getString("user_username");
			String userPasswordFromDB = rs.getString("user_password");
			int idConference = rs.getInt("conference_id");  
			
			//String dateString = dataImmatricolazione.toString();
			SottoscrizioneConference sottoscrizioneConference = new SottoscrizioneConference(idSottoscrizioneConferenceFromDB, userUsernameFromDB, userPasswordFromDB, idConference);

			sottoscrizioniConferenceList.add(sottoscrizioneConference);
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Errore accesso a db!");
			e.printStackTrace();
			return null;
		}

		return sottoscrizioniConferenceList;
	}

}
