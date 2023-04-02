package com.protom.codeforbreakfast.model.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		
		PreparedStatement ps = connection.prepareStatement(query);
		
		
			rs = ps.executeQuery();
			
			rs.next();

			int idFromDB= rs.getInt("id");
			String titleFromDB =rs.getString("titolo");
			String linkFromDB = rs.getString("link");
			String linkImgFromDB = rs.getString("link_img");
			String categoryFromDB = rs.getString("categoria");
			Date dateFromDB = rs.getDate("data");
			int pageFromDB = rs.getInt("page");
			Post postFromDB = new Post(idFromDB,titleFromDB,linkFromDB,linkImgFromDB, categoryFromDB, dateFromDB,pageFromDB); 
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

	@Override
	public ArrayList<Post> readAllPosts() {
		ArrayList<Post> listOfAllPosts = new ArrayList<Post>();
		ResultSet rs; 
		
		try {
		String query = "SELECT * FROM post"; 
		PreparedStatement ps = connection.prepareStatement(query);
		
		
			rs = ps.executeQuery();
		
		while (rs.next()) {

			int idPostFromDB = rs.getInt("id");
			String titleFromDB = rs.getString("titolo");
			String linkFromDB = rs.getString("link");
			String linkImgFromDB = rs.getString("link_img");
			String categoryFromDB = rs.getString("categoria");
			Date dateFromDB= rs.getDate("data");
			int pageFromDB = rs.getInt("page");
			
			
			 
			
			 
			
			//String dateString = dataImmatricolazione.toString();
			Post post = new Post(idPostFromDB, titleFromDB, linkFromDB, linkImgFromDB, categoryFromDB, dateFromDB, pageFromDB);

			listOfAllPosts.add(post);
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block 
			System.out.println("Errore accesso a db!");
			e.printStackTrace();
			return null;
		}

		return listOfAllPosts;
	} 
	
	@Override
	public ArrayList<Post> readAllPostsOfPage(int n) {
		ArrayList<Post> listOfAllPosts = new ArrayList<Post>();
		ResultSet rs; 
		
		try {
		String query = "SELECT * FROM post Where page="+n; 
		PreparedStatement ps = connection.prepareStatement(query);
		
		
			rs = ps.executeQuery();
		
		while (rs.next()) {

			int idPostFromDB = rs.getInt("id");
			String titleFromDB = rs.getString("titolo");
			String linkFromDB = rs.getString("link");
			String linkImgFromDB = rs.getString("link_img");
			String categoryFromDB = rs.getString("categoria");
			Date dateFromDB= rs.getDate("data");
			int pageFromDB = rs.getInt("page");
			
			
			 
			
			 
			
			//String dateString = dataImmatricolazione.toString();
			Post post = new Post(idPostFromDB, titleFromDB, linkFromDB, linkImgFromDB, categoryFromDB, dateFromDB, pageFromDB);

			listOfAllPosts.add(post);
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Errore accesso a db!");
			e.printStackTrace();
			return null;
		}

		return listOfAllPosts;
	} 
	
	

}
