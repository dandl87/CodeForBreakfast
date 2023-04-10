package com.protom.codeforbreakfast.model.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.protom.codeforbreakfast.dbconnections.DbConnectionMySql;
import com.protom.codeforbreakfast.model.dao.PostDAO;
import com.protom.codeforbreakfast.model.entity.Post;

 

public class PostDAOimpl implements PostDAO {
	
	private DbConnectionMySql dbConnection;

	public PostDAOimpl(DbConnectionMySql dbConnection) {
		super();
		this.dbConnection = dbConnection;
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
		
		PreparedStatement ps = dbConnection.getConnection().prepareStatement(query);
		
		
			rs = ps.executeQuery();
			
			rs.next();

			int idFromDB= rs.getInt("id");
			String title =rs.getString("titolo");
			String subTitle =rs.getString("sotto_titolo");
			String link = rs.getString("link");
			String linkImg = rs.getString("link_img");
			String linkImgSmall = rs.getString("link_img_small");
			String category = rs.getString("categoria");
			Date date = rs.getDate("data");
			String description = rs.getString("descrizione");
			int page = rs.getInt("page");
			
			Post post = new Post(idFromDB, title, subTitle, link, linkImg, linkImgSmall, category, date, description, page ); 
			return post;
			
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
		PreparedStatement ps = dbConnection.getConnection().prepareStatement(query);
		
		
			rs = ps.executeQuery();
		
		while (rs.next()) {

			int idPost = rs.getInt("id");
			String title = rs.getString("titolo");
			String subTitle =rs.getString("sotto_titolo");
			String link = rs.getString("link");
			String linkImg = rs.getString("link_img");
			String linkImgSmall = rs.getString("link_img_small");
			String category = rs.getString("categoria");
			Date date= rs.getDate("data");
			String description = rs.getString("descrizione");
			int page = rs.getInt("page");
			
			
			 
			
			 
			
			//String dateString = dataImmatricolazione.toString();
			Post post = new Post(idPost, title, subTitle, link, linkImg, linkImgSmall, category, date,description, page);

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
		PreparedStatement ps = dbConnection.getConnection().prepareStatement(query);
		
		
			rs = ps.executeQuery();
		
		while (rs.next()) {

			int idPost = rs.getInt("id");
			String title = rs.getString("titolo");
			String subTitle =rs.getString("sotto_titolo");
			String link = rs.getString("link");
			String linkImg = rs.getString("link_img");
			String linkImgSmall = rs.getString("link_img_small");
			String category = rs.getString("categoria");
			Date date= rs.getDate("data");
			String description = rs.getString("descrizione");
			int pageFromDB = rs.getInt("page");
			
			
			 
			
			 
			
			//String dateString = dataImmatricolazione.toString();
			Post post = new Post(idPost, title, subTitle, link, linkImg, linkImgSmall, category, date, description, pageFromDB);

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
