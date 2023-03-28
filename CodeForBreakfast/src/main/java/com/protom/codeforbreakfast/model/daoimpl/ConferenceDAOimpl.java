package com.protom.codeforbreakfast.model.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.protom.codeforbreakfast.model.dao.ConferenceDAO;
import com.protom.codeforbreakfast.model.entity.Conference;

 

public class ConferenceDAOimpl implements ConferenceDAO {
	
	private Connection connection;

	public ConferenceDAOimpl(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public boolean createConference(Conference conference) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Conference readConference(int idConference) {

		ResultSet rs; 
		
		try {
		String query = "SELECT * FROM conference WHERE id = " + idConference +";";
		
		PreparedStatement ps = connection.prepareStatement(query);
		
		
			rs = ps.executeQuery();
			
			rs.next();

			int idFromDB= rs.getInt("id");
			String titleFromDB =rs.getString("titolo");
			String linkFromDB = rs.getString("link");
			String linkImgFromDB = rs.getString("link_img");
			 
			Date dateFromDB = rs.getDate("data");
			Date dateConfFromDB = rs.getDate("data_conf");
			String tempoConfFromDB = rs.getString("tempo_conf");
			
			Conference conferenceFromDB = new Conference(idFromDB,titleFromDB,linkFromDB,linkImgFromDB, dateFromDB, dateConfFromDB,tempoConfFromDB); 
			return conferenceFromDB;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Errore accesso a db!");
			return null;
		}

	}

	 

	@Override
	public ArrayList<Conference> readAllConferences() {
		ArrayList<Conference> listOfAllConferences = new ArrayList<Conference>();
		ResultSet rs; 
		
		try {
		String query = "SELECT * FROM conference"; 
		PreparedStatement ps = connection.prepareStatement(query);
		
		
			rs = ps.executeQuery();
		
		while (rs.next()) {

			int idFromDB= rs.getInt("id");
			String titleFromDB =rs.getString("titolo");
			String linkFromDB = rs.getString("link");
			String linkImgFromDB = rs.getString("link_img");
			 
			Date dateFromDB = rs.getDate("data");
			Date dateConfFromDB = rs.getDate("data_conf");
			String tempoConfFromDB = rs.getString("tempo_conf");
			
			Conference conferenceFromDB = new Conference(idFromDB,titleFromDB,linkFromDB,linkImgFromDB, dateFromDB, dateConfFromDB,tempoConfFromDB); 

			listOfAllConferences.add(conferenceFromDB);
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Errore accesso a db!");
			e.printStackTrace();
			return null;
		}

		return listOfAllConferences;
	}

	@Override
	public boolean updateConference(Conference conference) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteConference(int idConference) {
		// TODO Auto-generated method stub
		return false;
	} 
	
	

}
