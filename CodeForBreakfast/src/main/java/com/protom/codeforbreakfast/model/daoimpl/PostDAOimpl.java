package com.protom.codeforbreakfast.model.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.protom.codeforbreakfast.model.dao.PostDAO;
import com.protom.codeforbreakfast.model.entity.Post; 

public class PostDAOimpl implements PostDAO {
	
	private Connection connection;

	public PostDAOimpl(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public boolean createPost(Post post) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Post readPost(int idPost) {

		ResultSet rs; 
		
		try {
		String query = "SELECT * FROM post WHERE id = " + idPost +";";
		System.out.println(query);
		PreparedStatement ps = connection.prepareStatement(query);
		
		
			rs = ps.executeQuery();
			
			rs.next();

			int idFromDB= rs.getInt("id");
			String titleFromDB =rs.getString("titolo");
			String linkFromDB = rs.getString("link");
			String linkImgFromDB = rs.getString("link_img");
			String categoryFromDB = rs.getString("categoria");
			Date dateFromDB = rs.getDate("data");
			Post postFromDB = new Post(idFromDB,titleFromDB,linkFromDB,linkImgFromDB, categoryFromDB, dateFromDB); 
			return postFromDB;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Errore accesso a db!");
			return null;
		}

	}

	@Override
	public boolean updatePost(Post post) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePost(int idPost) {
		// TODO Auto-generated method stub
		return false;
	} 
	
	

}
