package com.protom.codeforbreakfast.model.daoimpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.protom.codeforbreakfast.dbconnections.DbConnectionMySql;
import com.protom.codeforbreakfast.model.dao.ConferenceDAO;
import com.protom.codeforbreakfast.model.entity.Conference; 

 

public class ConferenceDAOimpl implements ConferenceDAO {
	
	private DbConnectionMySql dbConnection;

	public ConferenceDAOimpl(DbConnectionMySql dbConnection) {
		super();
		this.dbConnection = dbConnection;
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
		
		PreparedStatement ps = dbConnection.getConnection().prepareStatement(query);
		
		
			rs = ps.executeQuery();
			
			rs.next();

			int idFromDB= rs.getInt("id");
			String title =rs.getString("titolo");
			String speaker =rs.getString("speaker");
			String subTitle =rs.getString("sotto_titolo");
			String link = rs.getString("link");
			String linkImg = rs.getString("link_img");
			String linkImgSmall = rs.getString("link_img_small");			 
			Date date = rs.getDate("data");
			Date dateConf = rs.getDate("data_conf");
			String tempoConf = rs.getString("tempo_conf");
			String description = rs.getString("descrizione");
			int page= rs.getInt("page");
			String youtubeID = rs.getString("youtubeID");
			
			Conference conferenceFromDB = new Conference(idFromDB, title, speaker, subTitle, link, linkImg, linkImgSmall, date, dateConf, tempoConf, description, page, youtubeID); 
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
		PreparedStatement ps = dbConnection.getConnection().prepareStatement(query);
		
		
			rs = ps.executeQuery();
		
		while (rs.next()) {

			int idFromDB= rs.getInt("id");
			String title =rs.getString("titolo");
			String speaker =rs.getString("speaker");
			String subTitle =rs.getString("sotto_titolo");
			String link = rs.getString("link");
			String linkImg = rs.getString("link_img");
			String linkImgSmall = rs.getString("link_img_small");
			Date date = rs.getDate("data");
			Date dateConf = rs.getDate("data_conf");
			String tempoConf = rs.getString("tempo_conf");
			String description = rs.getString("descrizione");
			int page= rs.getInt("page");
			String youtubeID = rs.getString("youtubeID");

			
			Conference conferenceFromDB = new Conference(idFromDB, title, speaker, subTitle, link, linkImg, linkImgSmall, date, dateConf, tempoConf, description, page, youtubeID); 

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

	@Override
	public ArrayList<Conference> readAllConferencesOfPage(int n) {
		ArrayList<Conference> listOfAllConferences = new ArrayList<Conference>();
		ResultSet rs; 
		
		try {
		String query = "SELECT * FROM conference Where page="+n; 
		PreparedStatement ps = dbConnection.getConnection().prepareStatement(query);
		
		
			rs = ps.executeQuery();
		
		while (rs.next()) {

			int idPost = rs.getInt("id");
			String title = rs.getString("titolo");
			String speaker = rs.getString("speaker");
			String subTitle =rs.getString("sotto_titolo");
			String link = rs.getString("link");
			String linkImg = rs.getString("link_img");
			String linkImgSmall = rs.getString("link_img_small");
			Date date= rs.getDate("data");
			Date dateConf= rs.getDate("data_conf");
			String durationConf= rs.getString("tempo_conf");
			String description = rs.getString("descrizione");
			int pageFromDB = rs.getInt("page");
			String youtubeID = rs.getString("youtubeID");

			
			
			 
			
			 
			
			//String dateString = dataImmatricolazione.toString();
			Conference conference = new Conference(idPost, title, speaker,  subTitle, link, linkImg,linkImgSmall, date, dateConf, durationConf,description, pageFromDB, youtubeID);

			listOfAllConferences.add(conference);
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Errore accesso a db!");
			e.printStackTrace();
			return null;
		}

		return listOfAllConferences;
	} 
	
	

}
