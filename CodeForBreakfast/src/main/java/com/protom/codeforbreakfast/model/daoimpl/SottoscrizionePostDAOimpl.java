package com.protom.codeforbreakfast.model.daoimpl;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.protom.codeforbreakfast.model.dao.PostDAO;
import com.protom.codeforbreakfast.model.dao.SottoscrizionePostDAO;
import com.protom.codeforbreakfast.model.entity.Post;
import com.protom.codeforbreakfast.model.entity.SottoscrizionePost;

 

public class SottoscrizionePostDAOimpl implements SottoscrizionePostDAO {
	
	private Connection connection; 

	public SottoscrizionePostDAOimpl(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public boolean createSottoscrizioneP(SottoscrizionePost sottoscrizioneP) { 
		try {
			  
			 
			 
			PreparedStatement ps;

				ps = connection.prepareStatement(
						"INSERT INTO sottoscrizione_post(position, user_username, user_password, post_id) VALUES ('"
								+ sottoscrizioneP.getPosition() + "', '" + sottoscrizioneP.getUsername() + "','" + sottoscrizioneP.getPassword()+ "','"
								+ sottoscrizioneP.getPost().getId() + "');");
				System.out.println(ps.executeUpdate() + "Log: sottoscrizione Post inserted");
				
				
				
			 
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
				return false;
			} 
			return true; 
	 
	}

	@Override
	public SottoscrizionePost readSottoscrizioneP(int idSottoscrizioneP) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateSottoscrizioneP(SottoscrizionePost sottoscrizioneP) {
		
		try {
			 
			String query = "UPDATE sottoscrizione_post SET position='"+sottoscrizioneP.getPosition()+"', user_username='"+sottoscrizioneP.getUsername()+"',user_password='"+sottoscrizioneP.getPassword()+"', "
					+ "post_id='"+sottoscrizioneP.getPost().getId()+  
					"' WHERE id = " + sottoscrizioneP.getId();
			
			PreparedStatement ps = connection.prepareStatement(query);
			System.out.println(ps.executeUpdate() + " sottoscrizione post aggiornata");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Errore accesso a db!");
				e.getStackTrace() ;
				return false;
			}

			return true; 
	}

	@Override
	public boolean deleteSottoscrizionePost(int idSottoscrizioneP) {
		try { 
			String query = "DELETE FROM sottoscrizione_post WHERE id = " + idSottoscrizioneP;
			PreparedStatement ps = connection.prepareStatement(query);
			int result= ps.executeUpdate() ;
			 if(result==0)
				 return false;
			 
			 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Errore accesso a db!");
				return false;
			}

			return true;
	}
	
 

	@Override
	public ArrayList<SottoscrizionePost> readSottoscrizionePostOfPost(int idPost) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SottoscrizionePost> readSottoscrizionePostOfUser(String username, String password) {
		ArrayList<SottoscrizionePost> sottoscrizioniPostList= new ArrayList<>(6);
		ResultSet rs; 
		
		try {
		String query = "SELECT * FROM sottoscrizione_post WHERE user_username = '" + username+"' AND user_password='"+password+"'ORDER BY position;"; 
		PreparedStatement ps = connection.prepareStatement(query);
		
		
			rs = ps.executeQuery();
		
		while (rs.next()) {

			int idSottoscrizionePostFromDB = rs.getInt("id");
			String userUsernameFromDB = rs.getString("user_username");
			String userPasswordFromDB = rs.getString("user_password");
			int idPostFromDB = rs.getInt("post_id");
			int positionFromDB= rs.getInt("position"); 
			
			PostDAO postDao = new PostDAOimpl(connection);
			Post postFromDB = postDao.readPost(idPostFromDB);
			//String dateString = dataImmatricolazione.toString();
			SottoscrizionePost sottoscrizionePost = new SottoscrizionePost(idSottoscrizionePostFromDB, userUsernameFromDB, userPasswordFromDB, postFromDB,positionFromDB);

			sottoscrizioniPostList.add(sottoscrizionePost);
			
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
