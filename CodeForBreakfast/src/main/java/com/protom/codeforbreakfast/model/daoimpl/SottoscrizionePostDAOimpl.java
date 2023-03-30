package com.protom.codeforbreakfast.model.daoimpl;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.protom.codeforbreakfast.model.dao.SottoscrizionePostDAO;
import com.protom.codeforbreakfast.model.entity.SottoscrizionePost;

 

public class SottoscrizionePostDAOimpl implements SottoscrizionePostDAO {
	
	private Connection connection; 

	public SottoscrizionePostDAOimpl(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public boolean createSottoscrizioneP(SottoscrizionePost sottoscrizioneP) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SottoscrizionePost readSottoscrizioneP(int idSottoscrizioneP) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateSottoscrizioneP(SottoscrizionePost sottoscrizioneP) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteSottoscrizioneP(int idSottoscrizioneP) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<SottoscrizionePost> readSottoscrizionePostOfPost(int idPost) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SottoscrizionePost[] readSottoscrizionePostOfUser(String username, String password) {
		SottoscrizionePost[] sottoscrizioniPostList= new SottoscrizionePost[6];
		ResultSet rs; 
		
		try {
		String query = "SELECT * FROM sottoscrizione_post WHERE user_username = '" + username+"' AND user_password='"+password+"';"; 
		PreparedStatement ps = connection.prepareStatement(query);
		
		
			rs = ps.executeQuery();
		int i=0;
		while (rs.next()) {

			int idSottoscrizionePostFromDB = rs.getInt("id");
			String userUsernameFromDB = rs.getString("user_username");
			String userPasswordFromDB = rs.getString("user_password");
			int idPost = rs.getInt("post_id");
			int activeFromDB = rs.getInt("active");
			
			//String dateString = dataImmatricolazione.toString();
			SottoscrizionePost sottoscrizionePost = new SottoscrizionePost(idSottoscrizionePostFromDB, userUsernameFromDB, userPasswordFromDB, idPost,activeFromDB);

			sottoscrizioniPostList[i]=sottoscrizionePost;
			i++;
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Errore accesso a db!");
			e.printStackTrace();
			return null;
		}

		return sottoscrizioniPostList;
	}

}
